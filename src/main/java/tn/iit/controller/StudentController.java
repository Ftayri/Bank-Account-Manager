package tn.iit.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import tn.iit.dto.StudentDto;
import tn.iit.service.StudentService;

@AllArgsConstructor

@Controller
@RequestMapping("/students")
public class StudentController {

	private StudentService studentService;

	@ResponseBody
	@GetMapping("/json")
	public List<StudentDto> findAllJson() {
		return studentService.findAll();
	}

	@GetMapping({ "/", "" })
	public ModelAndView findAll() {
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("students", studentService.findAll());

		modelAndView.setViewName("students");// va à la page students.html
		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(name = "id") int id) {
		studentService.delete(id);
		return "redirect:/students/";
	}

	@ResponseBody
	@PostMapping("/delete-ajax")
	public void deleteAjax(@RequestParam(name = "id") int id) {
		studentService.delete(id);
	}

	@PostMapping("/save")
	public String save(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name,
			@RequestParam(name = "gender") Character gender) {
		StudentDto studentDto = new StudentDto(id, name, gender);
		studentService.save(studentDto);
		return "redirect:/students/";
	}

	@PostMapping("/save2")
	public String save2(@ModelAttribute StudentDto studentDto) {
		studentService.save(studentDto);
		return "redirect:/students/";
	}

}
