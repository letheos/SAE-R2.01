package m;
import java.util.Comparator;
public class CellTri implements Comparator<Cellule>{

    @Override
    public int compare(Cellule cell1, Cellule cell2) {
        if(cell1.getHeuriDep() > cell2.getHeuriDep()){
            return 1;
        }else{
            return -1;
        }
    }
}