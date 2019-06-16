package main.utilities;



import java.util.ArrayList;
import java.util.List;

public class Data {

    public static List<String> allNiveaux= new ArrayList<>();

    static public List<String> getAllNiveaux() {
        allNiveaux.add("L1");
        allNiveaux.add("L2");
        allNiveaux.add("L3");
        allNiveaux.add("M1");
        allNiveaux.add("M2");
        return allNiveaux;
    }
}
