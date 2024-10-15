package com.desktopapp;

import javafx.scene.paint.Color;

public class Mass {

    protected Double kg, Vx, Vy, x, y, canvaHeight, canvaWidth, size;

    public Double getCanvaHeight() {
        return canvaHeight;
    }

    public void setCanvaHeight(Double canvaHeight) {
        this.canvaHeight = canvaHeight;
    }

    public void setCanvaWidth(Double canvaWidth) {
        this.canvaWidth = canvaWidth;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    protected Color color;

    public Color getColor() {
        return color;
    }

    public Double getCanvaWidth() {
        return canvaWidth;
    }

    public Double getCanva() {
        return canvaHeight;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getVy() {
        return Vy;
    }

    public void setVy(Double vy) {
        Vy = vy;
    }

    public Double getVx() {
        return Vx;
    }

    public void setVx(Double vx) {
        Vx = vx;
    }

    public Double getKg() {
        return kg;
    }

    public void setKg(Double kg) {
        this.kg = kg;
    }

    public Mass(Double kg, Double x, Double y, Double Vx, Double Vy, Double canvaHeight, Double canvaWidth, Double size, Color color) {
        this.Vy = 0 + 10 * 0.016;
        this.Vx = Vx;
        this.x = x;
        this.y = y;
        this.kg = kg;
        this.canvaHeight = canvaHeight;
        this.canvaWidth = canvaWidth;
        this.color = color;
        this.size = size;
    }

    public void ballPhysics(Double gravityFalls, Double C, Double xSpeed, Double canvaHeight, Double canvaWidth){
        setVy(getVy() + gravityFalls * 1);
        setY(getY() + getVy());

        setX(getX() + getVx());

        if (getY() > canvaHeight - getSize()) {
            setVy(-(getVy() * C));
        }

        if (getY() > canvaHeight - getSize()) {
            setY(canvaHeight - getSize());
            setVx((getVx() * C));
        }

        if (getX() < 0 || getX() > canvaWidth - getSize()) {
            setVx(-getVx());
        }
    }
}