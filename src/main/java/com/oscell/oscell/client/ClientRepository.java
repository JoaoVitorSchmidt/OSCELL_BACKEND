package com.oscell.oscell.client;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.client.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
