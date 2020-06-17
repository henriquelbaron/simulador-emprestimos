package br.com.henrique.emprestimo.exeptions;

public class DataFormatoErrado extends RuntimeException {
    public DataFormatoErrado(String message) {
        super(message);
    }
}
