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
