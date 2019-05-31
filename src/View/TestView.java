package View;

import Controller.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TestView {

    private Label label;
    private Button button;

    public TestView(GridPane gridPane) {

        int nbArticles = Main.getNBArticles();


        this.label = new Label();
        this.button = new Button("Button 1");

        label.setText("Il y a " + nbArticles + " articles");

        gridPane.add(label,0,0);
        gridPane.add(button,1,0);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.gotoDashboard(gridPane);
            }
        });

    }

}
