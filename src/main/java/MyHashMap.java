
public class MyHashMap<K, V> {

    static final int INITIAL_CAPACITY = 16;

    static class Node<K, V> {
        final int hash;
        K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        private final K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    static int hash(Object key) {
        return (key == null) ? 0 : Math.abs(key.hashCode()) % INITIAL_CAPACITY;
    }

    private Node<K, V>[] table;

    public MyHashMap() {
        table = new Node[INITIAL_CAPACITY];
    }

    public void put(K key, V value) {
        int index = hash(key);

        Node<K, V> node = new Node<>(index, key, value, null);

        if (table[index] == null) {
            table[index] = node;
        } else {
            Node<K, V> previous = null;
            Node<K, V> current = table[index];

            while (current != null) {
                if (current.getKey().hashCode() == node.getKey().hashCode()) {
                    if (current.getKey().equals(key)) {
                        if (previous == null) {
                            node.next = current.next;
                            table[index] = node;
                            return;
                        } else {
                            node.next = current.next;
                            previous.next = node;
                            return;
                        }
                    }
                }
                previous = current;
                current = current.next;
            }
            previous.next = node;
        }
    }

    public V get(K key) {
        int index = hash(key);

        if (table[index] == null) {
            return null;
        } else {
            Node<K, V> current = table[index];

            while (current != null) {
                if (current.getKey().hashCode() == key.hashCode()) {
                    if (current.getKey().equals(key)) {
                        return current.getValue();
                    }
                }
                current = current.next;
            }
            return null;
        }
    }

    public void remove(K key) {
        int index = hash(key);

        if (table[index] != null) {
            Node<K, V> previous = null;
            Node<K, V> current = table[index];

            while (current != null) {
                if (current.getKey().hashCode() == key.hashCode()) {
                    if (current.getKey().equals(key)) {
                        if (previous == null) {
                            table[index] = table[index].next;
                        } else {
                            previous.next = current.next;
                        }
                    }
                }
                previous = current;
                current = current.next;
            }
        }
    }

    public void clear() {
        if (size() > 0) {
            for (int i = 0; i < table.length; i++) {
                table[i] = null;
            }
        }
    }

    public int size() {
        return table.length;
    }

}