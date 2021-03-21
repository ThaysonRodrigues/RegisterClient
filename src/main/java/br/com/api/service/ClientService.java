package br.com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.dto.ClientDTO;
import br.com.api.entity.Client;
import br.com.api.mapper.ClientMapper;
import br.com.api.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private ClientMapper mapper;

	public Client save(Client client) {
		return repository.save(client);
	}

	public Optional<Client> findById(Long clientId) {
		return repository.findById(clientId);
	}

	public List<ClientDTO> findAll() {
		List<Client> listClients = repository.findAll();

		List<ClientDTO> listClientsDto = new ArrayList<ClientDTO>();

		listClients.forEach(i -> listClientsDto.add(mapper.convertEntityToDTO(i)));

		return listClientsDto;
	}

	public void deleteById(Long clientId) {
		repository.deleteById(clientId);
	}
}
