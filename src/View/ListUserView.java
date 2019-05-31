package View;

import Controller.Article;
import Controller.Main;
import Controller.User;
import Model.UtilisateurModel;
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
import javafx.util.converter.IntegerStringConverter;

import java.util.ArrayList;

public class ListUserView {

    private Button retournerMenu = new Button("Retourner au menu");
    private TableView tableView = new TableView();

    private ArrayList<User> allUsers = UtilisateurModel.returnAllUserForList();

    private TableColumn emailCol = new TableColumn("Email");
    private TableColumn nomCol = new TableColumn("Nom");
    private TableColumn prenomCol = new TableColumn("Prenom");
    private TableColumn type = new TableColumn("Type");
    private TableColumn actif = new TableColumn("Actif");
    private final ObservableList<User> data = FXCollections.observableArrayList(allUsers);

    public ListUserView(GridPane gridPane) {

        tableView.setEditable(false);


        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));


        nomCol.setCellValueFactory(
                new PropertyValueFactory<>("nom"));


        prenomCol.setCellValueFactory(
                new PropertyValueFactory<>("prenom"));


        type.setCellValueFactory(
                new PropertyValueFactory<>("discr"));


        actif.setCellValueFactory(
                new PropertyValueFactory<Article,Integer>("actif"));
        actif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        actif.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<User,Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<User,Integer> event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                String email = emailCol.getCellObservableValue(row).getValue().toString();
                int newValue = event.getNewValue();
                String[][] fieldValue = {
                        {"actif"},
                        {String.valueOf(newValue)},
                        {"false"}
                };

                String[][] where = {
                        {"email"},
                        {"="},
                        {email},
                        {"true"}
                };


                Main.updateUser(fieldValue,where);
            }
        });

        tableView.setItems(data);
        tableView.setEditable(true);

        tableView.getColumns().addAll(emailCol,nomCol,prenomCol,type,actif);

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
