/*
 * File: BlankKarel.java
 * ---------------------
 * This class is a blank one that you can change at will.
 */

import stanford.karel.*;


public class Homework extends SuperKarel {
    int steps, xDimension,yDimension;
    // counting steps while putting beepers
    public void puttingBeeper(){
        move();
        if (!beepersPresent()){
            putBeeper();
        }
        steps++;
    }
    // counting steps while moving
    public void moving(){
        move();
        steps++;
    }

    // function for counting the y dimension
    public void getYDimension(){
        if (xDimension==1 || xDimension==2){
            while(frontIsClear()) {
                moving();
                yDimension++;
            }
            yDimension++;
            return;
        }
        while(frontIsClear()){
            puttingBeeper();
            yDimension++;
        }
        yDimension++;


    }
    // function for counting the x dimension
    public void getXDimension(){
        if (!frontIsClear()){
            xDimension=1;
            turnLeft();
            return;
        }
        while(frontIsClear()){
            moving();
            xDimension++;
        }
        xDimension++;
        turnAround();
        if (xDimension%2 == 0){
            for (int i=0;i<xDimension/2 -1;i++){
                moving();
            }
        }
        else {
            for (int i=0;i<xDimension/2;i++){
                moving();
            }
        }
        turnRight();
        if (!beepersPresent()&&xDimension!=2){
            putBeeper();
        }
    }
    public void checkXDimension(){

        // not executing the code if the matrix is 2X2
        if (xDimension==2 && yDimension==2){
            return;
        }
        if (xDimension%2==0){
            turnLeft();
            if (xDimension != 2)
                puttingBeeper();
            turnLeft();
        }
        else {
            turnAround();
        }
    }
    public void evenYDimension() {
        if (xDimension==1){
            for (int i=0;i<yDimension/2-2;i++){
                moving();
            }
            puttingBeeper();
            puttingBeeper();
            return;
        }

        for (int i = 0; i < yDimension / 2 - 1; i++) {
            if (xDimension==2){
                moving();
                continue;
            }
            puttingBeeper();
        }
        if (yDimension == 2) {
            puttingBeeper();
        } else {
            turnRight();
            while (frontIsClear()) {
                puttingBeeper();
            }
            turnLeft();
            puttingBeeper();
            turnLeft();
            while (frontIsClear()) {
                puttingBeeper();
            }
            turnLeft();
            puttingBeeper();
            turnLeft();

            for (int i = 0; i < xDimension / 2; i++) {

                puttingBeeper();
            }
            if (xDimension%2!=0){
                return;
            }
            turnLeft();
            while (frontIsClear()) {
                if (xDimension==2){
                    moving();
                    continue;
                }
                puttingBeeper();
            }
        }
    }
    public void oddYDimension(){
        if (xDimension%2==0){
            for (int i=0;i<yDimension/2;i++){
                if (xDimension==2){
                    moving();
                    continue;
                }
                puttingBeeper();
            }
        }
        else {
            for (int i=0;i<yDimension/2;i++){
                moving();
            }
        }

        if (xDimension==1){
            putBeeper();
            return;
        }
        turnRight();
        while (frontIsClear()){
            puttingBeeper();
        }
        turnAround();
        while (frontIsClear()){
            puttingBeeper();
        }
        if (xDimension%2==0) {
            turnAround();
            for (int i=0;i<xDimension/2;i++){
                moving();
            }
            turnLeft();
            while (frontIsClear()){
                if (xDimension==2){
                    moving();
                    continue;
                }
                puttingBeeper();
            }

        }
    }
    public void backToOrigin(){
        // special case if the matrix was 2X2
        if (xDimension==2 && yDimension==2){

            turnAround();
            while (frontIsClear()){
                if (beepersPresent())
                    pickBeeper();
                moving();
            }
            if (beepersPresent())
                pickBeeper();
            turnRight();
            while (frontIsClear()){
                if (beepersPresent())
                    pickBeeper();
                moving();
            }

            return;
        }
        if (facingNorth()||facingSouth()){
            turnRight();
        }
        else if (facingEast()){
            turnAround();
        }
        while (frontIsClear()){
            moving();
        }
        if (leftIsClear()){
            turnLeft();
            while (frontIsClear()){
                moving();
            }
        }
    }
    public void checkYDimension(){
        if (yDimension==1){
            return;
        }
        if (xDimension==2 && yDimension==2){
            return;
        }
        if (yDimension%2==0){
            evenYDimension();
        }
        else {
            oddYDimension();
        }
    }
    public void run() {
    }
}
