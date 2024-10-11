package com.desktopapp;

import javafx.event.ActionEvent;
import java.net.URL;


public class MainController {
    public static Scene CreateScene(String fxmlFile) throws Exception {
        URL sceneUrl = MainController.class.getResource(fxmlFile);

        if (sceneUrl == null) {
            throw new Exception("Arquivo FXML '" + fxmlFile + "' não foi encontrado!");
        }
        
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        return new Scene(root);
    }
    
}
