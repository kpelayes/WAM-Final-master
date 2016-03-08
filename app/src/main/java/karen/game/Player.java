package karen.game;

/**
 * Created by Myles Gray on 3/6/2016.
 */
public class Player {
    String name;
    int hscore;

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }

    public void setHscore(int hscore)
    {
        this.hscore = hscore;
    }
    public int getHscore()
    {
        return this.hscore;
    }
}
