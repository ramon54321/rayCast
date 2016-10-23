import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Ramon Brand on 20-Oct-16.
 */
public class Tile implements Serializable{

    public int x = 3;
    public int y = 4;
    private String name = "The name of the object";

    public Tile(String name){
        this.name = name;
    }

    public int incrementX(){
        this.x++;
        return this.x;
    }

    public String getName() {
        return name;
    }

}
