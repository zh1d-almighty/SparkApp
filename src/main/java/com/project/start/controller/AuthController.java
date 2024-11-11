package com.project.start.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.start.dto.ProgramsDto;
import com.project.start.dto.UserDto;
import com.project.start.entity.ConfirmationToken;
import com.project.start.entity.Institution;
import com.project.start.entity.Programs;
import com.project.start.entity.User;
import com.project.start.repository.ConfirmationTokenRepository;
import com.project.start.repository.UserRepository;
import com.project.start.service.ProgramService;
import com.project.start.service.SearchInstitutionService;
import com.project.start.service.UserService;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    private SearchInstitutionService searchInstitutionService;
    private ProgramService programService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("index")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    
    @GetMapping("/forgotpassword")
    public String forgotpassword() {
        return "forgotpassword";
    }
    
    @GetMapping("/searchinstitution")
    public String searchinstitution() {
        return "searchinstitution";
    }
    
    //@GetMapping("/updateProfile")
    //public String updateProfile() {
     //   return "updateProfile";
    //}
    
    
    
    @GetMapping("/matchme")
    public String matchme() {
        return "matchme";
    }
    
    @GetMapping("/ratings")
    public String ratings() {
        return "ratings";
    }
    
    @GetMapping("/aboutus")
    public String aboutus(){
        return "aboutus";
    }
    @GetMapping("/programs")
    public String programs(){
        return "programs";
    }
    @GetMapping("/resources")
    public String resources(){
        return "resources";
    }
    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle register user form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        
        userService.saveUser(user);
        return "redirect:/register?success";
    }
    
    @PostMapping("/register/index") //"/register/updateprofile") 
    public String updateprofile(@Valid @ModelAttribute("user") UserDto user,
                               BindingResult result,
                               Model model){
        
        userService.updateProfile(user);
        return "redirect:/users?success";
    }

    
    @GetMapping(value="/confirm-account")
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }
    
    @GetMapping("/users")
    public String listRegisteredUsers(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }
    
    @GetMapping("/search_result")
    public String search_result() {
        return "search_result";
    }
    
    @PostMapping("/searchinstitution/searchInt")
    public String home(Institution institution, Model model, String keyword) {
    	
       
        return "searchinstitution";
    }
  
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam("token") String token, @RequestParam("password") String newPassword) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (confirmationToken == null) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        User user = confirmationToken.getUser();
        user.setPassword(newPassword);  // You should encode the password here
        userRepository.save(user);
        
        return ResponseEntity.ok("Password has been successfully reset.");
    }
}
