package br.com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.api.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
