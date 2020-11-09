import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LexicalAnalyzer {
    private SymbolTable symbolTable;
    private PIF pif;
    private List<String> separators;
    private List<String> operators;
    private List<String> reservedWords;

    LexicalAnalyzer() {
        symbolTable = new SymbolTable(20);
        pif = new PIF();
        separators =  Collections.unmodifiableList(Arrays.asList("{", "}", ";", ",", " ", "(", ")"));
        operators = Collections.unmodifiableList(Arrays.asList("+", "-", "*", "/", "^", "=", "==", "!=", ">=", "<=", ">", "<", "%", "&", "|"));
        reservedWords = Collections.unmodifiableList(Arrays.asList("nr", "char", "bool", "print", "get", "if", "nono", "loop", "while", "return", "woho"));
    }

    LexicalAnalyzer(SymbolTable st) {
        symbolTable = st;
        pif = new PIF();
    }

    LexicalAnalyzer(PIF p) {
        symbolTable = new SymbolTable(20);
        pif = p;
    }

    LexicalAnalyzer(SymbolTable st, PIF p) {
        symbolTable = st;
        pif = p;
    }

    public PIF getPif() {
        return pif;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public void setPif(PIF pif) {
        this.pif = pif;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public List<String> detect(File file) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        return lines;
    }

    public void process(File file){
        try {
            List<String> lines = detect(file);
            int lineIdx = 1;
            for (String l : lines) {
                l = l.replaceAll("(,)", " $1 ");
                l = l.replaceAll("(;)", " $1 ");
                l = l.replaceAll("(\\{)", " $1 ");
                l = l.replaceAll("(})", " $1 ");
                l = l.replaceAll("(\\()", " $1 ");
                l = l.replaceAll("(\\))", " $1 ");
                l = l.replaceAll("((\\d|([a-z][A-Z]))\\Q+\\E(\\d|([a-z][A-Z])))", " $1 ");
                l = l.replaceAll("((\\d|([a-z][A-Z]))\\Q-\\E(\\d|([a-z][A-Z])))", " $1 ");
                l = l.replaceAll("((\\d|([a-z][A-Z]))\\Q*\\E(\\d|([a-z][A-Z])))", " $1 ");
                l = l.replaceAll("(\\Q/\\E)", " $1 ");
                l = l.replaceAll("(([a-zA-Z]|\\d)?=|<=|>=|==|!=|&|%|>|<|\\Q|\\E|\\Q^\\E([a-zA-Z]|\\d)?)", " $1 ");
                String[] tokens = l.split("\\s+");
                int tokenIdx = 1;
                for (String t : tokens) {
                    if (t.compareTo("") != 0) {
                        if (separators.contains(t) || operators.contains(t) || reservedWords.contains(t))
                            pif.genPIF(t, -1);
                        else {
                            if (t.matches("[a-zA-Z]+") || t.matches("\"([a-zA-Z ]*|\\d*)\"") || t.matches("(\\Q[+-]\\E)?([1-9]\\d+|[0-9])")) {
                                int idx = symbolTable.search(t);
                                if (idx == -1) {
                                    symbolTable.add(t);
                                    pif.genPIF(t, symbolTable.search(t));
                                } else
                                    pif.genPIF(t, idx);
                            } else {
                                System.out.println("Lexical error at line " + lineIdx + " at token " + tokenIdx);
                                return;
                            }
                        }
                        tokenIdx++;
                    }
                }
                lineIdx++;
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
