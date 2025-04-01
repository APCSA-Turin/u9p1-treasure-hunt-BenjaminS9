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
        initialize();
        play();
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
            
            if (player.getWin()) {
                grid.win();
                break;
            }
            if (player.getLives() == 0) {
                grid.gameover();
                break;
            }
            
            grid.display();
            System.out.println("Player Coords: " + player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Lives left: " + player.getLives());
            System.out.println("Treasures obtained: " + player.getTreasureCount());
            System.out.print("Enter your movement: ");
            String movement = scanner.nextLine();
            if (player.isValid(size, movement)) {
                player.move(movement);
                trackPlayer();
                player.interact(size, movement, treasures.length, grid.getGrid()[size - 1 - player.getY()][player.getX()]);
                grid.placeSprite(player, movement);
            }
        }
            
     
    }

    public void initialize(){
        grid = new Grid(8);
        player = new Player(0, 0);
        trophy = new Trophy(0, 7);
        Treasure treasure1 = new Treasure(4,5);
        Treasure treasure2 = new Treasure(7, 7);
        Enemy enemy1 = new Enemy(6,6);
        Enemy enemy2 = new Enemy(4,4);
    

        grid.placeSprite(player);
        grid.placeSprite(trophy);
        grid.placeSprite(treasure1);
        grid.placeSprite(treasure2);
        treasures = new Treasure[2];
        treasures[0] = treasure1;
        treasures[1] = treasure2;
        grid.placeSprite(enemy1);
        grid.placeSprite(enemy2);
        enemies = new Enemy[2];
        enemies[0] = enemy1;
        enemies[1] = enemy2;

        grid.display();
        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
   
    }

    public void trackPlayer() {
        for (Enemy enemy : enemies) {
            if (enemy.getX() > player.getX()) {
                enemy.move("a");
                grid.placeSprite(enemy, "a");
            } else if (enemy.getX() < player.getX()) {
                enemy.move("d");
                grid.placeSprite(enemy, "d");
            } else {
                if (enemy.getY() > player.getY()) {
                    enemy.move("s");
                    grid.placeSprite(enemy, "s");
                } else {
                    enemy.move("w");
                    grid.placeSprite(enemy, "w");
                }
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game(8);
    }
}