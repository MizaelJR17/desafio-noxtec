package com.desafio.agenda.utils;

public class Utils {

    public static boolean isCelularValido(String celular) {
        return celular != null && celular.matches("\\d{11}");
    }
}