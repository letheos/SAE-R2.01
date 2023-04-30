import java.io.Serializable;

public class Animaux implements Serializable {
    private int portée;
    private int x;
    private int y;


    public Animaux(int x ,int y){
        this.portée = 4;
        this.x =x;
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void errer(){

    }

}
