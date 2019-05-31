package View;

import Controller.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;



public class NewArticleView {
    private Label logoLabel = new Label("Choisir un logo");
    private Label libelleLabel = new Label("Titre");
    private Label prixLabel = new Label("Prix");
    private Label caloriesLabel = new Label("Calories");
    private Label fabricantLabel = new Label("Fabricants");

    private Button buttonLogo = new Button("Selectionner un fichier");
    private Button retournerMenu = new Button("Retourner au menu");
    private Button ajouter = new Button("Ajouter");

    private FileChooser fileChooser = new FileChooser();

    private TextField libelleText = new TextField();
    private TextField prixText = new TextField();
    private TextField caloriesText = new TextField();

    private ChoiceBox choiceBoxFabricant = null;

    private ListView listview;

    private File selectedFile;

    public NewArticleView(GridPane gridPane,String[] fabricants) {
        choiceBoxFabricant = new ChoiceBox(FXCollections.observableArrayList(fabricants));
        choiceBoxFabricant.getSelectionModel().selectFirst();
        fileChooser.setTitle("Choisir un logo");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Images","*.jpg","*.jpeg","*.png")
        );

        buttonLogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fc = new FileChooser();
                fc.setInitialDirectory(new File ("/Users/theosikli/Desktop"));
                fc.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Images","*.jpg","*.jpeg","*.png")
                );
                selectedFile = fc.showOpenDialog(null);

            }
        });

        ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int idFabricant = Main.getIdFabricant((String) choiceBoxFabricant.getSelectionModel().getSelectedItem());
                String[][] fieldVialue = {
                        {"id","fabricant_id","libelle","disponibilite","prix","calories","url_media"},
                        {"NULL",String.valueOf(idFabricant),libelleText.getText(),"1",prixText.getText(),caloriesText.getText(),selectedFile.getName()},
                        {"false","false","true","false","false","false","true"}
                };
                Main.insertArticle(fieldVialue);
                Main.uploadFile(selectedFile.getAbsolutePath(),"/var/www/html/website/public/uploads/article");
            }
        });

        gridPane.add(fabricantLabel,0,0);
        gridPane.add(choiceBoxFabricant,1,0);
        gridPane.add(libelleLabel,0,1);
        gridPane.add(libelleText,1,1);
        gridPane.add(prixLabel,0,2);
        gridPane.add(prixText,1,2);
        gridPane.add(caloriesLabel,0,3);
        gridPane.add(caloriesText,1,3);
        gridPane.add(logoLabel,0,4);
        gridPane.add(buttonLogo,1,4);
        gridPane.add(ajouter,1,5);
        gridPane.add(retournerMenu,0,5);



        retournerMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoDashboard(gridPane);
            }
        });


    }
}
