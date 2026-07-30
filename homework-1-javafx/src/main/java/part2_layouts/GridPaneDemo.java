package part2_layouts;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class GridPaneDemo extends GridPane {
    public GridPaneDemo() {
        setHgap(10);
        setVgap(10);
        setPadding(new Insets(0));
        setGridLinesVisible(true);

        // the title takes two columns of the first row
        add(new Label("Registration form built with GridPane"), 0, 0, 2, 1);

        TextField nameField = new TextField();
        TextField emailField = new TextField();
        PasswordField passwordField = new PasswordField();
        nameField.setPrefWidth(220);
        emailField.setPrefWidth(220);
        passwordField.setPrefWidth(220);

        add(new Label("Name:"), 0, 1);
        add(nameField, 1, 1);

        add(new Label("Email:"), 0, 2);
        add(emailField, 1, 2);

        add(new Label("Password:"), 0, 3);
        add(passwordField, 1, 3);

        add(new Button("Register"), 1, 4);
    }
}
