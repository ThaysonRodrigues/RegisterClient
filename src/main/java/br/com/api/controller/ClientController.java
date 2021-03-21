package br.com.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.dto.ClientDTO;
import br.com.api.entity.Client;
import br.com.api.mapper.ClientMapper;
import br.com.api.response.Response;
import br.com.api.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/clientes")
@Api(value = "Register Client API")
@CrossOrigin(origins = "*")
public class ClientController {

	@Autowired
	private ClientService service;

	@Autowired
	private ClientMapper mapper;

	@GetMapping
	@ApiOperation(value = "Endpoint to return all customers registered in the database.")
	public ResponseEntity<Response<List<ClientDTO>>> list() {
		Response<List<ClientDTO>> response = new Response<List<ClientDTO>>();

		List<ClientDTO> clientResponse = service.findAll();

		if (clientResponse.isEmpty()) {
			response.getErrors().add("Nenhum cliente localizado na base de dados");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}

		response.setData(clientResponse);

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(value = "/{clientId}")
	@ApiOperation(value = "Endpoint to return the client with a specific identifier.")
	public ResponseEntity<Response<ClientDTO>> search(@PathVariable Long clientId) {
		Response<ClientDTO> response = new Response<ClientDTO>();

		Optional<Client> client = service.findById(clientId);

		if (!client.isPresent()) {
			response.getErrors().add("Cliente não cadastrado na base de dados");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}

		response.setData(mapper.convertEntityToDTO(client.get()));

		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping
	@ApiOperation(value = "Endpoint to save a new customer.")
	public ResponseEntity<Response<ClientDTO>> save(@Valid @RequestBody ClientDTO clientDto, BindingResult result) {
		Response<ClientDTO> response = new Response<ClientDTO>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}

		Client client = service.save(mapper.convertDTOToEntity(clientDto));

		response.setData(mapper.convertEntityToDTO(client));

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@DeleteMapping(value = "/{clientId}")
	@ApiOperation(value = "Endpoint to delete a customer from the database.")
	public ResponseEntity<Response<String>> delete(@PathVariable Long clientId) {
		Response<String> response = new Response<String>();
		
		Optional<Client> client = service.findById(clientId);
		
		if (!client.isPresent()) {
			response.getErrors().add("Cliente não cadastrado na base de dados");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		
		service.deleteById(clientId);
		response.setData("Cliente apagado com sucesso");
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping
	@ApiOperation(value = "Endpoint to update a customer in the database.")
	public ResponseEntity<Response<ClientDTO>> update(@Valid @RequestBody ClientDTO clientDto, BindingResult result) {
		Response<ClientDTO> response = new Response<ClientDTO>();
		
		Optional<Client> client = service.findById(clientDto.getId());
		
		if(!client.isPresent()) {
			response.getErrors().add("Cliente não cadastrado na base de dados");
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(e -> response.getErrors().add(e.getDefaultMessage()));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		Client saved = service.save(mapper.convertDTOToEntity(clientDto));
		response.setData(mapper.convertEntityToDTO(saved));
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
