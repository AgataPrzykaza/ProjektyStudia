package com.example.library;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {

    @FXML
    private Label welcomeText;

    @FXML
    protected void handleLoginButton() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
