package com.gvendas.gestaovendas.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHoraFormatada {

    public static String formataDataHora(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }
}
