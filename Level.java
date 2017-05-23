import java.util.List;
import java.util.ArrayList;


public class Level{

    private List<Tile> gameObjects;//Holds all the objects in the level

    public Level(){
        gameObjects = new ArrayList<Tile>();
        
    }

    //Function used to add a new object to the level
    public void addGameObject(Tile g){
        gameObjects.add(g);
    }

    //Returns all the objects in the level
    public List getGameObjects(){
        return gameObjects;
    }
}