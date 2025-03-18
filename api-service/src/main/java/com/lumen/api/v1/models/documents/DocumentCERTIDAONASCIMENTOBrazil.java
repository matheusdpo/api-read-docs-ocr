package com.lumen.api.v1.models.documents;

public class DocumentCERTIDAONASCIMENTOBrazil {
    private String nomeCompleto;
    private String dataNascimento;
    private String nroEscricao;
    private String zona;
    private String secao;
    private String municipio;
    private String uf;
    private String dataEmissao;

    @Override
    public String toString() {
        return "{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", nroEscricao='" + nroEscricao + '\'' +
                ", zona='" + zona + '\'' +
                ", secao='" + secao + '\'' +
                ", municipio='" + municipio + '\'' +
                ", uf='" + uf + '\'' +
                ", dataEmissao='" + dataEmissao + '\'' +
                '}';
    }

    //getter

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getNroEscricao() {
        return nroEscricao;
    }

    public String getZona() {
        return zona;
    }

    public String getSecao() {
        return secao;
    }

    public String getMunicipio() {
        return municipio;
    }

    public String getUf() {
        return uf;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    //setter

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNroEscricao(String nroEscricao) {
        this.nroEscricao = nroEscricao;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public void setSecao(String secao) {
        this.secao = secao;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}