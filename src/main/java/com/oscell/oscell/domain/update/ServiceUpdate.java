package com.oscell.oscell.domain.update;

public class ServiceUpdate {
    private String serviceName;
    private String serviceDescription;
    private Long serviceValue;
    
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
