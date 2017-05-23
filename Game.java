import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Game{

    //Static variables
    private final int GRID_WIDTH = 9;
    private final int GRID_HEIGHT = 13;
    private final int WIDTH = GRID_WIDTH*32;
    private final int HEIGHT = GRID_HEIGHT*32;
    

    private JFrame jFrame;
    private JPanel jPanel;
    
    private Tile[][] grid = new Tile[GRID_HEIGHT][GRID_WIDTH];
    private Level[] levels = new Level[1];

    public Game(String title){
        
        //Configuring the JFrame
        jFrame = new JFrame(title);
        jFrame.setSize(WIDTH, HEIGHT);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Configuring the JPanel
        jPanel = new JPanel();
        jPanel.setLayout(new GridLayout(GRID_HEIGHT, GRID_WIDTH));
        
        jFrame.setContentPane(jPanel);
        
        //Making levels
        Level level1 = new Level();
        Tile start = new Tile(8, this, 2, 12);
        start.setStart();
        level1.addGameObject(start);
        level1.addGameObject(new Tile(6, this, 2, 8));
        level1.addGameObject(new Tile(6, this, 2, 6));
        level1.addGameObject(new Tile(6, this, 6, 6));
        level1.addGameObject(new Tile(6, this, 6, 4));
        level1.addGameObject(new Tile(10, this, 6, 0));
        //Planks
        level1.addGameObject(new Plank(2, 11, 'V', this));
        level1.addGameObject(new Plank(2, 10, 'V', this));
        level1.addGameObject(new Plank(2, 9, 'V', this));
        level1.addGameObject(new Plank(2, 7, 'V', this));
        levels[0] = level1;
        
        //Initialising grid
        //Top grassy bits
        for(int i = 0; i<GRID_WIDTH; i++){
            grid[0][i] = new Tile(1, this, i, 0);
        }
        
        //Water
        for(int i = 1; i<GRID_HEIGHT - 1; i++){
            for(int j = 0; j<GRID_WIDTH; j++){
                grid[i][j] = new Tile(12, this, j, i);
            }
        }
        
        //Bottom grassy bits
        for(int i = 0; i<GRID_WIDTH; i++){
            grid[GRID_HEIGHT - 1][i] = new Tile(0, this, i, GRID_HEIGHT - 1);
        }
        
        setLevel(1);
        
        draw();
        
    }

    //When a new level starts, call this function to set it up in game
    public void setLevel(int level){
         List<Tile> gameObjects = levels[level-1].getGameObjects();
         for(Tile t : gameObjects){
             grid[t.getY()][t.getX()] = new Tile(t.getTileNumber(), this, t.getX(), t.getY());
             grid[t.getY()][t.getX()].setPlayer(t.isPlayer());
         }
    }
    
    //Draws stuff to screen
    public void draw(){
        
        for(int i = 0; i<GRID_HEIGHT; i++){
            for(int j = 0; j<GRID_WIDTH; j++){
                jPanel.add(grid[i][j].getTile());
            }
        }
        
        jFrame.setVisible(true);
    }
    
    //Move the player
    public void movePlayer(int x, int y){
        //max, min x and y is used to check boundaries so we don't try and check a tile that doesn't exist
        int minX = x;
        if(x > 0){
            minX = x - 1;
        }
        
        int minY = y;
        if(y > 0){
            minY = y - 1;
        }
        
        int maxX = x;
        if(x < GRID_WIDTH - 1){
            maxX = x + 1;
        }
        
        int maxY = y;
        if(y < GRID_HEIGHT - 1){
            maxY = y + 1;
        }
        
        //Check around to see if player is on an adjacent tile
        for(int i=minY; i<=maxY; i++){
            for(int j=minX; j<=maxX; j++){
                if(grid[i][j].isPlayer()){
                    grid[i][j].setPlayer(false);
                    grid[y][x].setPlayer(true);
                }
            }
        }
    }

}