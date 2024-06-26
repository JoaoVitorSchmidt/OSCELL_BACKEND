package com.oscell.oscell.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.service.domain.Services;

public interface ServiceRepository extends JpaRepository<Services, Long> {

}
