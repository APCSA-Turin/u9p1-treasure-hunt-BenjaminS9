package com.example.project;

public class Sprite {
    private int x, y;

    public Sprite(int x, int y) { //initiates the sprite class with an x and y value on the cartesian plane
        this.x = x;
        this.y = y;
    }

    public int getX(){return x;} //returns the x value on the Cartesian plane
    public int getY(){return y;} //returns the y value on the Cartesian plane

    public void setX(int newX){x = newX;} //sets a new x value on the Cartesian plane
    public void setY(int newY){y = newY;} //sets a new y value on the Cartesian plane

    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "(" + x + "," + y + ")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        //the value for row is calculated using the size of the grid minus one subtracted by the value of y on the Cartesian plane
        return "[" + (size - 1 - y) + "][" + x + "]"; //the value for column is the same as x on the Cartesian plane
    }
    

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
