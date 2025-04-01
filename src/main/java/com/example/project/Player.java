package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2
        super(x, y);
        treasureCount = 0;
        numLives = 2; 
    }


    public int getTreasureCount(){return treasureCount;} //returns the amount of treasures obtained
    public int getLives(){return numLives;} //returns the amount of lives remaining
    public boolean getWin(){return win;} //returns whether the player has won
    public String getRowCol(int size) { //returns the location of the player on the 2D array
        return "Player:" + super.getRowCol(size);
    }
    public String getCoords() { //returns the location of the player on the Cartesian plane
        return "Player:" + super.getCoords();
    }
    public void setLives(int newLives) { //sets the players lives to newLives, used for different difficulties
        numLives = newLives;
    }

  
    //move method should override parent class, sprite
    public void move(String direction) { //move the (x,y) coordinates of the player
        if (direction.equals("w")) { 
            setY(getY() + 1); //moves player up by one on the Cartesian plane
        } else if (direction.equals("a")) {
            setX(getX() - 1); //moves player left by one on the Cartesian plane
        } else if (direction.equals("s")) {
            setY(getY() - 1); //moves player down by one on the Cartesian plane
        } else if (direction.equals("d")) {
            setX(getX() + 1); //moves player right by one on the Cartesian plane
        }
    }


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
        if (obj instanceof Enemy) { //checks if the player touched an enemy
            numLives--; //loses a life
            if (numLives == 0) { //upon reaching 0 lives, the player loses
                win = false; 
            }
        } else if (obj instanceof Trophy) { //checks if there was an interaction with the trophy
            if (treasureCount >= numTreasures) { //checks if the player has first collected all the treasures
                win = true; //if the player has all treasures, they win
            } else { //moves the player back one space to account for the one space they will move forward(prevents player from moving onto the trophy space without enough treasures)
                if (direction.equals("w")) { //if they are coming from below the trophy, moves them down one
                    move("s");
                } else { //if they are coming from the left of the trophy, moves them right
                    move("d");
                }
            }
        } else if(obj instanceof Treasure) { //checks if the player touched a treasure
            treasureCount++; //increases the amount of treasures obtained
        }
    }


    public boolean isValid(int size, String direction){ //check grid boundaries
        if (direction.equals("w")) {
            if (getY() + 1 >= size) { //player cannot move to a greater index than the size of the array
                return false;
            }
        } else if (direction.equals("a")) {
            if (getX() - 1 < 0) { //player cannot move to a lower index than 0
                return false;
            }
        } else if (direction.equals("s")) {
            if (getY() - 1 < 0) { //player cannot move to a lower index than 0
                return false;
            }
        } else if (direction.equals("d")) {
            if (getX() + 1 >= size) { //player cannot move to a greater index than the size of the array
                return false;
            }
        }
        return true; //if the move is in bounds of the array, returns true
    }


   
}



