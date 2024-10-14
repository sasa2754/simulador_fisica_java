package com.desktopapp;

public class Mola {
    
    protected Mass bola1;
    protected Mass bola2;

    protected Mola(Mass bola1, Mass bola2){
        this.bola1 = bola1;
        this.bola2 = bola2;
    }

    private void LinhaMola(Mass bola1, Mass bola2) {
        var x1 = bola1.getX() + 25;
        var y1 = bola1.getY() + 25;

        var x2 = bola2.getX() + 25;
        var y2 = bola2.getY() + 25;
    }
} 
