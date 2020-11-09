import javafx.util.Pair;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class PIF {
    private List<Pair<String, Integer>> pairs;

    public PIF () {
        pairs = new ArrayList<>();
    }

    public List<Pair<String, Integer>> getPairs() {
        return pairs;
    }

    public void setPairs(List<Pair<String, Integer>> pairs) {
        this.pairs = pairs;
    }

    public void genPIF(String token, int id) {
        Pair<String, Integer> p = new Pair<>(token, id);
        pairs.add(p);
    }

    @Override
    public String toString() {
        return "PIF{" +
                "pairs=" + pairs +
                '}';
    }

    public void writeFile() throws IOException {
        //File file= new File("E:\\Mara\\Faculta\\an III\\Semester I\\FLCD\\labs\\lab3\\src\\PIF.out");
        FileWriter fileWriter = new FileWriter("PIF.out");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Pair<String, Integer> p : pairs) {
            printWriter.print(p.getKey() + " -- " + p.getValue() + "\n");
        }
        printWriter.close();
    }
}
