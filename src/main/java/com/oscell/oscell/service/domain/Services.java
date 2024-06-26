package com.oscell.oscell.service.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="servico")
public class Services {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_SEQUENCIA")
    private Long sequence;

    @Column(name = "NM_SERVICO")
    private String serviceName;

    @Column(name = "DS_SERVICO")
    private String serviceDescription;

    @Column(name = "NR_VALOR")
    private Long serviceValue;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public void setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
    }

    public Long getServiceValue() {
        return serviceValue;
    }

    public void setServiceValue(Long serviceValue) {
        this.serviceValue = serviceValue;
    }

    
}
