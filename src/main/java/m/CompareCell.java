package m;

import java.util.Comparator;

public class CompareCell implements Comparator<Cellule> {
    //compare l'heuristique
    //comment foutre l'heuristique avec les cellules
    @Override
    public int compare(Cellule o1, Cellule o2) {
        if(o1.getHeuristique() < o2.getHeuristique()){
            return 1;
        }else {
            return -1;
        }

    }

    public CompareCell() {
        super();
    }
}
