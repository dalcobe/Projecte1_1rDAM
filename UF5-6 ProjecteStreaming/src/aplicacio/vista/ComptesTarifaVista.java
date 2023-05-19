package aplicacio.vista;

import aplicacio.basedades.CompteDAO;
import aplicacio.model.Compte;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ComptesTarifaVista {

    private TableView tblContractes;
    private Label lblModalitat;

    public VBox visualitzarContractesTarifa() {
        VBox vb = new VBox();

        Label lblContractes = new Label("VISUALITZACIÓ DE CONTRACTES PER MODALITAT");
        lblContractes.setFont(new Font("ArialBold", 40));
        lblContractes.setTextFill(Color.RED);

        MenuItem menuItemGR = new MenuItem("Gratuita");
        MenuItem menuItemBA = new MenuItem("Basica");
        MenuItem menuItemPR = new MenuItem("Premium");
        MenuItem menuItemTotes = new MenuItem("Totes");
        
        MenuButton menuButton = new MenuButton("Modalitats", null, menuItemGR, menuItemBA, menuItemPR, menuItemTotes);
        
        //Programem les diferents seleccions del menuButton
        //ha triat tarifaGratuita
        menuItemGR.setOnAction(event -> visualitzarContractesTarifaDades(1));
         //ha triat tarifaBasica
        menuItemBA.setOnAction(event -> visualitzarContractesTarifaDades(2));
         //ha triat tarifaPremium
        menuItemPR.setOnAction(event -> visualitzarContractesTarifaDades(3));
         //ha triat totes les modalitats
        menuItemTotes.setOnAction(event -> visualitzarContractesTarifaDades(0));
        
        lblModalitat = new Label();
        lblModalitat.setFont(new Font("Arial Bod", 30));
        lblModalitat.setTextFill(Color.BLUE);
        
        tblContractes = new TableView();
        
        TableColumn<Compte, Integer> colIdCompte = new TableColumn<>("Id Compte");
        colIdCompte.setCellValueFactory(new PropertyValueFactory<>("idCompte"));
        
        TableColumn<Compte, Integer> colIdCli = new TableColumn<>("Id Client");
        colIdCli.setCellValueFactory(new PropertyValueFactory<>("id Client"));
        
        TableColumn<Compte, Integer> colCliDNI = new TableColumn<>("DNI");
        colCliDNI.setMinWidth(100);
        colCliDNI.setCellValueFactory(new PropertyValueFactory<>("dniCli"));
        
        TableColumn<Compte, Integer> colCliNom = new TableColumn<>("Nom");
        colCliNom.setMinWidth(100);
        colCliNom.setCellValueFactory(new PropertyValueFactory<>("nomCli"));
        
        TableColumn<Compte, Integer> colDataAlta = new TableColumn<>("D.alta");
        colDataAlta.setCellValueFactory(new PropertyValueFactory<>("dataAlta"));
        
        TableColumn<Compte, Integer> colModalitat = new TableColumn<>("Modalitat");
        colModalitat.setCellValueFactory(new PropertyValueFactory<>("idModalitat"));
        
        tblContractes.getColumns().addAll(colIdCompte,colIdCli,colCliDNI, colCliNom, colDataAlta, colModalitat);
        tblContractes.setMinHeight(100);
        tblContractes.setMaxWidth(600);
        
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(30);
        vb.setPadding(new Insets(20,20,20,20));
        
        vb.getChildren().addAll(lblContractes, menuButton, lblModalitat, tblContractes);
        
        
        return vb;

    }
    
    
    private void visualitzarContractesTarifaDades(int modalitat){
        CompteDAO compteDAO = new CompteDAO();
        
        ArrayList<Compte> compteTarifa = compteDAO.obtenirComptesModalitatBD(modalitat);
        
        switch (modalitat){
            case 0: lblModalitat.setText("COMPTES DE TOTES LES MODALITATS"); break;
            case 1: lblModalitat.setText("COMPTES MODALITAT GRATUÏTA"); break;
            case 2: lblModalitat.setText("COMPTES MODALITAT BÀSICA"); break;
            case 3: lblModalitat.setText("COMPTES MODALITAT PREMIUM"); break;
        }
        
        tblContractes.getItems().clear();
        tblContractes.getItems().addAll(compteTarifa);
    }

}
