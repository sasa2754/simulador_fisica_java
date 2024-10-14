package com.desktopapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class CanvaController implements Initializable {

    
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = CanvaController.class.getResource("CanvaScene.fxml");
        
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();

        return new Scene(root);
    }

    @FXML
    public Canvas canva;

    protected Timer timer = new Timer();

    protected Integer numBolas = 100;

    public ArrayList<Mass> bolas = new ArrayList<Mass>();
    
    public GraphicsContext tela;

    public Color colors[] = {Color.BLUE, Color.RED, Color.YELLOW, Color.PURPLE, Color.AQUA, Color.CHOCOLATE, Color.DEEPPINK};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tela = canva.getGraphicsContext2D();

        for (int i = 0; i < numBolas; i++) {
            bolas.add(new Mass(5d, Math.random() * 1266 + 50, Math.random() * 668 - 50, Math.random() * 60 - 30, 0d, canva.getHeight(), canva.getWidth(), colors[(int)(Math.random() * (double)colors.length)]));
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
             
            @Override
            public void run(){
                tela.clearRect(0, 0, canva.getWidth(), canva.getHeight());

                for (int i = 0; i < numBolas; i++) {
                    drawBolinha(bolas.get(i).getX(), bolas.get(i).getY(), bolas.get(i).getColor());
                    bolas.get(i).ballPhysics(3d, 0.9, Math.random() * 20);
                }

            }
        }, 60, 30);
    }

    private void drawBolinha(Double x, Double y, Color cor){
        tela.setFill(cor);

        tela.fillRoundRect(x, y, 50, 50, 50, 50);
    }

    private void drawMolinha(Double x, Double y, Color cor){
        tela.setFill(cor);
        tela.fillRect(x, y, );
        
    }
}