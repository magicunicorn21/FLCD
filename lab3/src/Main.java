import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("E:\\Mara\\Faculta\\an III\\Semester I\\FLCD\\labs\\lab3\\src\\p2.txt");
        LexicalAnalyzer lexicalAnalyzer = new LexicalAnalyzer();
        lexicalAnalyzer.process(file);
        //System.out.println(lexicalAnalyzer.getPif());
        try {
            lexicalAnalyzer.getPif().writeFile();
            lexicalAnalyzer.getSymbolTable().writeFile();
        }
        catch (IOException e) {
            e.getMessage();
        }
    }

}
