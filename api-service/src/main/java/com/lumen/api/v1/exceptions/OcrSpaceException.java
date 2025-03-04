package com.lumen.api.v1.exceptions;


public class OcrSpaceException extends Exception {


    public OcrSpaceException() {
        super("Erro durante a operação com arquivos do OCR Space!");
    }


    public OcrSpaceException(String mensagem) {
        super(mensagem);
    }


    public OcrSpaceException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}