package m;

import java.io.Serializable;

public class Végétal extends Element implements Serializable {
    protected int bonus;

    public Végétal(int bonus) {
        this.bonus = bonus;
    }

}
