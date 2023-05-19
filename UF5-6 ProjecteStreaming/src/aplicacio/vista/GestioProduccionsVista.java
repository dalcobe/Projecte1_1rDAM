package aplicacio.vista;

import aplicacio.basedades.PeliculaDAO;
import aplicacio.basedades.ProduccioDAO;
import aplicacio.basedades.SerieDAO;
import aplicacio.model.Pelicula;
import aplicacio.model.Produccions;
import aplicacio.model.Series;
import static aplicacio.vista.AlertVista.alertInfo;
import static aplicacio.vista.AlertVista.alertWarning;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GestioProduccionsVista {

    Label lblId;
    TextField txtId;

    Label lblNom;
    TextField txtNom;

    Label lblNacionalitat;
    TextField txtNacionalitat;

    Label lblAny;
    TextField txtAny;

    Label lblCategoria;
    TextField txtCategoria;

    Label lblDirector;
    TextField txtDirector;

    Label lblActor;
    TextField txtActor;

    Label lblFavorit;
    TextField txtFavorit;

    Label lblDurada;
    TextField txtDurada;

    Label lblNumCapitols;
    TextField txtNumCapitols;

    Label lblDuradaTotal;
    TextField txtDuradaTotal;

    RadioButton rb1;
    RadioButton rb2;

    public VBox centreGestioProduccio() {
        VBox vb = new VBox();
        Label lblTitol = new Label("Gestió Produccions");
        lblTitol.setFont(new Font("ArialBold", 30));
        lblTitol.setTextFill(Color.RED);
        //creem els label i textField de la produccio
        //les operacions CRUD sobre Produccions
        VBox vbElem = dadesProduccio();
        HBox hbBotons = botonsGestio();
        hbBotons.setSpacing(10);
        hbBotons.setAlignment(Pos.CENTER);
        vb.getChildren().addAll(lblTitol, vbElem, hbBotons);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        return vb;
    }

    private VBox dadesProduccio() {
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

        rb1 = new RadioButton("Pel·licula");
        rb2 = new RadioButton("Serie");
        ToggleGroup tg = new ToggleGroup();
        //afegim els radioButton a un objecte ToggleGroup
        //per a que només hi hagi una única seleccio
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);

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
        gp.add(rb1, 0, 8, 1, 1);
        gp.add(rb2, 1, 8, 1, 1);

        //En aquest segon GridPane, posem els camps particulars de
        //Pelicula i de Serie
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

        //Programem la seleccio del radioButton
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> x, Toggle anterior, Toggle actual) {
                if (rb1.isSelected()) {
                    lblDurada.setVisible(true);
                    txtDurada.setVisible(true);
                    lblNumCapitols.setVisible(false);
                    txtNumCapitols.setVisible(false);
                    lblDuradaTotal.setVisible(false);
                    txtDuradaTotal.setVisible(false);
                } else {
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
        gpps.setPadding(new Insets(20, 20, 20, 20));
        vb.setSpacing(20);
        vb.getChildren().addAll(gp, gpps);
        vb.setAlignment(Pos.CENTER);

        return vb;
    }

    private HBox botonsGestio() {
        //Creació i programació dels button CRUD de produccions
        Button btnConsulta = new Button("Consulta");
        Button btnAlta = new Button("Alta");
        Button btnModificar = new Button("Modificació");
        Button btnBaixa = new Button("Baixa");
        Button btnInicia = new Button("Inicialitza");
        
        btnConsulta.setOnAction(e -> consultaProduccions());

        btnAlta.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Alta peli...");
                AlertVista.alertInfo("Opcio no implementada");

            }

        });

        btnModificar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AlertVista.alertInfo("Opcio no implementada");
                System.out.println("Modificant peli...");
                
            }

        });

        btnInicia.setOnAction(e -> inicialitzarCampsPantallaProduccio());
        HBox hb = new HBox(btnConsulta, btnAlta, btnModificar, btnBaixa, btnInicia);
        return hb;
    }

    private void consultaProduccions() {
        if (txtId.getText().equals("")) {
            //Per poder realitzar la consulta d'una producció per id, cal que el camp id tingui algun valor
            alertWarning("L'identificador ha de tenir un valor");

        } else {
            //els camps de la pantalla son text, cal convertir els que son numeric
            int id = Integer.parseInt(txtId.getText());
            //Inicialitzem els camps de la pantalla per si hi ha valors d'anteriors operacions
            inicialitzarCampsPantallaProduccio();
            //Creem un objecte ProduccioDAO per accedir a les dades de Produccio a la Base de dades
            ProduccioDAO prodDAO = new ProduccioDAO();

            Produccions produccio = prodDAO.consultaProduccioBD(id);
            System.out.println(produccio);
            if (produccio == null) {
                alertInfo("No existeix aquest identificador de produccio");
            } else {
                dadesProduccioAPantalla(produccio);
                //accedim a la taula Pelicula de la Base de dades per comprovar si es tracta d'una pelicula
                //creem un objecte PeliculaDAO per accedir a la taula Pelicula
                PeliculaDAO peliDAO = new PeliculaDAO();
                Pelicula peli = peliDAO.consultaPelicuaBD(id);
                //Si retorna un objecte Pelicual, mostrem les dades per pantalla
                System.out.println(peli);
                if (peli != null) {
                    dadesPeliculaAPantalla(peli);
                } else {
                    SerieDAO serieDAO = new SerieDAO();
                    Series serie = serieDAO.consultaSerieBD(id);
                    System.out.println(serie);
                    if (serie != null) {//en el cas de que no existeixi les dades en serie no les mostrara per pantalla
                        dadesSerieAPantalla(serie);
                    }
                }
            }
        }
    }

    void inicialitzarCampsPantallaProduccio() {
        txtId.setText("");
        txtNom.setText("");
        txtAny.setText("");
        txtCategoria.setText("");
        txtDirector.setText("");
        txtActor.setText("");
        txtNacionalitat.setText("");
        txtFavorit.setText("");

        rb1.setSelected(false);
        rb2.setSelected(false);

        lblDurada.setVisible(false);
        txtDurada.setVisible(false);

        lblDuradaTotal.setVisible(false);
        txtDuradaTotal.setVisible(false);
        lblNumCapitols.setVisible(false);
        txtNumCapitols.setVisible(false);
    }

    private void dadesProduccioAPantalla(Produccions p) {
        txtId.setText(String.valueOf(p.getId()));
        txtNom.setText(p.getNom());
        txtNacionalitat.setText(p.getNacionalitat());
        txtAny.setText(String.valueOf(p.getAny()));
        txtFavorit.setText(String.valueOf(p.getFavorit()));
    }

    private void dadesPeliculaAPantalla(Pelicula p) {
        //seleccionem el radio button de serie
        rb1.setSelected(true);
        //mapeig dels atributs de la classe Pelicula als camps de pantalla
        txtDurada.setText(String.valueOf(p.getDurada()));
        //en la consulta de pelicula només visualitzem la primera
        //categoria, director i actor de la pelicula
        txtCategoria.setText(p.getCategoria(0));
        txtDirector.setText(p.getDirector(0));
        txtActor.setText(p.getActor(0));
        

    }

    private void dadesSerieAPantalla(Series s) {
        //seleccionem el radio button de la serie
        rb2.setSelected(true);
        //mapeig dels camps de la classe Serie als camps de la pantalla
        txtDuradaTotal.setText(String.valueOf(s.getDuradaTotal()));
        txtNumCapitols.setText(String.valueOf(s.getNumCapitols()));
        //en la consulta de Serie només visualitzem la primera
        //categoria, director i actor de la serie
        txtCategoria.setText(s.getCategoria(0));
        txtDirector.setText(s.getDirector(0));
        txtActor.setText(s.getActor(0));
        //Posem visible els camps propis de la serie
        txtDuradaTotal.setVisible(true);
        txtNumCapitols.setVisible(true);
    }


}
