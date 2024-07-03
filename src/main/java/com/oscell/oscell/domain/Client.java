package com.oscell.oscell.domain;

import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Table(name="cliente")
public class Client {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NR_SEQUENCIA")
    private Long sequence;

    @Column(name = "NM_CLIENTE")
    private String clientName;

    @Column(name = "CELL_CLIENTE")
    private String clientCell;

    @Column(name = "FIXO_CLIENTE")
    private String clientFixo;

    @Column(name = "CPF_CLIENTE")
    private String clientCPF;

    @Column(name = "CNPJ_CLIENTE")
    private String clientCNPJ;

    @Column(name = "EMAIL_CLIENTE")
    private String clientEmail;

    public Long getSequence() {
        return sequence;
    }

    public void setSequence(Long sequence) {
        this.sequence = sequence;
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

    public String getClientCNPJ() {
        return clientCNPJ;
    }

    public void setClientCNPJ(String clientCNPJ) {
        this.clientCNPJ = clientCNPJ;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
