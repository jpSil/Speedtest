package org.jpsil.speedtest;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class GameButton extends Circle {

    private Color buttonColorOn;
    private Color buttonColorOff;
    private boolean buttonOn;

    public GameButton(Color buttonColorOn, Color buttonColorOff) {
        this.buttonColorOn = buttonColorOn;
        this.buttonColorOff = buttonColorOff;
        this.buttonOn = false;
        setRadius(50);
        setFill(buttonColorOff);
        setStroke(Color.BLACK);
        setStrokeWidth(5);
    }

    public void turnButtonOn() {
        this.buttonOn = true;
        this.setFill(buttonColorOn);
    }

    public void turnButtonOff() {
        this.buttonOn = false;
        this.setFill(buttonColorOff);
    }

    public Color getButtonColorOn() {
        return buttonColorOn;
    }

    public void setButtonColorOn(Color buttonColorOn) {
        this.buttonColorOn = buttonColorOn;
    }

    public Color getButtonColorOff() {
        return buttonColorOff;
    }

    public void setButtonColorOff(Color buttonColorOff) {
        this.buttonColorOff = buttonColorOff;
    }

    public boolean isButtonOn() {
        return buttonOn;
    }

    public void setButtonOn(boolean buttonOn) {
        this.buttonOn = buttonOn;
    }
}
