package Exemples.Chapitre7;

//import java.sql;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
//import java.util.Date;
import java.util.Date;

import Exemples.biblioXX.BOL.Auteur;
import Exemples.dal.DB;
import Exemples.lib.Utils;

public class test {
    public static void main(String[] args) throws Exception {

        try (DB db = new DB();) {
            Date date_naissance = new SimpleDateFormat("yyyy-MM-dd").parse("1892-01-03");
            Auteur auteur= new Auteur(null, "Tolkien", "J.R.R.", date_naissance, "Anglais");
            
            // 
        }
    }
}