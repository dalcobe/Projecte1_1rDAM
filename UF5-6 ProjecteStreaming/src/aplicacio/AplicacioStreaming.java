package aplicacio;

import aplicacio.basedades.Connexio;
import static aplicacio.basedades.Connexio.*;
import aplicacio.basedades.PeliculaDAO;
import aplicacio.basedades.ProduccioDAO;
import aplicacio.basedades.SerieDAO;
import aplicacio.model.Pelicula;
import aplicacio.model.Produccions;
import aplicacio.model.Series;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class AplicacioStreaming extends Application {
    BorderPane bp = new BorderPane();
    TextField txtId;
    RadioButton rbPeli;
    RadioButton rbSerie;
    TextField txtDurada;
    TextField txtDirector;
    TextField txtActor;
    TextField txtCategoria;
    TextField txtDuradaTotal;
    TextField txtNumCapitols;
    Label lblDurada;
    Label lblDuradaTotal;
    Label lblNumCapitols;
    
    

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage escenari) throws Exception {
        
        Connection conn = Connexio.getConnection();
        
        System.out.println("streaming...");
        
        bp.setTop(top());
        bp.setCenter(center());
        bp.setLeft(left());
        escenari.getIcons().add(new Image("Imatges/foxy.png"));
        escenari.setTitle("HellFlix");
        Scene escena = new Scene(bp);
        escenari.setScene(escena);
        escenari.setMinHeight(1000);
        escenari.setMinWidth(1000);
        escenari.show();
    }

    private HBox top() {
        HBox hb = new HBox();
        Label lblTitol = new Label("Aplicació Gestió Streaming");
        lblTitol.setFont(new Font("ArialBold", 40));
        lblTitol.setTextFill(Color.WHITE);
        hb.getChildren().add(lblTitol);
        hb.setSpacing(30);
        hb.setPadding(new Insets(10, 10, 10, 10));
        hb.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        hb.setMinHeight(200);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }

    private VBox left() {
        VBox vb = new VBox();
        Label lblTitol = new Label("Gestió Produccions");
        lblTitol.setFont(new Font("ArialBold", 20));
        lblTitol.setTextFill(Color.WHITESMOKE);

        Button btn = new Button("Gestió Pel·licules/Series");
        btn.setMinWidth(80);
        
        btn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                bp.setCenter(center());
            }
            
        });

        Label lblEstadistiques = new Label("Estadistiques");
        lblEstadistiques.setFont(new Font("ArialBold", 20));
        lblEstadistiques.setTextFill(Color.WHITESMOKE);

        Button btnTarifa = new Button("Contractes clients Tarifa");
        btnTarifa.setMinWidth(80);

        Button btnProduccions = new Button("Produccions favorites");
        btnProduccions.setMinWidth(80);
        vb.getChildren().addAll(lblTitol, btn, lblEstadistiques, btnTarifa, btnProduccions);
        vb.setSpacing(30);
        vb.setPadding(new Insets(10, 10, 10, 10));
        vb.setMinSize(180, 100);
        vb.setBackground(new Background(new BackgroundFill(Color.GREY, CornerRadii.EMPTY, Insets.EMPTY)));
        vb.setAlignment(Pos.CENTER);
        return vb;
    }

    private VBox center() {
        VBox vb = new VBox();
        Label lblTitol = new Label("Gestió Produccions");
        lblTitol.setFont(new Font("ArialBold", 30));
        lblTitol.setTextFill(Color.RED);
        VBox vbElem = dadesProduccio();
        HBox hbBotons = botonsGestio();
        hbBotons.setSpacing(10);
        hbBotons.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(lblTitol,vbElem,hbBotons);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        return vb;
    }
    
    private VBox dadesProduccio(){
        VBox vb = new VBox();
        GridPane gp = new GridPane();
        Label lblProduccions = new Label("Gestió produccions");
        lblProduccions.setFont(new Font("ArialBold", 20));
        lblProduccions.setTextFill(Color.RED);
        
        Label lblId = new Label("id");
        txtId = new TextField();
        DropShadow shadow7 = new DropShadow();
        txtId.setEffect(shadow7);
        
        Label lblNom = new Label("Nom");       
        TextField txtNom = new TextField();
        txtNom.setEffect(shadow7);

        Label lblAny = new Label("Any");
        TextField txtAny = new TextField();
        txtAny.setEffect(shadow7);

        Label lblNacionalitat = new Label("Nacionalitat");
        TextField txtNacionalitat = new TextField();
        txtNacionalitat.setEffect(shadow7);

        Label lblCategoria = new Label("Categoria");
        txtCategoria = new TextField();
        txtCategoria.setEffect(shadow7);

        Label lblDirector = new Label("Director");
        txtDirector = new TextField();
        txtDirector.setEffect(shadow7);

        Label lblActor = new Label("Actor");
        txtActor = new TextField();
        txtActor.setEffect(shadow7);

        Label lblFavorit = new Label("Favorit");
        TextField txtFavorit = new TextField();
        txtFavorit.setEffect(shadow7);

        rbPeli = new RadioButton("Pel·licula");
        rbSerie = new RadioButton("Serie");
        ToggleGroup tg = new ToggleGroup();
        rbPeli.setToggleGroup(tg);
        rbSerie.setToggleGroup(tg);


        gp.add(lblId, 0, 0, 1, 1);
        gp.add(txtId, 1, 0, 1, 1);
        gp.add(lblNom, 0, 1, 1, 1);
        gp.add(txtNom, 1, 1, 1, 1);
        gp.add(lblAny, 0, 2, 1, 1);
        gp.add(txtAny, 1, 2, 1, 1);
        gp.add(lblNacionalitat, 0, 3, 1, 1);
        gp.add(txtNacionalitat, 1, 3, 1, 1);
        gp.add(lblCategoria, 0, 4, 1, 1);
        gp.add(txtCategoria, 1, 4, 1, 1);
        gp.add(lblDirector, 0, 5, 1, 1);
        gp.add(txtDirector, 1, 5, 1, 1);
        gp.add(lblActor, 0, 6, 1, 1);
        gp.add(txtActor, 1, 6, 1, 1);
        gp.add(lblFavorit, 0, 7, 1, 1);
        gp.add(txtFavorit, 1, 7, 1, 1);
        gp.add(rbPeli, 0, 8, 1, 1);
        gp.add(rbSerie, 1, 8, 1, 1);
        
        
        
        GridPane gpps = new GridPane();
        
        lblDurada = new Label("Durada");
        lblDurada.setVisible(false);
        txtDurada = new TextField();
        txtDurada.setVisible(false);
        
        lblNumCapitols = new Label("Numero Capitols");
        lblNumCapitols.setVisible(false);
        txtNumCapitols = new TextField();
        txtNumCapitols.setVisible(false);
        
        lblDuradaTotal = new Label("Durada Total");
        lblDuradaTotal.setVisible(false);
        txtDuradaTotal = new TextField();
        txtDuradaTotal.setVisible(false);
        
        gpps.add(lblDurada, 0, 0, 1, 1);
        gpps.add(txtDurada, 1, 0, 1, 1);
        gpps.add(lblNumCapitols, 0, 1, 1, 1);
        gpps.add(txtNumCapitols, 1, 1, 1, 1);
        gpps.add(lblDuradaTotal, 0, 2, 1, 1);
        gpps.add(txtDuradaTotal, 1, 2, 1, 1);
        
        
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> x, Toggle anterior, Toggle actual){
                if (rbPeli.isSelected()) {
                    lblDurada.setVisible(true);
                    txtDurada.setVisible(true);
                    lblNumCapitols.setVisible(false);
                    txtNumCapitols.setVisible(false);
                    lblDuradaTotal.setVisible(false);
                    txtDuradaTotal.setVisible(false);
                } else{
                    lblDurada.setVisible(false);
                    txtDurada.setVisible(false);
                    lblNumCapitols.setVisible(true);
                    txtNumCapitols.setVisible(true);
                    lblDuradaTotal.setVisible(true);
                    txtDuradaTotal.setVisible(true);
                }
            }
            
        });
        
        gp.setAlignment(Pos.CENTER);
        gpps.setAlignment(Pos.CENTER);
        gp.setHgap(10);
        gp.setVgap(10);
        gpps.setHgap(10);
        gpps.setVgap(10);
        gpps.setPadding(new Insets(20,20,20,20));
        vb.setSpacing(20);
        vb.getChildren().addAll(gp,gpps);
        vb.setAlignment(Pos.CENTER);
        
        return vb;
    }
    
    private HBox botonsGestio(){
        
        Button btnConsulta = new Button("Consulta");
        Button btnAlta = new Button("Alta");
        Button btnModificar = new Button("Modificació");
        Button btnBaixa = new Button("Baixa");
        Button btnInicia = new Button("Inicialitza");
        btnConsulta.setOnAction(e -> {
            try {
                consultaProduccions();
            } catch (SQLException ex) {
            }
        });
        
        btnAlta.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Alta peli...");
                //altaProduccio();
            }
            
        });
        
        btnModificar.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Modificant peli...");
                //modificaProduccio();
            }
            
        });

        btnInicia.setOnAction(e -> inicialitzarCampsPantallaProduccio());
        HBox hb = new HBox(btnConsulta,btnAlta,btnModificar,btnBaixa,btnInicia);
        return hb;        
    }
    
    private void consultaProduccions() throws SQLException{
        if (txtId.getText().equals("")) {
            alertWarning("L'identificador ha de tenir un valor");
            
        } else{
            //els camps de la pantalla son text, cal convertir els que son numeric
            int id=Integer.parseInt(txtId.getText());
            inicialitzarCampsPantallaProduccio();
            
            ProduccioDAO prodDAO = new ProduccioDAO();
            
            Produccions produccio = prodDAO.consultaProduccioBD(id);
            if (produccio == null) {
                alertInfo("No existeix aquest identificador de produccio");
            }else{
                //dadesProduccioAPantalla(produccio);
                PeliculaDAO peliDAO = new PeliculaDAO();
                Pelicula peli = peliDAO.consultaPelicuaBD(id);
                
                if (peli!=null) {
                    dadesPeliculaAPantalla(peli);
                }else{
                    SerieDAO serieDAO = new SerieDAO();
                    Series serie = serieDAO.consultaSerieBD(id);
                    if (serie!=null) {
                        dadesSerieAPantalla(serie);
                    }
                }
            }
        }
    }
    
    void inicialitzarCampsPantallaProduccio(){
        txtId.setText("");
    }
    
    private void dadesPeliculaAPantalla(Pelicula p){
        rbPeli.setSelected(true);
        
        txtDurada.setText(String.valueOf(p.getDurada()));
        
        txtCategoria.setText(p.getCategoria(0));
        txtDirector.setText(p.getDirector(0));
        txtActor.setText(p.getActor(0));
        
        txtDurada.setVisible(true);
    }
    
    private void dadesSerieAPantalla(Series s){
        rbSerie.setSelected(true);
        
        txtDuradaTotal.setText(String.valueOf(s.getDuradaTotal()));
        txtNumCapitols.setText(String.valueOf(s.getNumCapitols()));
        
        txtCategoria.setText(s.getCategoria(0));
        txtDirector.setText(s.getDirector(0));
        txtActor.setText(s.getActor(0));
        
        txtDuradaTotal.setVisible(true);
        txtNumCapitols.setVisible(true);
    }

    private void alertWarning(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("Warning");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    private void alertInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Informació");
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
