package GoT.DnD.Business_Layer;

public abstract class Enemy extends GameUnit {
    private Integer xp;         //Experience value
    private char tile;

    public Enemy(String name, Integer hp, Integer currHP, Integer ap, Integer dp, Integer[] position, Integer xp, char tile){
        super(name, hp, currHP, ap, dp, position);
        this.xp = xp;
        this.tile = tile;
    }

}
