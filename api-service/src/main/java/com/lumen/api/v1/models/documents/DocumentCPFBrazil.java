package com.lumen.api.v1.models.documents;

public class DocumentCPFBrazil {
    private String nomeCompleto;
    private String cpf;

    @Override
    public String toString() {
        return "{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}
