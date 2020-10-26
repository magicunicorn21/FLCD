public class Main {
    public static void main(String args[]) {
        HashTable ht = new HashTable(23);
        ht.add("ab");
        ht.add("ru");
        ht.add("ba");
        System.out.println(ht);
        System.out.println(ht.search("nu"));
    }
}
