package GoT.DnD.Business_Layer;

import GoT.DnD.Persistent_Layer.ReadText;
import sun.awt.image.ImageWatched;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class Board {

    //Fields
    private static Character[][] board;
    private LinkedList<GameUnit> GameUnits;
    Player Hero;

    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char HERO = '@';

    //Constructor
    public Board(String level, Player Hero){
        int x=0;
        int y=0;
        List<String> boardScheme = ReadText.readAllLines(level);
        board = new Character[boardScheme.size()][boardScheme.get(1).length()]; //Initialize board in the required dimension
        GameUnits = BoardSchemeParser.ParseScheme(boardScheme,Hero);
        this.Hero=(Player)GameUnits.getFirst();
        GameUnits.removeFirst();
        for (String line:boardScheme) {
            for (char tile:line.toCharArray()) {
                board[x][y]=tile;
                x++;
            }
            y++;
        }
    }

    //Methods
    public LinkedList<GameUnit> getGameUnits() {
        return GameUnits;
    }
    public LinkedList<GameUnit> NearbyGameUnits(GameUnit gameunit, Double radius) {
        LinkedList<GameUnit> allCreatures = this.getGameUnits();
        LinkedList<GameUnit> nearbyCreatures = new LinkedList<GameUnit>();
        for (GameUnit creature : allCreatures) {
            if (range(gameunit, creature) <= radius) {
                nearbyCreatures.addLast(creature);
            }

        }
        return nearbyCreatures;
    }
    public Double rangeToHero(GameUnit gameUnit){
        return  range(Hero,gameUnit);
    }
    public static Double range(GameUnit gameunit1, GameUnit gameunit2){
        return gameunit1.getPosition().distance(gameunit2.getPosition());
    }
    public static boolean isLegalMove(Point pos, int move){
        Point p = new Point(pos.x, pos.y);
        Character gu = null;
        switch (move){
            case 1:
                gu = board[p.x][p.y+1];
                break;
            case 2:
                gu = board[p.x][p.y-1];
                break;
            case 3:
                gu = board[p.x+1][p.y];
                break;
            case 4:
                gu = board[p.x-1][p.y];
                break;
        }
        if (gu == EMPTY){
            return true;
        } else if(gu != WALL && gu != HERO) {
            return false;
        } else if (gu == HERO){
            //TODO: Engage combat
        } else
            return false;

    }

    public void MoveHero(int direction){
        MoveGameUnit(Hero,direction);
    }
    public void MoveGameUnit(GameUnit gameunit,int direction){
        gameunit.Move(direction);
    }

    private List<Point> getEmptyPlaces(){
        List<Point> emptySpots = new LinkedList<>();
        for(int x=0;x<board.length;x++){
            for(int y=0;y<board[x].length;y++){
                if(board[x][y]==EMPTY){
                    emptySpots.add(new Point(x,y));
                }
            }
        }
        return emptySpots;
    }
    public void gameTick(){
        for(GameUnit gu:GameUnits){
            if(gu.GameUnitType().equals("Trap")){
                gu.gameTick(getEmptyPlaces());
            }
            else{
                gu.gameTick();
            }
        }
    }




}


