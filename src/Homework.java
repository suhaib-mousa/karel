/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;


public class Homework extends SuperKarel {
    private int steps = 1;
    private int xAxis = 0;
    private int yAxis = 0;

    private boolean isValidXAxis() {
        return xAxis != 1 && xAxis != 2;
    }

    private boolean isValidYAxis() {
        return yAxis != 1 && yAxis != 2;
    }
    private void moveAndPlaceBeeperInYAxis() {
        step();
        if (isValidXAxis() && !beepersPresent()) {
            putBeeper();
        }
    }
    private void moveAndPlaceBeeperInXAxis() {
        step();
        if (isValidYAxis() && !beepersPresent()) {
            putBeeper();
        }
    }
    private void step() {
        move();
        steps++;
    }

    public void run() {
        initializeCounters();
        startMission();
        returnToOrigin();
        System.out.println("The steps count: " + steps);
    }

    private void initializeCounters() {
        steps = 1; // count steps, initialized with 1 to count first step
        xAxis= 0; // count x units
        yAxis =0; // count y units
    }

    private void startMission() {
        discoverXAxis(); // count the x-axis then back to origin depending on even or odd
        discoverYAxis(); // count the y-axis

        placeBeepersInYAxis();
        placeBeepersInXAxis();

        while (frontIsClear()) {
            moveAndPlaceBeeperInYAxis();
        }
    }

    private void placeBeepersInXAxis() {
        if (!isValidYAxis()){
            return;
        }
        turnLeft();
        while (frontIsClear()){
            moveAndPlaceBeeperInXAxis();
        }
        turnRight();

        if (yAxis % 2 == 0) {
            moveAndPlaceBeeperInXAxis();
        }
        turnRight();


        while (frontIsClear()){
            moveAndPlaceBeeperInXAxis();
        }

        turnRight();
        if (yAxis % 2 == 0) {
            moveAndPlaceBeeperInXAxis();
        }
        turnRight();

        int middleOfX = xAxis % 2 == 0 ? xAxis / 2 - 1 : xAxis / 2;
        for (int i = 1; i <= middleOfX; i++){
            moveAndPlaceBeeperInXAxis();
        }
        turnRight();
    }
    private void placeBeepersInYAxis() {
        int middleOfY = yAxis % 2 == 0 ? yAxis / 2 - 1 : yAxis / 2;
        turnLeft();

        // special case
        if (!isValidXAxis()) {
            turnLeft();
            //back to the middle - 1
            for (int i = 1; i < middleOfY; i++) {
                step();
            }
            moveAndPlaceBeeperInXAxis();
            return;
        }

        if (xAxis % 2 == 0) {
            moveAndPlaceBeeperInYAxis();
        }
        turnLeft();
        for (int i = 1; i <= middleOfY; i++) {
            moveAndPlaceBeeperInYAxis();
        }
    }
    private  void  discoverYAxis() {
        yAxis++; // count the first step where the karl starting the mission
        while (frontIsClear()){
            moveAndPlaceBeeperInYAxis();
            yAxis++;
        }
    }
    private void discoverXAxis() {
        xAxis++; // count the first step where karl stands
        while (frontIsClear()){
            step();
            xAxis++;
        }
        moveToStartPoint();
    }
    private void moveToStartPoint() {
        turnAround();
        int startPoint = xAxis % 2 == 0 ? xAxis / 2 - 1: xAxis / 2;
        for (int i = 1; i < startPoint; i++){
            step();
        }
        if (startPoint > 0) { // to ensure karl can step
            moveAndPlaceBeeperInYAxis();
        }
        turnRight();
    }

    private void returnToOrigin() {
        turnRight();
        while (frontIsClear()){
            step();
        }
        turnRight();
        turnRight();
    }
}
