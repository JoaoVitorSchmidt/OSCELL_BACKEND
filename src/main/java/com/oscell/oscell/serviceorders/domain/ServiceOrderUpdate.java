package com.oscell.oscell.serviceorders.domain;

public class ServiceOrderUpdate {
    private String brand;
    private String model;
    private String description;
    private String situation;
    private String clientName;
    private String clientCell;
    private String clientFixo;
    private String clientCPF;
    private String clientEmail;
    private String userSys;
    
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public String getClientCell() {
        return clientCell;
    }
    public void setClientCell(String clientCell) {
        this.clientCell = clientCell;
    }
    public String getClientFixo() {
        return clientFixo;
    }
    public void setClientFixo(String clientFixo) {
        this.clientFixo = clientFixo;
    }
    public String getClientCPF() {
        return clientCPF;
    }
    public void setClientCPF(String clientCPF) {
        this.clientCPF = clientCPF;
    }
    public String getClientEmail() {
        return clientEmail;
    }
    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
    public String getSituation() {
        return situation;
    }
    public void setSituation(String situation) {
        this.situation = situation;
    }
    public String getUserSys() {
        return userSys;
    }
    public void setUserSys(String userSys) {
        this.userSys = userSys;
    }
}
