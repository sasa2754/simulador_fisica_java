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

    protected Integer numBolas = 0;

    protected Integer numMolas = numBolas / 2;

    protected Integer numQuadrados = 15;

    public ArrayList<Mola> molas = new ArrayList<Mola>();

    public ArrayList<Mass> bolas = new ArrayList<Mass>();

    public ArrayList<Quadrado> quadrados = new ArrayList<Quadrado>();
    
    public GraphicsContext tela;

    public Color colors[] = {Color.BLUE, Color.RED, Color.YELLOW, Color.PURPLE, Color.AQUA, Color.CHOCOLATE, Color.DEEPPINK};

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        tela = canva.getGraphicsContext2D();

        for (int i = 0; i < numBolas; i++) {
            bolas.add(new Mass(Math.random() * 110 + 2, Math.random() * 1266 + 50, Math.random() * 668 - 50, Math.random() * 60 - 30, 0d, canva.getHeight(), canva.getWidth(), Math.random() * 75 + 25,colors[(int)(Math.random() * (double)colors.length)]));
        }

        for (int i = 0; i < numQuadrados; i++) {
            quadrados.add(new Quadrado(Math.random() * 110 + 2, 533d, 50d, Math.random() * 60 - 30, 0d, canva.getHeight(), canva.getWidth(), 10d, 200d, colors[(int)(Math.random() * (double)colors.length)]));
        }

        for (int i = 0; i < numMolas; i++) {
            molas.add(new Mola(bolas.get(i * 2), bolas.get(i * 2 + 1)));
        }
        
        timer.scheduleAtFixedRate(new TimerTask() {
             
            @Override
            public void run(){
                tela.clearRect(0, 0, canva.getWidth(), canva.getHeight());
                
                for (int i = 0; i < numMolas; i++) {

                    if (numBolas == 0) {
                        continue;
                    }
                    
                    bolas.get(i * 2).ballPhysics(3d, 0.8, canva.getHeight(), canva.getWidth());
                    bolas.get(i * 2 + 1).ballPhysics(3d, 0.8, canva.getHeight(), canva.getWidth());
                    molas.get(i).springPhysics();
                    

                    drawBolinha(bolas.get(i * 2).getX(), bolas.get(i * 2).getY(), bolas.get(i * 2).getSize(),bolas.get(i * 2).getColor());
                    drawBolinha(bolas.get(i * 2 + 1).getX(), bolas.get(i * 2 + 1).getY(), bolas.get(i * 2 + 1).getSize(),bolas.get(i * 2 + 1).getColor());
                    drawMolinha(molas.get(i).getX1(), molas.get(i).getY1(), molas.get(i).getX2(), molas.get(i).getY2(), Color.BLACK);
                }

                for (int i = 0; i < numQuadrados; i++) {

                    quadrados.get(i).squarePhysics(canva.getHeight(), canva.getWidth());

                    drawQuadrado(quadrados.get(i));
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

    public void drawQuadrado(Quadrado quadrado){
        drawBolinha(quadrado.ball1.getX(), quadrado.ball1.getY(), quadrado.ball1.getSize(), quadrado.ball1.getColor());
        drawBolinha(quadrado.ball2.getX(), quadrado.ball2.getY(), quadrado.ball2.getSize(), quadrado.ball2.getColor());
        drawBolinha(quadrado.ball3.getX(), quadrado.ball3.getY(), quadrado.ball3.getSize(), quadrado.ball3.getColor());
        drawBolinha(quadrado.ball4.getX(), quadrado.ball4.getY(), quadrado.ball4.getSize(), quadrado.ball4.getColor());

        drawMolinha(quadrado.Spring1.getX1(), quadrado.Spring1.getY1(), quadrado.Spring1.getX2(), quadrado.Spring1.getY2(), Color.BLACK);
        drawMolinha(quadrado.Spring2.getX1(), quadrado.Spring2.getY1(), quadrado.Spring2.getX2(), quadrado.Spring2.getY2(), Color.BLACK);
        drawMolinha(quadrado.Spring3.getX1(), quadrado.Spring3.getY1(), quadrado.Spring3.getX2(), quadrado.Spring3.getY2(), Color.BLACK);
        drawMolinha(quadrado.Spring4.getX1(), quadrado.Spring4.getY1(), quadrado.Spring4.getX2(), quadrado.Spring4.getY2(), Color.BLACK);
        drawMolinha(quadrado.Spring5.getX1(), quadrado.Spring5.getY1(), quadrado.Spring5.getX2(), quadrado.Spring5.getY2(), Color.BLACK);
        drawMolinha(quadrado.Spring6.getX1(), quadrado.Spring6.getY1(), quadrado.Spring6.getX2(), quadrado.Spring6.getY2(), Color.BLACK);
    }
}