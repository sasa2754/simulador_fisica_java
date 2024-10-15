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


    @FXML
    public Canvas canva;

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = CanvaController.class.getResource("CanvaScene.fxml");
        
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        CanvaController controller = loader.getController();

        Timer timerScene = new Timer();

        timerScene.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run(){
                controller.canva.setWidth(scene.getWidth());
                controller.canva.setHeight(scene.getHeight());
            }
        }, 50, 50);
        
        return scene;
    }

    protected Timer timer = new Timer();

    protected Integer numBolas = 100;

    protected Integer numMolas = numBolas / 2;

    public ArrayList<Mola> molas = new ArrayList<Mola>();

    public ArrayList<Mass> bolas = new ArrayList<Mass>();
    
    public GraphicsContext tela;

    public Color colors[] = {Color.BLUE, Color.RED, Color.YELLOW, Color.PURPLE, Color.AQUA, Color.CHOCOLATE, Color.DEEPPINK};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tela = canva.getGraphicsContext2D();

        for (int i = 0; i < numBolas; i++) {
            bolas.add(new Mass(Math.random() * 110 + 2, Math.random() * 1266 + 50, Math.random() * 668 - 50, Math.random() * 60 - 30, 0d, canva.getHeight(), canva.getWidth(), Math.random() * 75 + 25,colors[(int)(Math.random() * (double)colors.length)]));
        }

        bolas.get(0).color = Color.DARKORANGE;

        for (int i = 0; i < numMolas; i++) {
            molas.add(new Mola(bolas.get(i * 2), bolas.get(i * 2 + 1)));
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
             
            @Override
            public void run(){
                tela.clearRect(0, 0, canva.getWidth(), canva.getHeight());
                
                for (int i = 0; i < numMolas; i++) {
                    bolas.get(i * 2).ballPhysics(3d, 0.8, Math.random() * 20, canva.getHeight(), canva.getWidth());
                    bolas.get(i * 2 + 1).ballPhysics(3d, 0.8, Math.random() * 20, canva.getHeight(), canva.getWidth());
                    molas.get(i).springPhysics();

                    drawBolinha(bolas.get(i * 2).getX(), bolas.get(i * 2).getY(), bolas.get(i * 2).getSize(),bolas.get(i * 2).getColor());
                    drawBolinha(bolas.get(i * 2 + 1).getX(), bolas.get(i * 2 + 1).getY(), bolas.get(i * 2 + 1).getSize(),bolas.get(i * 2 + 1).getColor());
                    drawMolinha(molas.get(i).getX1(), molas.get(i).getY1(), molas.get(i).getX2(), molas.get(i).getY2(), Color.BLACK);
                }
            }
        }, 60, 30);
    }

    private void drawBolinha(Double x, Double y, Double size, Color cor){
        tela.setFill(cor);

        tela.fillRoundRect(x, y, size, size, size, size);
    }

    private void drawMolinha(Double x1, Double y1, Double x2, Double y2, Color cor){
        tela.setFill(cor);
        tela.strokeLine(x1, y1, x2, y2);
    }
}