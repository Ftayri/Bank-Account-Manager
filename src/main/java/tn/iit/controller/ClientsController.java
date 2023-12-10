package tn.iit.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import tn.iit.dto.ClientDto;
import tn.iit.dto.DataTablesResponse;
import tn.iit.entity.BankAccount;
import tn.iit.service.ClientService;

import java.util.List;

@AllArgsConstructor

@Controller
@RequestMapping("/clients")
public class ClientsController {

	@Autowired
	ClientService clientService;

	@GetMapping({ "/", "" })
	public String getClients() {
		return "clients-list";
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
