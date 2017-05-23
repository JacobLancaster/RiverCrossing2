public class Plank extends Tile{
    
    private char direction;

    public Plank(int x, int y, char direction, Game game){
        super(4, game, x, y);//Calling the super class (parent class)
        if(direction == 'H'){//If plank is horizontal, change the image to look horizontal
            changeTile(2);
        }
    }

}