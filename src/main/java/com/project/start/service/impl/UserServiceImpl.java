package com.project.start.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.dto.UserDto;
import com.project.start.entity.ConfirmationToken;
import com.project.start.entity.Role;
import com.project.start.entity.User;
import com.project.start.repository.ConfirmationTokenRepository;
import com.project.start.repository.RoleRepository;
import com.project.start.repository.UserRepository;
import com.project.start.service.EmailService;
import com.project.start.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    EmailService emailService;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        confirmationTokenRepository.save(confirmationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("Welcome to SparkEd " + user.getName() + " " + user.getLastname() +
                ". To confirm your account, please click here: " + 
                "https://sparkeducation-production.up.railway.app/confirm-account?token=" + confirmationToken.getConfirmationToken());
        emailService.sendEmail(mailMessage);

        System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

        return;
    }
    
    @Override
    public void updateProfile(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setEmail(userDto.getEmail());
        user.setEmail(userDto.getAreaOfStudy());
        user.setEmail(userDto.getHighestQualification());
        user.setEmail(userDto.getHighestQualification());

        userRepository.save(user);
        
        return;
    }
    
    @Override
    public ResponseEntity<?> confirmEmail(String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setEnabled(true);
            userRepository.save(user);
            return ResponseEntity.ok("Email verified successfully!");
        }
        return ResponseEntity.badRequest().body("Error: Couldn't verify email");
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        UserDto userDto = new UserDto();
       
        userDto.setName(user.getName());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setEmail(user.getAreaOfStudy());
        userDto.setEmail(user.getCompletedDegree());
        userDto.setEmail(user.getCompletedDegree());
        
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Service
    public class ForgotPasswordService {

   

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ConfirmationTokenRepository confirmationTokenRepository;

        @Autowired
        private EmailService emailService;

        public void sendForgotPasswordEmail(String email) throws Exception {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new Exception("No user found with email: " + email);
            }

            // Generate a token
            ConfirmationToken token = new ConfirmationToken(user);
            confirmationTokenRepository.save(token);

            // Send reset link via email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Reset Password");
            mailMessage.setText("Hello,  \n\nTo reset your password, click the link below:\n" +
            		"https://sparkeducation-production.up.railway.app/reset-password?token=" + token.getConfirmationToken() +
                    "\n\nIf you did not request this, please ignore this email.");
            emailService.sendEmail(mailMessage);
        }
    }
}


