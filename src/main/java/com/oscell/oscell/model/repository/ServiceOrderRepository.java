package com.oscell.oscell.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.model.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

}