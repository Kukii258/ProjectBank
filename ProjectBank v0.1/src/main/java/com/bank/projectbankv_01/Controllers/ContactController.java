package com.bank.projectbankv_01.Controllers;

import com.bank.projectbankv_01.Services.ContactServices;
import com.bank.projectbankv_01.Services.UserServices;
import com.bank.projectbankv_01.model.Contact;
import com.bank.projectbankv_01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {


    @Autowired
    UserServices userServices;
    @Autowired
    ContactServices contactServices;

    @GetMapping("/contact/{id}")
    public String Contact(@PathVariable(value = "id")long id, Model model){
        model.addAttribute("user", userServices.getById(id));

        model.addAttribute("contacts",contactServices.listOfContacts(userServices.getById(id).getEmail()));

        return "contact";
    }

    @GetMapping("/addContact/{id}")
    public String addContact(@PathVariable(value = "id") long id, @RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("user", userServices.getById(id));
        Contact contact = new Contact();
        contact.setFriend1(userServices.getById(id).getEmail());

        if ("duplicate".equals(error)) {
            model.addAttribute("error", "Error: This email is already in your friends list.");
        } else if ("validationError".equals(error)) {
            model.addAttribute("error", "Error: Invalid email");
        }

        model.addAttribute("contact", contact);

        return "addContact";
    }

    @PostMapping("/processAddingContact")
    public String addingContact(@ModelAttribute("contact") Contact contact, @ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {


        if(contactServices.isValidEmail(user,redirectAttributes,contact)) {
            return "redirect:/addContact/" + user.getId();
        }else{
            return "redirect:/contact/" + user.getId();
        }
    }


}
