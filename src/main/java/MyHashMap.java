import java.util.HashMap;
import java.util.Map;

public class MyHashMap<K, V> {

    static class Node<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {

        HashMap<Integer, String> map = new HashMap<>();
        map.put(123, "Тест");
        System.out.println(map.get(123).hashCode());
        for(Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println(entry.getKey().hashCode());
            System.out.println(entry.getValue().hashCode());
        }
        System.out.println(map.get(123));

    }
    transient Node<K, V>[] table;

    public MyHashMap() {
    }


}