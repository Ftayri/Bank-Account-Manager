package tn.iit.controller;

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
import tn.iit.entity.Compte;
import tn.iit.service.CompteService;

@AllArgsConstructor

@Controller
@RequestMapping("/comptes")
public class CompteController {

	private CompteService compteService;

	@ResponseBody
	@GetMapping("/json")
	public List<Compte> findAllJsosn() {
		return compteService.findAll();
	}

	@GetMapping({ "/", "" })
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("comptes", compteService.findAll());

		modelAndView.setViewName("tables");
		return modelAndView;
	}

	@GetMapping("/delete/{rib}")
	public String delete(@PathVariable(name = "rib") Integer rib) {
		compteService.delete(rib);
		return "redirect:/comptes/";
	}
	
	@PostMapping("/edit")
	public String edit(@RequestParam(name = "rib") Integer rib, Model model) {
		Compte compte = compteService.findById(rib);
		model.addAttribute("compte", compte);
		return "edit-compte";
	}

	@ResponseBody
	@PostMapping("/delete-ajax")
	public void deleteAjax(@RequestParam(name = "rib") Integer rib) {
		compteService.delete(rib);
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "nomClient") String nomClient, 
			@RequestParam(name = "solde") float solde) {
		//FIXME
		Compte compte = null;//new Compte(nomClient, solde);
		compteService.save(compte);
		return "redirect:/comptes/";
	}

	@PostMapping("/save2")
	public String save2(@ModelAttribute Compte compte) {
		compteService.save(compte);
		return "redirect:/comptes/";
	}

}
