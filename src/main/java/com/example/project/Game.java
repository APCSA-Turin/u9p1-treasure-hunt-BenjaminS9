package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        this.size = size;
        initialize(size); //creates the starting grid
        play(); //allows the user to interact with the grid
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        Scanner scanner = new Scanner(System.in);

        while(true){
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clearScreen(); // Clear the screen at the beggining of the while loop
            
            if (player.getWin()) { //checks if the player has won
                grid.win(); //displays the win message
                break; //stops iterating through the while loop
            }
            if (player.getLives() == 0) { //checks if the player has lost(when their remaining lives is 0)
                grid.gameover(); //displays the loss message
                break; //stops iterating through the while loop
            }
            
            grid.display(); //displays the current board with sprites at their respective locations
            
            //Displays info about the location of the player, remaining lives, and treasures obtained
            System.out.println("Player Coords: " + player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Lives left: " + player.getLives());
            System.out.println("Treasures obtained: " + player.getTreasureCount());
            
            //prompts player for their move
            System.out.print("Enter your movement: ");
            String movement = scanner.nextLine();

            if (player.isValid(size, movement)) { //first checks that the direction of movement is not out of bounds, nothing happens if it is
                player.move(movement); //changes the values x or y depending on movement on the Cartesian plane
                player.interact(size, movement, treasures.length, grid.getGrid()[size - 1 - player.getY()][player.getX()]); //replaces the index it was on with an empty Dot object, does one of 4 things depending on the type of object it moved on
                grid.placeSprite(player, movement); //visually moves the player in the direction they chose on the grid
            }
        }
            
     
    }

    public void initialize(int size){ //creates a starting grid upon being called
        grid = new Grid(size); //creates a new grid of specified size
        player = new Player(0, 0); //player always starts in bottom left corner of grid
        if (size == 4) { //checks if the mode is easy
            player.setLives(1);
        } else if (size == 6) { //checks if the mode is medium
            player.setLives(2);
        } else { //occurs if the mode is hard
            player.setLives(1);
        }

        trophy = new Trophy(0, size - 1); //tropy is always at the top left corner
        
        //2 treasures and 1 enemy are guarenteed no matter the mode
        Treasure treasure1 = new Treasure(size - 3,size - 3);
        Treasure treasure2 = new Treasure(size - 1, 2);
        Enemy enemy1 = new Enemy(2, 2);
        
        //places all created objects onto the grid
        grid.placeSprite(player);
        grid.placeSprite(trophy);
        grid.placeSprite(treasure1);
        grid.placeSprite(treasure2);
        treasures = new Treasure[2];
        treasures[0] = treasure1;
        treasures[1] = treasure2;
        grid.placeSprite(enemy1);

        if (size >= 6) { //checks if the mode is medium or hard to add one more enemy
            Enemy enemy2 = new Enemy(4,4);
            grid.placeSprite(enemy2);
        }
        if (size == 8) { //checks if the mode is hard to add a third enemy
            Enemy enemy3 = new Enemy(7,6);
            grid.placeSprite(enemy3);
        }

        grid.display(); //prints the board out to be seen visually
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean playAgain = true;
        
        while (playAgain) { //repeats games until the player quits
            System.out.print("Insert your dificulty(easy/medium/hard): "); //promts user for difficulty
            String difficulty = scan.nextLine();
            if (difficulty.equals("hard")) { //on hard mode, a board of size 8 is created
                Game game = new Game(8);
            } else if (difficulty.equals("medium")) { //on medium mode, a board of size 6 is created
                Game game = new Game(6);
            } else if (difficulty.equals("easy")) { //on easy mode, a board of size 4 is created
                Game game = new Game(4);
            }

            //occurs after the game ends(win or loss)
            System.out.print("Play again?(y/n): "); //promts user to play again
            String response = scan.nextLine();
            if (response.equals("n")) { //on saying no, the game stops
                playAgain = false;
                System.out.println("Hope you had fun!");
            }
        }
    }
}