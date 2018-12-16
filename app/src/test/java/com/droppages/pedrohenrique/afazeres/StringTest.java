package com.droppages.pedrohenrique.afazeres;

public class StringTest {

    public static void main(String[] args) {
        if (dateOkay("18/06/1998")){
            System.out.println("Valido");
        } else {
            System.out.println("Invalido");
        }
    }

    private static boolean dateOkay(String d){
        if (d.length() != 10){
            return false;
        }
        if (d.indexOf(2) == '.' && d.indexOf(5) == '.'){
            return true;
        } else if (d.indexOf(2) == '-' && d.indexOf(5) == '-'){
            return true;
        } else if (d.indexOf(2) == '/' && d.indexOf(5) == '/'){
            return true;
        }
        return false;
    }
}
