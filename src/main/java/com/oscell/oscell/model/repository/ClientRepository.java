package com.oscell.oscell.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
