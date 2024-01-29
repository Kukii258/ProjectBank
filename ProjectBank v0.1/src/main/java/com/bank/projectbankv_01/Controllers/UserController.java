package com.bank.projectbankv_01.Controllers;

import com.bank.projectbankv_01.Services.TransactionProcess;
import com.bank.projectbankv_01.Services.UserServices;
import com.bank.projectbankv_01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.time.LocalDateTime;


@Controller
public class UserController {

    @Autowired
    UserServices userServices;
    @Autowired
    TransactionProcess trans;

    @GetMapping("/")
    public String getToLoginPage(){
        return "login_page";
    }

    @GetMapping("/info/{id}")
    public String userInfo(@PathVariable(value = "id")long id, Model model){
        model.addAttribute("userInfo", userServices.getById(id));
        return "UserInformations";
    }

    @GetMapping("users_page/{id}")
    public String userPage(@PathVariable(value = "id")long id, Model model){
        User user = userServices.getById(id);
        model.addAttribute("userInfo",user);
        return "users_page";
    }

    @GetMapping("/add")
    public String addUserInfro(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "addUserInformations";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user){
        userServices.save(user);
        return "redirect:/info";
    }
}
