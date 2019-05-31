package View;

import Controller.*;
import Model.ArticleModel;
import Model.BDD;
import Model.FabricantModel;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.util.converter.DoubleStringConverter;

import java.sql.SQLException;
import java.util.ArrayList;

public class ListArticleView {

    private Button retournerMenu = new Button("Retourner au menu");

    private TableView tableView = new TableView();
    private TableColumn fabricantCol = new TableColumn("Nom du fabricant");
    private TableColumn idCol = new TableColumn("Identifiant");
    private TableColumn libelleCol = new TableColumn("Nom de l'article");
    private TableColumn disponibiliteCol = new TableColumn("Disponibilite de l'article");
    private TableColumn prixCol = new TableColumn("Prix unitaire");
    private TableColumn caloriesCol= new TableColumn("Calories présentes");

    public ListArticleView(GridPane gridPane)
    {
        ArrayList<ArticlesFabricants> allArticles = ArticleModel.returnAllAF();

        idCol.setCellValueFactory(
                new PropertyValueFactory<>("aid"));
        idCol.setEditable(false);

        /** FABRICANT **/
        fabricantCol.setCellValueFactory(
                new PropertyValueFactory<>("libelle"));
        fabricantCol.setCellFactory(TextFieldTableCell.forTableColumn());

        fabricantCol.setOnEditCommit(new EventHandler<CellEditEvent<ArticlesFabricants,String >>() {
            @Override
            public void handle(CellEditEvent<ArticlesFabricants,String > event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                int id = Integer.parseInt(idCol.getCellObservableValue(row).getValue().toString());
                String  newValue = event.getNewValue();

                int idFabricant = Main.getIdFabricant(newValue);
                if(idFabricant != 0) {
                    String[][] fieldValue = {
                            {"fabricant_id"},
                            {String.valueOf(idFabricant)},
                            {"false"}
                    };

                    String[][] where = {
                            {"id"},
                            {"="},
                            {String.valueOf(id)},
                            {"false"}
                    };
                    Main.updateArticle(fieldValue,where);
                }
            }
        });


        /** LIBELLE **/

        libelleCol.setCellValueFactory(
                new PropertyValueFactory<Article,String>("designation"));
        libelleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        libelleCol.setOnEditCommit(new EventHandler<CellEditEvent<ArticlesFabricants,String>>() {
            @Override
            public void handle(CellEditEvent<ArticlesFabricants,String> event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                int id = Integer.parseInt(idCol.getCellObservableValue(row).getValue().toString());
                String newValue = event.getNewValue();

                String[][] fieldValue = {
                        {"libelle"},
                        {newValue},
                        {"true"}
                };

                String[][] where = {
                    {"id"},
                    {"="},
                    {String.valueOf(id)},
                    {"false"}
                };
                Main.updateArticle(fieldValue,where);
            }
        });

        /** DISPONIBILITE **/
        disponibiliteCol.setCellValueFactory(
                new PropertyValueFactory<Article,String>("disponibilite"));
        disponibiliteCol.setCellFactory(TextFieldTableCell.forTableColumn());
        disponibiliteCol.setOnEditCommit(new EventHandler<CellEditEvent<ArticlesFabricants,String>>() {
            @Override
            public void handle(CellEditEvent<ArticlesFabricants,String > event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                int id = Integer.parseInt(idCol.getCellObservableValue(row).getValue().toString());
                String newValue = event.getNewValue();
                String disponibilite = "";
                if(newValue.equals("Oui")) {
                    disponibilite = "1";
                } else if(newValue.equals("Non")) {
                    disponibilite = "0";
                }

                if(!disponibilite.equals("")) {
                    String[][] fieldValue = {
                            {"disponibilite"},
                            {disponibilite},
                            {"false"}
                    };

                    String[][] where = {
                            {"id"},
                            {"="},
                            {String.valueOf(id)},
                            {"false"}
                    };


                    Main.updateArticle(fieldValue,where);
                }
            }
        });

        /** PRIX **/


        prixCol.setCellValueFactory(
                new PropertyValueFactory<Article,Double>("prix"));
        prixCol.setOnEditCommit(new EventHandler<CellEditEvent<ArticlesFabricants,Double>>() {
            @Override
            public void handle(CellEditEvent<ArticlesFabricants,Double> event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                int id = Integer.parseInt(idCol.getCellObservableValue(row).getValue().toString());
                Double newValue = event.getNewValue();
                String[][] fieldValue = {
                        {"prix"},
                        {String.valueOf(newValue)},
                        {"false"}
                };

                String[][] where = {
                        {"id"},
                        {"="},
                        {String.valueOf(id)},
                        {"false"}
                };


                Main.updateArticle(fieldValue,where);
            }
        });

        prixCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        /** CALORIES **/


        caloriesCol.setCellValueFactory(
                new PropertyValueFactory<Article,Double>("calories"));

        caloriesCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        caloriesCol.setOnEditCommit(new EventHandler<CellEditEvent<ArticlesFabricants,Double>>() {
            @Override
            public void handle(CellEditEvent<ArticlesFabricants,Double> event) {
                int row = tableView.getFocusModel().getFocusedCell().getRow();
                int id = Integer.parseInt(idCol.getCellObservableValue(row).getValue().toString());
                Double newValue = event.getNewValue();
                String[][] fieldValue = {
                        {"calories"},
                        {String.valueOf(newValue)},
                        {"false"}
                };

                String[][] where = {
                        {"id"},
                        {"="},
                        {String.valueOf(id)},
                        {"false"}
                };


                Main.updateArticle(fieldValue,where);
            }
        });


        final ObservableList<ArticlesFabricants> data = FXCollections.observableArrayList(
                allArticles
        );

        tableView.setItems(data);

        tableView.getColumns().addAll(idCol,fabricantCol,libelleCol,disponibiliteCol,prixCol,caloriesCol);
        tableView.setEditable(true);

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
