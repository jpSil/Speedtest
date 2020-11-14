package org.jpsil.speedtest;

import javafx.application.Platform;

import java.util.Random;

public class NewGameThread extends Thread {

    private GameButton redButton;
    private GameButton blueButton;
    private GameButton yellowButton;
    private GameButton greenButton;
    private Random random = new Random();
    private long interval;
    private boolean stopRun = false;

    public NewGameThread(GameButton redButton, GameButton blueButton,
                         GameButton yellowButton, GameButton greenButton, long interval) {

        this.redButton = redButton;
        this.blueButton = blueButton;
        this.yellowButton = yellowButton;
        this.greenButton = greenButton;
        this.interval = interval;
    }

    public void run() {
        while (!stopRun) {
            int num = random.nextInt(4) + 1;
            if(num == 1 && !redButton.isButtonOn()) {
                redButton.turnButtonOn();
            }
            if(num == 2 && !blueButton.isButtonOn()) {
                blueButton.turnButtonOn();
            }
            if(num == 3 && !yellowButton.isButtonOn()) {
                yellowButton.turnButtonOn();
            }
            if(num == 4 && !greenButton.isButtonOn()) {
                greenButton.turnButtonOn();
            }
            System.out.println(num);
            try {
                Thread.sleep(interval);
            } catch(InterruptedException ie) {
                //ie.printStackTrace();
            }
            if(redButton.isButtonOn()) {
                redButton.turnButtonOff();
            }
            if(blueButton.isButtonOn()) {
                blueButton.turnButtonOff();
            }
            if(yellowButton.isButtonOn()) {
                yellowButton.turnButtonOff();
            }
            if(greenButton.isButtonOn()) {
                greenButton.turnButtonOff();
            }
        }
    }

    public void setStopRun(boolean stopRun) {
        this.stopRun = true;
    }

}
