package aplicacio.vista;

import javafx.scene.control.Alert;

public class AlertVista {

    public static void alertWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static void alertInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informació");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public static void alertError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Informació");
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
    public static void alertConfirmacio(String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informació");
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
