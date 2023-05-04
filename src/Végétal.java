import java.io.Serializable;
import java.util.Random;

public class Végétal extends Element implements Serializable {
    protected int bonus;

    public Végétal(int bonus) {
        this.bonus = bonus;
    }

}
