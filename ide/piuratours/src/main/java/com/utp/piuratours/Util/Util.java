package com.utp.piuratours.Util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Util {
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encriptarContrasena(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public static boolean desencriptarContrasena(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public static Integer getInteger(String value) {
        Integer valueInteger = null;
        if (!Objects.isNull(value) && !value.isEmpty()) {
            try {
                valueInteger = Integer.parseInt(value);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return valueInteger;
    }

    public static String obtenerFechaActualString() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        return currentDate.format(formatter);
    }

    public static Boolean convertirStringBoolean(String parametro) {
        try {
            return Boolean.parseBoolean(parametro);
        } catch (Exception e) {
            System.out.println("Error al convertir el string a boolean: " + e.getMessage());
            return null;
        }
    }
}
