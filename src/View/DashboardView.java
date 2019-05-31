package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import static javafx.scene.paint.Color.RED;

public class DashboardView {


    private Pane pane = new Pane();
    private Label nbAll = new Label();
    private HBox hb = new HBox();


    public DashboardView(GridPane gridPane) {

        int nbArticles = Main.getNBArticles();
        int nbFabricants = Main.getNbFabricants();
        int nbUsers = Main.getNbUsers();

        /*System.out.println(nbArticles + " " + nbFabricants + " " + nbUsers);
        this.nbAll.setText("Articles ("+nbArticles+") -- Fabricants("+nbFabricants+") -- Utilisateurs("+nbUsers+")");


        gridPane.add(this.nbAll,0,1);
        this.nbAll.setTextAlignment(TextAlignment.CENTER);*/



        // An image file on the hard drive.
        // TODO: EDIT this link
        File file = new File("D:\\telechargements\\programmation pour ecole\\kelloggs-jfx\\src\\images\\logo.png");

        String localUrl = null;
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Image image = new Image(localUrl);
        ImageView imageView = new ImageView(image);

        gridPane.add(imageView,0,0);

        Button newArticle = new Button("Ajouter un article");
        newArticle.setMinWidth(gridPane.getWidth() / 2 - 40);
        gridPane.add(newArticle,0,6);
        newArticle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoNewArticle(gridPane);
            }
        });

        Button listArticle = new Button("Lister les articles");
        listArticle.setMinWidth(gridPane.getWidth() / 2 - 40);
        listArticle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoListArticle(gridPane);
            }
        });
        gridPane.add(listArticle,1,6);

        Button addFabricant = new Button("Ajouter un fabricant");
        addFabricant.setMinWidth(gridPane.getWidth() / 2 - 40);
        gridPane.add(addFabricant,0,7);
        addFabricant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoNewFabricant(gridPane);
            }
        });

        Button listFabricant = new Button("Lister les fabricants");
        listFabricant.setMinWidth(gridPane.getWidth() / 2 - 40);
        gridPane.add(listFabricant,1,7);
        listFabricant.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoListFabricant(gridPane);
            }
        });

        Button listUtilisateur = new Button("Lister les utilisateurs");
        listUtilisateur.setMinWidth(gridPane.getWidth() / 2 - 40);
        listUtilisateur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoListUser(gridPane);
            }
        });
        gridPane.add(listUtilisateur,1,8);

        Button listTypeNutrition = new Button("Lister les types de nutrtions");
        listTypeNutrition.setMinWidth(gridPane.getWidth() / 2 - 40);
        listTypeNutrition.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoListTypeNutrition(gridPane);
            }
        });
        gridPane.add(listTypeNutrition,1,9);

        /*Button testButton = new Button("TEXT POUR LE BOUTON");
        testButton.setMinWidth(gridPane.getWidth() / 2 - 40);
        testButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoTest(gridPane);
            }
        });
        gridPane.add(testButton,0,8);*/

        //gridPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
}

