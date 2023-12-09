package tn.iit.controller;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello") // localhost:8080/hello --> accès à ce controller
public class HelloController {

	// localhost:8080/hello/index
	@GetMapping("/index") // toute requete /index, avec la méthode http GET a accès à cette méthode
	public String goToHello(Model model) {
		model.addAttribute("serverTime", LocalTime.now());
		return "hello"; // va à la page hello.html
	}
	@GetMapping("/index2")
	public ModelAndView goToHello2() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("serverTime", LocalDate.now());
		
		modelAndView.setViewName("hello");// va à la page hello.html
		return modelAndView;
	}
}
