package com.lumen.api.v1.models.documents;

public class DocumentCARTAOMILITARBrazil {
    private String organizacaoMilitar;
    private String numeroIdentificacao;
    private String validade;
    private String nomeCompleto;
    private String cargoMilitar;
    private String nomePai;
    private String nomeMae;
    private String dataNascimento;
    private String naturalidade;
    private String uf;
    private String pais;
    private String rg;
    private String cpf;
    private String localEmissao;
    private String dataEmissao;
    private String militarResponsavel;
    private String cargoMilitarResponsavel;
    private String observacoes;

    @Override
    public String toString() {
        return "{" +
                "organizacaoMilitar='" + organizacaoMilitar + '\'' +
                ", numeroIdentificacao='" + numeroIdentificacao + '\'' +
                ", validade='" + validade + '\'' +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", cargoMilitar='" + cargoMilitar + '\'' +
                ", nomePai='" + nomePai + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", dataNascimento='" + dataNascimento + '\'' +
                ", naturalidade='" + naturalidade + '\'' +
                ", uf='" + uf + '\'' +
                ", pais='" + pais + '\'' +
                ", rg='" + rg + '\'' +
                ", cpf='" + cpf + '\'' +
                ", localEmissao='" + localEmissao + '\'' +
                ", dataEmissao='" + dataEmissao + '\'' +
                ", militarResponsavel='" + militarResponsavel + '\'' +
                ", cargoMilitarResponsavel='" + cargoMilitarResponsavel + '\'' +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }

    //getter

    public String getOrganizacaoMilitar() {
        return organizacaoMilitar;
    }

    public String getNumeroIdentificacao() {
        return numeroIdentificacao;
    }

    public String getValidade() {
        return validade;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCargoMilitar() {
        return cargoMilitar;
    }

    public String getNomePai() {
        return nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getUf() {
        return uf;
    }

    public String getPais() {
        return pais;
    }

    public String getRg() {
        return rg;
    }

    public String getCpf() {
        return cpf;
    }

    public String getLocalEmissao() {
        return localEmissao;
    }

    public String getDataEmissao() {
        return dataEmissao;
    }

    public String getMilitarResponsavel() {
        return militarResponsavel;
    }

    public String getCargoMilitarResponsavel() {
        return cargoMilitarResponsavel;
    }

    public String getObservacoes() {
        return observacoes;
    }


    //setter

    public void setOrganizacaoMilitar(String organizacaoMilitar) {
        this.organizacaoMilitar = organizacaoMilitar;
    }

    public void setNumeroIdentificacao(String numeroIdentificacao) {
        this.numeroIdentificacao = numeroIdentificacao;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setCargoMilitar(String cargoMilitar) {
        this.cargoMilitar = cargoMilitar;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setLocalEmissao(String localEmissao) {
        this.localEmissao = localEmissao;
    }

    public void setDataEmissao(String dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public void setMilitarResponsavel(String militarResponsavel) {
        this.militarResponsavel = militarResponsavel;
    }

    public void setCargoMilitarResponsavel(String cargoMilitarResponsavel) {
        this.cargoMilitarResponsavel = cargoMilitarResponsavel;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}