package com.oscell.oscell.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
