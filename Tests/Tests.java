package Tests;

import Tests.bll.AuteurManager;
import Tests.bol.Auteur;

public class Tests {
    public static void main(String[] args) throws Exception {
        Auteur auteur =         AuteurManager.get(1);
        System.out.println(auteur.toString());
        //auteur.displayAuteur();//javac  -cp .:lib/mariadb-java-client-3.1.1.jar:bol/*.java:dal/*.java:bll/*.java Tests/Tests.java

   }
}