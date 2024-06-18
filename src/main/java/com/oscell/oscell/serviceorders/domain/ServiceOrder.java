package com.oscell.oscell.serviceorders.domain;

import jakarta.persistence.Table;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="ordem_servico")
public class ServiceOrder {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_SEQUENCIA")
    private Long sequence;

    @Column(name = "NM_MARCA")
    private String brand;
    
    @Column(name = "NM_MODELO")
    private String model;

    @Column(name = "DT_CRIACAO")
    private Instant creationDate;

    @Column(name = "DS_DEFEITO")
    private String description;

    @Column(name = "DS_SITUACAO")
    private String situation;

    @Column(name = "NR_SEQ_CLIENTE")
    private String clientSequence;

    @Column(name = "NM_CLIENTE")
    private String clientName;

    @Column(name = "CELL_CLIENTE")
    private String clientCell;

    @Column(name = "FIXO_CLIENTE")
    private String clientFixo;

    @Column(name = "CPF_CLIENTE")
    private String clientCPF;

    @Column(name = "EMAIL_CLIENTE")
    private String clientEmail;

    @Column(name = "USUARIO_SIS")
    private Long userSys;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
    }

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

    public Instant getUpdateDate() {
        return creationDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.creationDate = updateDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClientSequence() {
        return clientSequence;
    }

    public void setClientSequence(String clientSequence) {
        this.clientSequence = clientSequence;
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

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public Long getUserSys() {
        return userSys;
    }

    public void setUserSys(Long userSys) {
        this.userSys = userSys;
    }
}
