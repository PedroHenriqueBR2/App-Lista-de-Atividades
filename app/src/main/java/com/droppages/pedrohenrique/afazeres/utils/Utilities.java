package com.droppages.pedrohenrique.afazeres.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

    public String getDateNow(String formater){
        Date data = new Date();
        DateFormat format = new SimpleDateFormat(formater);
        return format.format(data);
    }

}
