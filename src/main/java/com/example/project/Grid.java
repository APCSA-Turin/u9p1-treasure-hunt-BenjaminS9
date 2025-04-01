package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        grid = new Sprite[size][size];
        this.size = size;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Dot(row, col);
            }
        }
    }

    public int getSize(){return size;}
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
        grid[size - s.getY() - 1][s.getX()] = s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
        if (direction.equals("w")) {
            placeSprite(s);
            grid[size - s.getY()][s.getX()] = new Dot(s.getX(), s.getY());
        } else if (direction.equals("a")) {
            placeSprite(s);
            grid[size - s.getY() - 1][s.getX() + 1] = new Dot(s.getX(), s.getY());
        } else if (direction.equals("s")) {
            placeSprite(s);
            grid[size - s.getY() - 2][s.getX()] = new Dot(s.getX(), s.getY());
        } else if (direction.equals("d")) {
            placeSprite(s);
            grid[size - s.getY() - 1][s.getX() - 1] = new Dot(s.getX(), s.getY());
        }
    }


    public void display() { //print out the current grid to the screen
        for (Sprite[] row : grid) {
            for (Sprite col : row) {
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
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print("❌");
            }
            System.out.println();
        }
        System.out.println("You lost...");
    }

    public void win(){ //use this method to display a win 
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.print("✅");
            }
            System.out.println();
        }
        System.out.println("You won!");
    }


}