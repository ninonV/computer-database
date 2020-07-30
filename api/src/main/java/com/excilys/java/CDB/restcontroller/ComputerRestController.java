package com.excilys.java.CDB.restcontroller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.java.CDB.DTO.ComputerDTO;
import com.excilys.java.CDB.DTO.DashboardDTO;
import com.excilys.java.CDB.DTO.mapper.ComputerMapper;
import com.excilys.java.CDB.model.Computer;
import com.excilys.java.CDB.service.ComputerService;

@RestController
@RequestMapping("computers")
public class ComputerRestController {

	@Autowired
	private ComputerService computerService;

	@GetMapping(value = { "", "/" })
	public List<ComputerDTO> listComputers() {
		List<Computer> computers = computerService.listComputers();
		return computers.stream().map(computer -> ComputerMapper.mapComputerToDTO(computer)).collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public ComputerDTO findById(@PathVariable Long id) {
		ComputerDTO computerDTO = new ComputerDTO();
		if (computerService.existComputer(id)) {
			computerDTO = ComputerMapper.mapComputerToDTO(computerService.findbyID(id));
		}
		return computerDTO; 
	}
	
	@DeleteMapping("/{id}")
	public HttpStatus deleteById(@PathVariable Long id) {
		if (computerService.existComputer(id)) {
			computerService.deleteComputer(id);
            return HttpStatus.OK;
		}
		else {
			return HttpStatus.NOT_FOUND;
		}
	}
	
	@PostMapping(value = { "/" })
	public void createComputer(@RequestBody ComputerDTO computerDTO) {
		computerService.createComputer(ComputerMapper.mapDtoToComputer(computerDTO));
	}
	
	@PutMapping(value = { "/" })
	public void updateComputer(@RequestBody ComputerDTO computerDTO) {
		computerService.updateComputer(ComputerMapper.mapDtoToComputer(computerDTO));
	}
	
	@GetMapping("/search/{search}")
	public List<ComputerDTO> searchComputer(@PathVariable String search, @RequestBody DashboardDTO dashboardDTO) {
		return null;
	}
	
	@GetMapping("/order/{order}")
	public List<ComputerDTO> orderComputer(@PathVariable String order, @RequestBody DashboardDTO dashboardDTO) {
		return null;
	}

}
