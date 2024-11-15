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
import org.springframework.web.bind.annotation.RequestParam;
import com.project.start.dto.UserDto;
import com.project.start.entity.ConfirmationToken;
import com.project.start.entity.Institution;
import com.project.start.entity.User;
import com.project.start.repository.ConfirmationTokenRepository;
import com.project.start.repository.UserRepository;
import com.project.start.service.ProgramService;
import com.project.start.service.SearchInstitutionService;
import com.project.start.service.UserService;
import com.project.start.service.impl.UserServiceImpl.ForgotPasswordService;

import java.util.List;
import jakarta.servlet.http.HttpSession; 




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
    
	
	  @GetMapping("/forgotpassword") public String forgotpassword() { return
	  "forgotpassword"; }
	 
    
    @GetMapping("/searchinstitution")
    public String searchinstitution() {
        return "searchinstitution";
    }
    
   @GetMapping("/updateProfile")
   public String updateProfile() {
        return "updateProfile";
    }
    
    
    
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
    
    @GetMapping
    ("/resetpassword")
    public String resetpassword() {
        return "resetpassword";
    }
    
    
    
    
    // handler method to handle user registration request
    @GetMapping("register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    } 
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the session to log the user out
        return "redirect:/login?logout=true";  // Redirect to the login page with a message
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
    
    
    
    @PostMapping("/login")
    public String login(@RequestParam("email") String email, 
                        @RequestParam("password") String password, 
                        Model model) {
        User existingUser = userService.findByEmail(email);

        if (existingUser == null) {
            // No user with this email
        	model.addAttribute("error", "No account found with that email.");
        	return "redirect:/login?error=true";  // Return to login page with error message
        } else if (!existingUser.getPassword().equals(password)) {
            // User exists, but password is incorrect
            model.addAttribute("error", "Invalid password.");
            return "login";  // Return to login page with error message
        }

        // Successful login
        return "redirect:/index";  // Redirect to the home page
    }
    
    
    
    
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, Model model) {
        System.out.println("Processing forgot password for email: " + email);  // Add logging
        try {
            forgotPasswordService.sendForgotPasswordEmail(email);
            model.addAttribute("message", "Password reset email sent!");
            return "forgotpassword";  // return the same page with a success message
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
            return "forgotpassword";  // Return the same page with an error message
        }
    }


    @GetMapping("/forgot-password")
    public String showForgotPasswordPage() {
        return "forgotpassword"; // Matches the name of  template without the .html extension
    }

    
  
    
    
    @GetMapping("/confirm-account")
    public String confirmUserAccount(@RequestParam("token") String confirmationToken, Model model) {
        ResponseEntity<?> response = userService.confirmEmail(confirmationToken);
        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("successMessage", "Account successfully confirmed! Redirecting to the home page...");
            return "redirect:/index"; // Redirects to the index page
        } else {
            model.addAttribute("errorMessage", "Confirmation failed or token is invalid.");
            return "error"; // Displays an error page or message
        }
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
    
	
    @GetMapping("/resetpassword/token")
    public String displayResetPasswordPage(@RequestParam("token") String token, Model model) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);

        if (confirmationToken != null) {
            model.addAttribute("token", token); // Pass token to the reset password page
            return "resetpassword";
        } else {
            model.addAttribute("error", "Invalid or expired token.");
            return "error";  // Render an error page
        }
    }

   
    
  
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestParam String token,
                                           @RequestParam("password") String newPassword) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByConfirmationToken(token);
        if (confirmationToken == null) {
            return ResponseEntity.badRequest().body("Invalid or expired token.");
        }

        User user = confirmationToken.getUser();
        user.setPassword(newPassword);  
        userRepository.save(user);

        confirmationTokenRepository.delete(confirmationToken);  // Prevent token reuse

        return ResponseEntity.ok("Password has been successfully reset.");
    }

}
