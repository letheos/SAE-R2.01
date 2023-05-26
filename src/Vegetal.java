import java.io.Serializable;

public class Vegetal extends Element implements Serializable {
    protected int bonus;

    public Vegetal(int bonus) {
        this.bonus = bonus;
    }

}
