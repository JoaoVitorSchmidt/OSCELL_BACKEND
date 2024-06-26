package com.oscell.oscell.serviceorders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oscell.oscell.serviceorders.domain.ServiceOrder;

public interface ServiceOrderRepository extends JpaRepository<ServiceOrder, Long> {

}