package com.lumen.api.v1.models.documents;

public class DocumentCNHBrazil {
    private String nomeCompleto;
    private String dataDeNascimento;
    private String dataPrimeiraHabilitacao;
    private String localDeNascimento;
    private String ufDeNascimento;
    private String dataDaEmissao;
    private String dataDeValidade;
    private String rg;
    private String orgaoEmissor;
    private String ufOrgaoEmissor;
    private String cpf;
    private String numeroRegistro;
    private String categorias;
    private String nacionalidade;
    private String nomePai;
    private String nomeMae;
    private String observacoes;

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getDataPrimeiraHabilitacao() {
        return dataPrimeiraHabilitacao;
    }

    public String getLocalDeNascimento() {
        return localDeNascimento;
    }

    public String getUfDeNascimento() {
        return ufDeNascimento;
    }

    public String getDataDaEmissao() {
        return dataDaEmissao;
    }

    public String getDataDeValidade() {
        return dataDeValidade;
    }

    public String getRg() {
        return rg;
    }

    public String getOrgaoEmissor() {
        return orgaoEmissor;
    }

    public String getUfOrgaoEmissor() {
        return ufOrgaoEmissor;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public String getCategorias() {
        return categorias;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public String getNomePai() {
        return nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setDataPrimeiraHabilitacao(String dataPrimeiraHabilitacao) {
        this.dataPrimeiraHabilitacao = dataPrimeiraHabilitacao;
    }

    public void setLocalDeNascimento(String localDeNascimento) {
        this.localDeNascimento = localDeNascimento;
    }

    public void setUfDeNascimento(String ufDeNascimento) {
        this.ufDeNascimento = ufDeNascimento;
    }

    public void setDataDaEmissao(String dataDaEmissao) {
        this.dataDaEmissao = dataDaEmissao;
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setOrgaoEmissor(String orgaoEmissor) {
        this.orgaoEmissor = orgaoEmissor;
    }

    public void setUfOrgaoEmissor(String ufOrgaoEmissor) {
        this.ufOrgaoEmissor = ufOrgaoEmissor;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", dataPrimeiraHabilitacao='" + dataPrimeiraHabilitacao + '\'' +
                ", localDeNascimento='" + localDeNascimento + '\'' +
                ", ufDeNascimento='" + ufDeNascimento + '\'' +
                ", dataDaEmissao='" + dataDaEmissao + '\'' +
                ", dataDeValidade='" + dataDeValidade + '\'' +
                ", rg='" + rg + '\'' +
                ", orgaoEmissor='" + orgaoEmissor + '\'' +
                ", ufOrgaoEmissor='" + ufOrgaoEmissor + '\'' +
                ", cpf='" + cpf + '\'' +
                ", numeroRegistro='" + numeroRegistro + '\'' +
                ", categorias='" + categorias + '\'' +
                ", nacionalidade='" + nacionalidade + '\'' +
                ", nomePai='" + nomePai + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }
}
