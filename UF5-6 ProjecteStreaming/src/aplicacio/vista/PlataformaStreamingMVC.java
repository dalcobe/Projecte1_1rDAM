package aplicacio.vista;

import aplicacio.basedades.*;
import java.sql.Connection;
import javafx.application.Application;
import javafx.stage.Stage;

public class PlataformaStreamingMVC extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage escenari) {
        Connection conn = Connexio.getConnection();
        AplicacioVista apVista = new AplicacioVista();
        apVista.inici(escenari);
        
        
    }

}
