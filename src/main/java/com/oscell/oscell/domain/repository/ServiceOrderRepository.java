package com.oscell.oscell.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.domain.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

}