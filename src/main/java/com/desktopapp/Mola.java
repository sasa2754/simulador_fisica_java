package com.desktopapp;

public class Mola {
    
    protected Mass bola1, bola2;
    protected Double x1, y1, x2, y2, midW, power;

    public Double getPower() {
        return power;
    }

    public void setPower(Double power) {
        this.power = power;
    }

    public Double getMidW() {
        return midW;
    }

    public void setMidW(Double midW) {
        this.midW = midW;
    }

    public Double getX1() {
        return x1;
    }

    public void setX1(Double x1) {
        this.x1 = x1;
    }

    public Double getY1() {
        return y1;
    }

    public void setY1(Double y1) {
        this.y1 = y1;
    }

    public Double getX2() {
        return x2;
    }

    public void setX2(Double x2) {
        this.x2 = x2;
    }

    public Double getY2() {
        return y2;
    }

    public void setY2(Double y2) {
        this.y2 = y2;
    }

    public Mass getBola1() {
        return bola1;
    }

    public void setBola1(Mass bola1) {
        this.bola1 = bola1;
    }

    public Mass getBola2() {
        return bola2;
    }

    public void setBola2(Mass bola2) {
        this.bola2 = bola2;
    }

    protected Mola(Mass bola1, Mass bola2){
        this.bola1 = bola1;
        this.bola2 = bola2;
        this.midW = 200d;
    }

    public void springPhysics(){
        setX1(getBola1().getX() + getBola1().getSize() / 2);
        setY1(getBola1().getY() + getBola1().getSize() / 2);

        setX2(getBola2().getX() + getBola2().getSize() / 2);
        setY2(getBola2().getY() + getBola2().getSize() / 2);

        Double Y = getBola1().getY() - getBola2().getY();
        Double X = getBola1().getX() - getBola2().getX();

        Double hyp = Math.hypot(getBola1().getY() - getBola2().getY(), getBola1().getX() - getBola2().getX());

        setPower((hyp - midW) * 0.09);

        Double weightSum = getBola1().getKg() + getBola2().getKg();

        Double powerVelBall1 = getBola1().getKg() / weightSum * getPower();
        Double powerVelBall2 = getBola2().getKg() / weightSum * getPower();
        Double DisSum = Math.abs(Y) + Math.abs(X);
        Double powerVelBall1x = powerVelBall1 * (Math.abs(X) / DisSum);
        Double powerVelBall1y = powerVelBall1 * (Math.abs(Y) / DisSum);
        Double powerVelBall2x = powerVelBall2 * (Math.abs(X) / DisSum);
        Double powerVelBall2y = powerVelBall2 * (Math.abs(Y) / DisSum);
        
        if (getBola1().getX() < getBola2().getX()) {
            getBola1().setVx(powerVelBall1x + getBola1().getVx());
            getBola1().setVy(powerVelBall1y + getBola1().getVy());
            getBola2().setVx(-(powerVelBall2x) + getBola2().getVx());
            getBola2().setVy(-(powerVelBall2y) + getBola2().getVy());
        } else {
            getBola1().setVx(-(powerVelBall1x) + getBola1().getVx());
            getBola1().setVy(-(powerVelBall1y) + getBola1().getVy());
            getBola2().setVx(powerVelBall2x + getBola2().getVx());
            getBola2().setVy(powerVelBall2y + getBola2().getVy());
        }
    }
} 
