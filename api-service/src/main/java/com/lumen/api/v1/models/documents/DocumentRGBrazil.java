package com.lumen.api.v1.models.documents;

public class  DocumentRGBrazil {
    private String nomeCompleto; 
    private String dataDeNascimento; 
    private String naturalidade;
    private String orgaoExpedidor;
    private String fatorRH;
    private String dataExpedicao; 
    private String docOrigem;
    private String registroCivil;
    private String cpf; 
    private String rg; 
    private String nomePai;
    private String nomeMae;
    private String DNI;
    private String tituloEleitor;
    private String CTPS;
    private String uf;
    private String NIS_PIS_PASEP;
    private String identidadeProfissional;
    private String certMilitar;
    private String CNH;
    private String CNS;
    private String observacoes;

    @Override
    public String toString() {
        return "{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", dataDeNascimento='" + dataDeNascimento + '\'' +
                ", naturalidade='" + naturalidade + '\'' +
                ", orgaoExpedidor='" + orgaoExpedidor + '\'' +
                ", fatorRH='" + fatorRH + '\'' +
                ", dataExpedicao='" + dataExpedicao + '\'' +
                ", docOrigem='" + docOrigem + '\'' +
                ", registroCivil='" + registroCivil + '\'' +
                ", cpf='" + cpf + '\'' +
                ", rg='" + rg + '\'' +
                ", nomePai='" + nomePai + '\'' +
                ", nomeMae='" + nomeMae + '\'' +
                ", DNI='" + DNI + '\'' +
                ", tituloEleitor='" + tituloEleitor + '\'' +
                ", CTPS='" + CTPS + '\'' +
                ", uf='" + uf + '\'' +
                ", NIS_PIS_PASEP='" + NIS_PIS_PASEP + '\'' +
                ", identidadeProfissional='" + identidadeProfissional + '\'' +
                ", certMilitar='" + certMilitar + '\'' +
                ", CNH='" + CNH + '\'' +
                ", CNS='" + CNS + '\'' +
                ", observacoes='" + observacoes + '\'' +
                '}';
    }

    //getter
    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public String getDataExpedicao() {
        return dataExpedicao;
    }

    public String getDocOrigem() {
        return docOrigem;
    }

    public String getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
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

    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public String getFatorRH() {
        return fatorRH;
    }

    public String getRegistroCivil() {
        return registroCivil;
    }

    public String getDNI() {
        return DNI;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public String getCTPS() {
        return CTPS;
    }

    public String getUf() {
        return uf;
    }

    public String getNIS_PIS_PASEP() {
        return NIS_PIS_PASEP;
    }

    public String getIdentidadeProfissional() {
        return identidadeProfissional;
    }

    public String getCertMilitar() {
        return certMilitar;
    }

    public String getCNH() {
        return CNH;
    }

    public String getCNS() {
        return CNS;
    }


    //setter

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public void setDataExpedicao(String dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public void setDocOrigem(String docOrigem) {
        this.docOrigem = docOrigem;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setRg(String rg) {
        this.rg = rg;
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

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    public void setFatorRH(String fatorRH) {
        this.fatorRH = fatorRH;
    }

    public void setRegistroCivil(String registroCivil) {
        this.registroCivil = registroCivil;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public void setCTPS(String CTPS) {
        this.CTPS = CTPS;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setNIS_PIS_PASEP(String NIS_PIS_PASEP) {
        this.NIS_PIS_PASEP = NIS_PIS_PASEP;
    }

    public void setIdentidadeProfissional(String identidadeProfissional) {
        this.identidadeProfissional = identidadeProfissional;
    }

    public void setCertMilitar(String certMilitar) {
        this.certMilitar = certMilitar;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public void setCNS(String CNS) {
        this.CNS = CNS;
    }
}
