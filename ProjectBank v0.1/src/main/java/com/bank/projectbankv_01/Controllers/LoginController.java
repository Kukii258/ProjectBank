package com.bank.projectbankv_01.Controllers;

import com.bank.projectbankv_01.Services.UserServices;
import com.bank.projectbankv_01.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class LoginController {
    private final UserServices userServices;

    public LoginController(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest",new User());
        model.addAttribute("registerRequest", new User());
        return "login_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user,Model model){
        //try catch if user dataBase is empty then prevent its from error
        System.out.println("Register request " + user);
        try {
            if (userServices.getByEmail(user.getEmail()) != null) {
                model.addAttribute("error", "notNull");
                return "login_page";
            } else {
                User registeredUser = userServices.registerUser(user.getName(), user.getPassword(), user.getEmail(), user.getAge(), user.getAdress(), user.getPhoneNumber());
                model.addAttribute("userInfo", registeredUser);
                return "/users_page";
            }
        }catch (Exception e){
            User registeredUser = userServices.registerUser(user.getName(), user.getPassword(), user.getEmail(), user.getAge(), user.getAdress(), user.getPhoneNumber());
            model.addAttribute("userInfo", registeredUser);
            return "/users_page";
        }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model){
        System.out.println("Login request: " + user);
        User authenticated = userServices.authenticate(user.getName(),user.getPassword());
        if(authenticated != null){
            model.addAttribute("userInfo", authenticated);
            return "users_page";
        }else{
            model.addAttribute("error2","notNull");
            return "login_page";
        }
    }
}
