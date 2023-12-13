package tn.iit.controller;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tn.iit.dto.ClientDto;
import tn.iit.dto.DataTablesResponse;
import tn.iit.entity.Client;
import tn.iit.service.ClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Controller
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	ClientService clientService;

	@GetMapping({ "/", "" })
	public String getClients(RedirectAttributes redirectAttributes, Model model) {

		@SuppressWarnings("unchecked")
		List<String> errorMessages = (List<String>) redirectAttributes.getFlashAttributes().get("errorMessages");

		if (errorMessages != null) {
			model.addAttribute("errorMessages", errorMessages);
		}

		return "clients-list";
	}

	@GetMapping("/{id}")
	public String getClientById(@PathVariable(name="id") String id){
		return "clients-list";
	}

	@PostMapping("/save")
	public String save(@Valid Client client, BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			if (result.hasErrors()) {
				List<String> errorMessages = result.getAllErrors()
						.stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage)
						.collect(Collectors.toList());
				redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
				return "redirect:/clients/";
			}
			clientService.save(client);
			return "redirect:/clients/";
		} catch (DuplicateKeyException e) {
			List<String> errorMessages = new ArrayList<>();
			errorMessages.add(e.getMessage());
			redirectAttributes.addFlashAttribute("errorMessages", errorMessages);
			return "redirect:/clients/";
		}
	}

	@PostMapping("/edit")
	public ResponseEntity<Object> edit(@Valid Client client, BindingResult result, RedirectAttributes redirectAttributes) {
		try {
			if (result.hasErrors()) {
				List<String> errorMessages = result.getAllErrors()
						.stream()
						.map(DefaultMessageSourceResolvable::getDefaultMessage)
						.toList();
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessages);
			}
			clientService.edit(client);
			return ResponseEntity.ok("Client edited successfully");
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(e.getMessage());
		}
	}

	@ResponseBody
	@PostMapping("/delete")
	public void delete(@RequestParam(name="cin") String cin){
		clientService.delete(cin);
	}


	@GetMapping("/data-table")
	@ResponseBody
	public DataTablesResponse<ClientDto> list(@RequestParam int draw,
											  @RequestParam int start,
											  @RequestParam int length,
											  @RequestParam(required = false, name = "search[value]") String searchValue,
											  @RequestParam(required = false, name = "order[0][dir]") String orderDir) {

		List<ClientDto> clients = clientService.getClientsForDataTable(start, length, searchValue, orderDir);
		DataTablesResponse<ClientDto> response = new DataTablesResponse<>();
		response.setDraw(draw);
		response.setRecordsTotal(clientService.getTotalClientCount());
		response.setRecordsFiltered(clientService.getFilteredClientCount(searchValue));
		response.setData(clients);
		return response;
	}

}
