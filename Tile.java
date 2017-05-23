import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Tile{
    
    private final String[] IMAGE_NAMES;
    
    
    private int tileNumber;
    private JButton jButton;
    private ImageIcon image;
    private Game game;
    
    private int x;
    private int y;
    
    private boolean hasPlayer = false;
    

    public Tile(int tileNumber, Game game, int x, int y){
        IMAGE_NAMES = new String[] {"bank1", "bank2", "plank1", "plank1_man", "plank2", "plank2_man", "stump1", "stump1_man", "stump2", "stump2_man", "stump3", "stump3_man", "water1", "water2", "water3", "water4"};
        
        this.tileNumber = tileNumber;
        this.game = game;
        this.x = x;
        this.y = y;
        image = new ImageIcon("images/" + IMAGE_NAMES[tileNumber] + ".jpg");
        jButton = new JButton(image);
        jButton.setBorder(BorderFactory.createEmptyBorder());//Get rid of borders on the jButton
        
        //when the button is clicked, this action is called
        jButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(tileNumber >= 2 && tileNumber <= 10 && tileNumber % 2 == 0){//Only move player to a valid tile e.g. plank, stump
                    game.movePlayer(x, y);
                }
                
            }
        });
    
    }
    
    public JButton getTile(){
        return jButton;
    }
    
    public int getTileNumber(){
        return tileNumber;
    }
    
    //Change the image on the tile
    public void changeTile(int tileNumber){
        this.tileNumber = tileNumber;
        image = new ImageIcon("images/" + IMAGE_NAMES[tileNumber] + ".jpg");
        jButton.setIcon(image);
    }
    
    //Call this to tell the tile whether or not a player is on it
    public void setPlayer(boolean isPlayer){
        if(hasPlayer != isPlayer){
            hasPlayer = isPlayer;
            if(isPlayer){
                changeTile(tileNumber + 1);
            }else{
                changeTile(tileNumber - 1);
            }
        }
    }
    
    public void setStart(){
        hasPlayer = true;
    }
    
    public boolean isPlayer(){
        return hasPlayer;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }

}