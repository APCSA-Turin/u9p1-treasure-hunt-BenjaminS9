package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    
    public Enemy(int x, int y) {
        super(x, y); //initializes an enemy with x and y parameters specified in the superclass
    }

    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords(); //calls the getCoords method from the superclass to print the x and y values in a string
    }


    public String getRowCol(int size){ //return "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size); //calls the getRowCol method from the superclass to print the current row and column index of the enemy
    }
}