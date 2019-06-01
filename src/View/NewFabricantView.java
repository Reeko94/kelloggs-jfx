package View;

import java.io.File;

import Controller.Main;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class NewFabricantView {
    private Label logoLabel = new Label("Choisir un logo");
    private Label libelleLabel = new Label("Titre");
    private Label fabricantLabel = new Label("Fabricants");

    private Button buttonLogo = new Button("Selectionner un fichier");
    private Button retournerMenu = new Button("Retourner au menu");
    private Button ajouter = new Button("Ajouter");

    private FileChooser fileChooser = new FileChooser();

    private TextField libelleText = new TextField();

    private ChoiceBox choiceBoxFabricant = null;
    
    private ListView listview;

    private File selectedFile;

    public NewFabricantView(GridPane gridPane, String[] typeNutritions) {
        choiceBoxFabricant = new ChoiceBox(FXCollections.observableArrayList(typeNutritions));
        choiceBoxFabricant.getSelectionModel().selectFirst();
        fileChooser.setTitle("Choisir un logo");
        this.listview = new ListView();

        

        buttonLogo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	FileChooser fc = new FileChooser();
            	fc.setInitialDirectory(new File ("C:\\Users\\louis\\Desktop\\fabricants"));
            	fc.getExtensionFilters().addAll(
                        new ExtensionFilter("Images","*.jpg","*.jpeg","*.png")
                );
            	selectedFile = fc.showOpenDialog(null);
            }
        });

        ajouter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int idTypeNutrtition = Main.getIdTypeNutrition((String) choiceBoxFabricant.getSelectionModel().getSelectedItem());
                String[][] fieldVialue = {
                        {"id","type_nutrition_id","libelle","logo"},
                        {"NULL",String.valueOf(idTypeNutrtition),libelleText.getText(),selectedFile.getName()},
                        {"false","false","true","true"}
                };
                Main.uploadFile(selectedFile.getAbsolutePath(),"/var/www/html/kelloggs/public/uploads/fabricant");
                Main.insertFabricant(fieldVialue);
                Main.gotoDashboard(gridPane);
            }
        });

        gridPane.add(fabricantLabel,0,0);
        gridPane.add(choiceBoxFabricant,1,0);
        gridPane.add(libelleLabel,0,1);
        gridPane.add(libelleText,1,1);
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
