package com.bank.projectbankv_01.Controllers;

import com.bank.projectbankv_01.Services.ContactServices;
import com.bank.projectbankv_01.Services.TransactionProcess;
import com.bank.projectbankv_01.Services.TransactionServices;
import com.bank.projectbankv_01.Services.UserServices;
import com.bank.projectbankv_01.model.Transaction;
import com.bank.projectbankv_01.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class TranscationController {

    @Autowired
    UserServices userServices;
    @Autowired
    TransactionServices transactionServices;

    TransactionProcess trans = new TransactionProcess();

    @GetMapping("maketransaction/{id}")
    public String makeTransaction(@PathVariable(value = "id") long id, Model model, @RequestParam(value = "error2", required = false) String error2) {
        trans.setId1(id);
        model.addAttribute("transaction", trans);

        //checks if error exists
         if("validationError".equals(error2)) {
            model.addAttribute("error2", "Error: Not Enough Balance");
        }else if("WrongEmail".equals(error2)){
             model.addAttribute("error2","Error: Invalid email");
        }
        return "transaction";
    }
    @GetMapping("/maketransactionByEmail/{id}/{email}")
    public String makeTransactionByEmail(@PathVariable(value = "id") long id,@PathVariable(value = "email") String email, Model model) {

        //sets attributes for transaction
        model.addAttribute("email",email);
        model.addAttribute("id",id);
        trans.setId1(id);
        trans.setError("NotNull");
        model.addAttribute("transaction",trans);
        model.addAttribute("error1", "NotNull");

        return "transaction";
    }

    @GetMapping("/balance/{id}")
    public String balance(@PathVariable(value = "id") long id, Model model) {
        User user = userServices.getById(id);
        List<Transaction> allTransactions = transactionServices.findAllByUserId(id);

        //sets attribute for balance graph
        model.addAttribute("user", user);
        model.addAttribute("transactions", allTransactions);
        model.addAttribute("graphTitle", "Transfer Graph");
        model.addAttribute("col1", user.getName());

        List<List<Object>> listData = new ArrayList<>();
        List<Object> newRow = Arrays.asList(user.getRegister_date(), 1000);//user.Date();
        listData.add(newRow);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //puts balance data in graph
        int balance = 1000;
        for (Transaction transaction : allTransactions) {
            List<Object> row = Arrays.asList(
                    transaction.getTransaction_date().format(formatter),
                    balance+=Integer.parseInt(transaction.getAmount())
            );
            listData.add(row);
        }
        model.addAttribute("chartData", listData);
        return "balance_page";
    }

    @PostMapping("/processTransaction")
    public String processTransaction(@ModelAttribute("transaction")TransactionProcess trans, Model model, RedirectAttributes redirectAttributes) {

        //check if email exist in dataBase
        if(!userServices.getById(trans.getId1()).getEmail().equals(trans.getEmail())) {
            if (!transactionServices.isValidEmail(trans)) {
                redirectAttributes.addAttribute("error2", "WrongEmail");
                return "redirect:/maketransaction/" + trans.getId1();
                //check if amount is higher than 1 and lower than users balance
            } else if (Integer.parseInt(trans.getAmount()) < 1 || Integer.parseInt(trans.getAmount()) > userServices.getById(trans.getId1()).getBalance()) {
                redirectAttributes.addAttribute("error2", "validationError");
                return "redirect:/maketransaction/" + trans.getId1();
            } else {
                transactionServices.transactionProcess(trans);
            }
        }else{
            redirectAttributes.addAttribute("error2", "WrongEmail");
            return "redirect:/maketransaction/" + trans.getId1();
        }

        return (trans.getError()==null)? "redirect:/users_page/"+trans.getId1() : "redirect:/contact/"+trans.getId1() ;
    }

}
