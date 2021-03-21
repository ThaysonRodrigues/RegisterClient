package br.com.api.mapper;

import org.springframework.stereotype.Component;

import br.com.api.dto.ClientDTO;
import br.com.api.entity.Client;

@Component
public class ClientMapper {
	
	public Client convertDTOToEntity(ClientDTO dto) {
		Client client = new Client();
		
		client.setId(dto.getId());
		client.setName(dto.getNome());
		client.setCpf(dto.getCpf());
		client.setAddress(dto.getEndereco());
		
		return client;
	}
	
	public ClientDTO convertEntityToDTO(Client client) {
		ClientDTO clientDto = new ClientDTO();
		
		clientDto.setId(client.getId());
		clientDto.setNome(client.getName());
		clientDto.setCpf(client.getCpf());
		clientDto.setEndereco(client.getAddress());
		
		return clientDto;
	}
	
}
