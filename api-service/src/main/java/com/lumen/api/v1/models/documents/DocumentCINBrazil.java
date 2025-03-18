package com.lumen.api.v1.models.documents;

public class DocumentCINBrazil {
    private String numeroRegistro;
    private String nomeCompleto;
    private String dataDeNascimento;
    private String localDeNascimento;
    private String ufDeNascimento;
    private String nacionalidade;
    private String nomeMae;
    private String nomePai;
    private String cpf;
    private String dataDeEmissao;
    private String dataDeValidade;
    private String orgaoEmissor;
    private String ufOrgaoEmissor;
    private String nis;
    private String observacoes;

    @Override
    public String toString() {
        return "{" +
                "numeroRegistro='" + numeroRegistro + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", localDeNascimento='" + localDeNascimento + '\'' +
                ", ufDeNascimento='" + ufDeNascimento + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", nomePai='" + nomePai + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataDeEmissao='" + dataDeEmissao + '\'' +
                ", dataDeValidade='" + dataDeValidade + '\'' +
                ", orgaoEmissor='" + orgaoEmissor + '\'' +
                ", ufOrgaoEmissor='" + ufOrgaoEmissor + '\'' +
                ", nis='" + nis + '\'' +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }

    // Getters e Setters
    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getLocalDeNascimento() {
        return localDeNascimento;
    }

    public void setLocalDeNascimento(String localDeNascimento) {
        this.localDeNascimento = localDeNascimento;
    }

    public String getUfDeNascimento() {
        return ufDeNascimento;
    }

    public void setUfDeNascimento(String ufDeNascimento) {
        this.ufDeNascimento = ufDeNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataDeEmissao() {
        return dataDeEmissao;
    }

    public void setDataDeEmissao(String dataDeEmissao) {
        this.dataDeEmissao = dataDeEmissao;
    }

    public String getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public String getUfOrgaoEmissor() {
        return ufOrgaoEmissor;
    }

    public void setUfOrgaoEmissor(String ufOrgaoEmissor) {
        this.ufOrgaoEmissor = ufOrgaoEmissor;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}