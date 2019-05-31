package View;

import Controller.Fabricant;
import Controller.FabricantsTypeNutrition;
import Controller.Main;
import Model.FabricantModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.util.converter.DoubleStringConverter;


import java.util.ArrayList;

public class ListFabricantView {

    private Button retournerMenu = new Button("Retourner au menu");

    private ArrayList<FabricantsTypeNutrition> fabricants = FabricantModel.getAll();

    private TableView tableView = new TableView();

    private TableColumn idCol = new TableColumn("ID");
    private TableColumn nom = new TableColumn("Nom de la marque");
    private TableColumn logo = new TableColumn("Chemin du logo");
    private TableColumn type_nutrition = new TableColumn("Type de nutrition");
    private final ObservableList<FabricantsTypeNutrition> data  = FXCollections.observableArrayList(fabricants);

    public ListFabricantView(GridPane gridPane){

        tableView.setEditable(true);


        idCol.setCellValueFactory(
                new PropertyValueFactory<FabricantsTypeNutrition,Integer>("id")
        );


        nom.setCellValueFactory(
                new PropertyValueFactory<FabricantsTypeNutrition,String >("libelle")
        );
        nom.setCellFactory(
                TextFieldTableCell.forTableColumn()
        );
        nom.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FabricantsTypeNutrition,String >>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FabricantsTypeNutrition,String> event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                String id = idCol.getCellObservableValue(row).getValue().toString();
                String newValue = event.getNewValue();
                String[][] fieldValue = {
                        {"libelle"},
                        {newValue},
                        {"true"}
                };

                String[][] where = {
                        {"id"},
                        {"="},
                        {id},
                        {"true"}
                };


                Main.updateFabricant(fieldValue,where);
                Main.gotoListFabricant(gridPane);
            }
        });
// -------------------

        logo.setCellValueFactory(
                new PropertyValueFactory<FabricantsTypeNutrition,String>("logo")
        );
        logo.setCellFactory(TextFieldTableCell.forTableColumn());
        logo.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FabricantsTypeNutrition,String >>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FabricantsTypeNutrition,String > event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                String id = idCol.getCellObservableValue(row).getValue().toString();
                String newValue = event.getNewValue();
                String[][] fieldValue = {
                        {"logo"},
                        {newValue},
                        {"true"}
                };

                String[][] where = {
                        {"id"},
                        {"="},
                        {id},
                        {"true"}
                };


                Main.updateFabricant(fieldValue,where);
                Main.gotoListFabricant(gridPane);
            }
        });

        type_nutrition.setCellValueFactory(
                new PropertyValueFactory<FabricantsTypeNutrition,String >("typeNutriton")
        );
        type_nutrition.setCellFactory(TextFieldTableCell.forTableColumn());
        type_nutrition.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<FabricantsTypeNutrition,String >>() {
            @Override
            public void handle(TableColumn.CellEditEvent<FabricantsTypeNutrition,String> event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                int id = Integer.parseInt(idCol.getCellObservableValue(row).getValue().toString());
                String  newValue = event.getNewValue();
                int type_nutrition = Main.getIdTypeNutrition(newValue);
                if(type_nutrition != 0) {
                    String[][] fieldValue = {
                            {"type_nutrition_id"},
                            {String.valueOf(type_nutrition)},
                            {"true"}
                    };

                    String[][] where = {
                            {"id"},
                            {"="},
                            {String.valueOf(id)},
                            {"false"}
                    };
       
                    


                    Main.updateFabricant(fieldValue,where);
                }
            }
        
        });
//----------------------

        tableView.setItems(data);

        tableView.setEditable(true);

        tableView.getColumns().addAll(idCol,nom,logo,type_nutrition);

        gridPane.add(tableView,0,0);
        gridPane.add(retournerMenu,0,1);

        retournerMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoDashboard(gridPane);
            }
        });
    }

}
