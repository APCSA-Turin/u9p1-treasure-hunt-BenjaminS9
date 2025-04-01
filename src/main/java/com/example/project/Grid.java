package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size]; //2d array of size size is created
        this.size = size;

        //assigns each index of the 2d array to be an Dot object
        for (int row = 0; row < grid.length; row++) { //iterates through each row
            for (int col = 0; col < grid[row].length; col++) { //iterates through each column
                grid[row][col] = new Dot(row, col);
            }
        }
    }

    public int getSize(){return size;} //returns the size of the array
    public Sprite[][] getGrid(){return grid;} //returns a reference to the 2d array



    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - s.getY() - 1][s.getX()] = s; //places a sprite onto the grid based on its current x and y values on the Cartesian plane
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        if (direction.equals("w")) { //on moving up
            placeSprite(s); //the sprite is placed one unit up
            grid[size - s.getY()][s.getX()] = new Dot(s.getX(), s.getY()); //a Dot object is created in its spot below
        } else if (direction.equals("a")) { //on moving left
            placeSprite(s); //the sprite is place one unit left
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX(), s.getY()); //a Dot object is created in its spot to the left
        } else if (direction.equals("s")) {//on moving down 
            placeSprite(s);//the sprite is placed one unit down
            grid[size - s.getY() - 2][s.getX()] = new Dot(s.getX(), s.getY()); //a Dot object is created in its spot above
        } else if (direction.equals("d")) { //on moving right
            placeSprite(s);//the sprite is placed one unit right
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX(), s.getY());//a Dot object is created in its spot to the right
        }
    }


    public void display() { //print out the current grid to the screen
        for (Sprite[] row : grid) { //iterates through each row
            for (Sprite col : row) { //itreates through each index in the row
                //prints out an emoji based on the class of the sprite
                if (col instanceof Dot) {
                    System.out.print("⬜");
                } else if (col instanceof Player) {
                    System.out.print("🚘");
                } else if (col instanceof Enemy) {
                    System.out.print("🚧");
                } else if (col instanceof Trophy) {
                    System.out.print("🏁");
                } else if (col instanceof Treasure) {
                    System.out.print("🚩");
                }
            }
            System.out.println();
        } 
    }
    
    public void gameover(){ //use this method to display a loss
        //changes the entire grid to a X emoji
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print("❌");
            }
            System.out.println();
        }
        System.out.println("You lost...");
    }

    public void win(){ //use this method to display a win 
        //changes the entire grid to a check emoji
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print("✅");
            }
            System.out.println();
        }
        System.out.println("You won!");
    }


}