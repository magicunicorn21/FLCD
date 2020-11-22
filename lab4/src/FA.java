import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FA {
    private List<String> states = new ArrayList<>();
    private List<String> startStates = new ArrayList<>();
    private List<String> alphabet = new ArrayList<>();
    private List<List<String>> transitions = new ArrayList<>();
    private List<String> finals = new ArrayList<>();

    public void read(File f) {
        try {
            Scanner myReader = new Scanner(f);
            String data = myReader.nextLine();
            data = myReader.nextLine();
            String[] sts = data.split(",");
            Collections.addAll(states, sts);
            data = myReader.nextLine();
            data = myReader.nextLine();
            String[] sss = data.split(",");
            Collections.addAll(startStates, sss);
            data = myReader.nextLine();
            data = myReader.nextLine();
            String[] a = data.split(",");
            Collections.addAll(alphabet, a);
            myReader.nextLine();
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                if (data.compareTo("Final states:") == 0) {
                    break;
                }
                List<String> l = new ArrayList<>();
                String[] s1 = data.split(",");
                l.add(s1[0]);
                String[] s2 = s1[1].split("=");
                l.add(s2[0]);
                l.add(s2[1]);
                transitions.add(l);
            }
            data = myReader.nextLine();
            String[] fs = data.split(",");
            Collections.addAll(finals, fs);
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isDFA() {
        for (int i = 0; i < transitions.size() - 1; i++) {
            for (int j = i + 1; j < transitions.size(); j++)
                if ((transitions.get(i).get(0).compareTo(transitions.get(j).get(0)) == 0) && (transitions.get(i).get(1).compareTo(transitions.get(j).get(1)) == 0))
                    return false;
        }
        return true;
    }

    public boolean isAccepted(String seq) {
        if (!isDFA())
            return false;
        String currentState;
        int count = 0;
        for (int idx = 0; idx < startStates.size(); idx++) {
            currentState = startStates.get(idx);
            int i = 0;
            while (i < seq.length()) {
                for (List<String> t : transitions) {
                    if ((t.get(0).compareTo(currentState) == 0) && (t.get(1).compareTo(String.valueOf(seq.charAt(i))) == 0)) {
                        currentState = t.get(2);
                        count++;
                        break;
                    }
                }
                if (count != (i+1))
                    return false;
                i++;
            }
        }
        return true;
    }

    public List<String> getStartStates() {
        return startStates;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public List<String> getFinals() {
        return finals;
    }

    public List<String> getStates() {
        return states;
    }

    public List<List<String>> getTransitions() {
        return transitions;
    }
}
