package com.desktopapp;

import javafx.scene.paint.Color;

public class Quadrado {
    
    protected Mass ball1, ball2, ball3, ball4;
    protected Mola Spring1, Spring2, Spring3, Spring4, Spring5, Spring6;
    protected Double gravityFalls = 3d, C = 0.4;

    public Quadrado(Double kg, Double x, Double y, Double Vx, Double Vy, Double canvaHeight, Double canvaWidth, Double size, Double midw, Color color){

        ball1 = new Mass(kg, x, y, Vx, Vy, canvaHeight, canvaWidth, size, color);
        ball2 = new Mass(kg, x + (midw * 2) , y, Vx, Vy, canvaHeight, canvaWidth, size, color);
        ball3 = new Mass(kg, x, y + (midw * 2), Vx, Vy, canvaHeight, canvaWidth, size, color);
        ball4 = new Mass(kg, x + (midw * 2), y + (midw * 2), Vx, Vy, canvaHeight, canvaWidth, size, color);

        Spring1 =  new Mola(ball1, ball2);
        Spring2 =  new Mola(ball1, ball3);
        Spring3 =  new Mola(ball1, ball4);
        Spring4 =  new Mola(ball2, ball3);
        Spring5 =  new Mola(ball2, ball4);
        Spring6 =  new Mola(ball3, ball4);
    }

    public void squarePhysics(Double canvaHeight, Double canvaWidth){
        ball1.ballPhysics(gravityFalls, C, canvaHeight, canvaWidth);
        ball2.ballPhysics(gravityFalls, C, canvaHeight, canvaWidth);
        ball3.ballPhysics(gravityFalls, C, canvaHeight, canvaWidth);
        ball4.ballPhysics(gravityFalls, C, canvaHeight, canvaWidth);

        Spring1.springPhysics();
        Spring2.springPhysics();
        Spring3.springPhysics();
        Spring4.springPhysics();
        Spring5.springPhysics();
        Spring6.springPhysics();
    }
}
