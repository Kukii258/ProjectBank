package com.bank.projectbankv_01.Services;

import com.bank.projectbankv_01.Repositories.ContactRepository;
import com.bank.projectbankv_01.model.Contact;
import com.bank.projectbankv_01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Service
public class ContactServices {

    @Autowired
    ContactRepository contactRepository;
    @Autowired
    UserServices userServices;

    //function for puting all contact with same email in list
    public List<Contact> listOfContacts(String email) {
        List<Contact> friends1 = contactRepository.findByFriend1(email);

        return friends1;
    }
    //checking if email for adding contact is valid, and if it is already in contacts
    public Boolean isValidEmail(User user, RedirectAttributes redirectAttributes,Contact contact){
        boolean isDuplicate = false;

        for (Contact contact1 : listOfContacts(user.getEmail())) {
            if (contact1.getFriend1().equals(contact.getFriend1()) && contact1.getFriend2().equals(contact.getFriend2())) {
                isDuplicate = true;
                redirectAttributes.addAttribute("error", "duplicate");
                return true;
            }
        }
        if (!isDuplicate) {
            for (User user1 : userServices.getAllUsers()) {
                if (user1.getEmail().equals(contact.getFriend2()) && !user1.getEmail().equals(contact.getFriend1())) {
                    save(contact);
                    return false;
                }
            }
            redirectAttributes.addAttribute("error", "validationError");
            return true;
        }
        return false;
    }
    //save contact in contact dataBase
    public void save(Contact contact) {
        if (contact != null) {
            contactRepository.save(contact);
        }
    }
}
