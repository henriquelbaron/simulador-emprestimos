package br.com.henrique.emprestimo.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilsDate {
    private final static String DATA = "dd/MM/yyyy";

    public static LocalDate stringToLocalDate(String data) {
        if (data == null || data.isEmpty()) return null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATA);
            return LocalDate.parse(data, formatter);
        } catch (Exception e) {
            throw new RuntimeException("Data não deve estar no formato dd/MM/yyyy");
        }
    }

    public static String getyyyyMMdd() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public static String dataFormatada(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
