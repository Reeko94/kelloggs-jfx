package View;

import Controller.Main;
import Controller.TypeNutrition;
import Controller.User;
import Model.TypeNutritionModel;
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
import javafx.scene.text.Text;

import java.util.ArrayList;

public class TypeNutritionView {

    private Button retournerMenu = new Button("Retourner au menu");

    private Text labelText = new Text();

    private ArrayList<TypeNutrition> typeNutritions = TypeNutritionModel.returnAllTypeNutrition();

    private TableView tableView = new TableView();

    private TableColumn idCol = new TableColumn("ID");
    private TableColumn libelleCol = new TableColumn("Libelle");

    private final ObservableList<TypeNutrition> data = FXCollections.observableArrayList(typeNutritions);


    public TypeNutritionView(GridPane gridPane) {


        tableView.setEditable(true);


        idCol.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        libelleCol.setCellValueFactory(
                new PropertyValueFactory<>("libelle")
        );
        libelleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        libelleCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<TypeNutrition,String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<TypeNutrition,String > event) {
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


                Main.updateTypeNutrition(fieldValue,where);
            }
        });

        retournerMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoDashboard(gridPane);
            }
        });

        tableView.setItems(data);
        tableView.getColumns().addAll(idCol,libelleCol);
        gridPane.add(tableView,0,0);
        gridPane.add(retournerMenu,0,1);

    }
}
