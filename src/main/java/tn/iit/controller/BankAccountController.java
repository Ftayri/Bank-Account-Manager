package tn.iit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import tn.iit.dao.ClientDao;
import tn.iit.entity.BankAccount;
import tn.iit.entity.Client;
import tn.iit.service.BankAccountService;

@AllArgsConstructor

@Controller
@RequestMapping("/accounts")
public class BankAccountController {



	private BankAccountService bankAccountService;


	@ResponseBody
	@GetMapping("/json")
	public List<BankAccount> findAllJsosn() {
		return bankAccountService.findAll();
	}

	@GetMapping({ "/", "" })
	public ModelAndView findAll() {
		Client c2= new Client("2344","hatem","zouari","sousse");


		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("bankAccounts", bankAccountService.findAll());
		modelAndView.addObject("clients", c2);
		modelAndView.setViewName("accounts-list");
		return modelAndView;
	}


	@GetMapping("/delete/{rib}")
	public String delete(@PathVariable(name = "rib") Integer rib) {
		bankAccountService.delete(rib);
		return "redirect:/comptes/";
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
	public String save(@RequestParam(name = "nomClient") String nomClient, 
			@RequestParam(name = "balance") float balance) {
		//FIXME
		BankAccount bankAccount = null;//new Compte(nomClient, solde);
		bankAccountService.save(bankAccount);
		return "redirect:/comptes/";
	}

	@PostMapping("/save2")
	public String save2(@RequestParam(name = "cin") String cin,
						@RequestParam(name = "balance") float balance) {
		//findById client after add it

		return "redirect:/accounts/";
	}
	@ResponseBody
	@PostMapping("/edit-balance-ajax")
	public BankAccount edit(@RequestParam(name = "rib") Integer rib,@RequestParam(name = "balance") Integer balance ) {
		return bankAccountService.editBalance(rib,balance);
	}

}
