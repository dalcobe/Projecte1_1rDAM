package aplicacio.basedades;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connexio {

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            //si hi ha una connexió establerta, ens retorna aquesta connexió, és un atribut static
            if (conn == null) {
                //si no s'ha establert cap connexió a la Base de dades, ens conectem
                String url = "jdbc:mysql://localhost:3336/";
                String bbdd ="impflix";
                String user = "root";
                String password = "et1056sh";
                //Fat/3232 et1056sh
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(url + bbdd, user, password);
                System.out.println("Connexio establerta");

                
            }

        } catch (Exception e) {
            System.out.println("No s'ha pogut connectar a la BD");
            e.printStackTrace();

        }
        //retorna la connexió establerta a la Base de dades
        return conn;
    }

}
