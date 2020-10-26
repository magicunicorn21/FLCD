import java.util.Arrays;

public class HashTable {
    private int capacity;
    private int size;
    private String[] elems;

    public HashTable(int c) {
        capacity = c;
        size = 0;
        elems = new String[capacity];
        for (String e : elems)
            e = "";
    }

    public String[] getElems() {
        return elems;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSize() {
        return size;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setElems(String[] elems) {
        this.elems = elems;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int hashFunction(String e) {
        int sum = 0;
        for (int i = 0; i < e.length(); i++)
            sum += e.charAt(i);
        return sum % capacity;
    }

    public void resize(){
        capacity = capacity * 2;
        String[] copy = elems;
        elems = new String[capacity];
        for (String c : copy)
            add(c);
    }

    public void add(String e) {
        if (size != 0 && (double)size/capacity > 0.7)
            resize();
        if (search(e) != -1) {
            System.out.println("Already exists!");
            return;
        }
        int hashPos = hashFunction(e);
        if (elems[hashPos] == null) {
            elems[hashPos] = e;
        }
        else {
            int i;
            i = hashPos + 1;
            boolean emptyfound;
            emptyfound = false;
            while (!emptyfound) {
                if (i == capacity)
                    i = 0;
                if (elems[i] == null) {
                    emptyfound = true;
                    elems[i] = e;
                }
                i++;
            }
        }
        size++;
    }

    public int search(String e) {
        int hashPos = hashFunction(e);
        int i;
        i = hashPos;
        if (elems[i] != null && !elems[i].equals(e)) {
            i++;
            while ((elems[i] != null && !elems[i].equals(e)) && i != hashPos) {
                if (i == capacity - 1)
                    i = 0;
                i++;
            }
        }
        if (hashPos == i || elems[i] == null)
            return -1;
        return hashPos;
    }

    @Override
    public String toString() {
        return Arrays.toString(elems);
    }
}
