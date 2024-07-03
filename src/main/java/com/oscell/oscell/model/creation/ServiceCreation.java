package com.oscell.oscell.model.creation;

public class ServiceCreation {
    private Long sequence;
    private String serviceName;
    private String serviceDescription;
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
