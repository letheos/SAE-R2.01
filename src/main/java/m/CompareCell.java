package m;

import java.util.Comparator;

public class CompareCell implements Comparator<Cellule> {
    //compare l'heuristique
    //comment foutre l'heuristique avec les cellules
    private Cellule fin;
    @Override
    public int compare(Cellule o1, Cellule o2) {
        //return o1.getHeuriDep(fin) - o2.getHeuriDep(fin);
        return o1.getHeuristique() - o2.getHeuristique();
    }

    public CompareCell(Cellule f) {
        super();
        this.fin = f;
    }
}
