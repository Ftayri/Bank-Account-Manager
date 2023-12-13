package tn.iit.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tn.iit.entity.BankAccount;
import tn.iit.entity.Client;
import tn.iit.service.BankAccountService;
import tn.iit.service.ClientService;

@AllArgsConstructor

@Controller
@RequestMapping("/accounts")
public class BankAccountController {


    private BankAccountService bankAccountService;

    private ClientService clientService;


    @GetMapping({"/", ""})
    public ModelAndView findAll() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("bankAccounts", bankAccountService.findAll());
        modelAndView.setViewName("accounts-list");
        return modelAndView;
    }

    @PostMapping("/edit")
    public String edit(@RequestParam(name = "rib") Integer rib, Model model) {
        BankAccount bankAccount = bankAccountService.findById(rib);
        model.addAttribute("compte", bankAccount);
        return "edit-compte";
    }

    @ResponseBody
    @PostMapping("/delete-ajax")
    public void deleteAjax(@RequestParam(name = "rib") Integer rib) {
        bankAccountService.delete(rib);
    }

    @PostMapping("/save")
    public String save(@RequestParam(name = "cin") String cin,
                       @RequestParam(name = "balance") float balance) {
        Client client = clientService.findById(cin);
        BankAccount bankAccount = new BankAccount(balance, client);
        bankAccountService.save(bankAccount);
        return "redirect:/accounts/";
    }

    @ResponseBody
    @PostMapping("/edit-balance-ajax")
    public BankAccount edit(@RequestParam(name = "rib") Integer rib, @RequestParam(name = "balance") Integer balance) {
        return bankAccountService.editBalance(rib, balance);
    }

}
