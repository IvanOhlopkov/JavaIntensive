package mycollections;

public class MyArrayList<T> {

    private static final int DEFAULT_SIZE = 10;

    private Object[] array;

    private int pointer;

    public MyArrayList() {
        this.array = new Object[DEFAULT_SIZE];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.array = new Object[initialCapacity];
        }
    }

    public MyArrayList(int initialCapacity, T firstItem) {
        this(initialCapacity);
        this.add(firstItem);
    }

    public void deleteFirstItem() {
        array[0] = null;
        for (int i = 0; i < pointer; i++) {
            array[i] = array[i + 1];
        }
        array[pointer] = null;
    }

    public void deleteLastItem() {
        pointer--;
        array[pointer] = null;
    }

    public T get(int item) {
        return (T) array[item];
    }

    public void add(T item) {
        if (pointer == array.length - 1) {
            resize(array.length * 2);
        }
        array[pointer++] = item;
    }

    public void remove(int index) {
        for (int i = index; i < pointer; i++) {
            array[i] = array[i + 1];
        }
        array[pointer] = null;
        pointer--;
        if (array.length > DEFAULT_SIZE && pointer < array.length / 4) {
            resize(array.length / 2);
        }
    }

    public int size() {
        return pointer;
    }

    public int getLength() {
        return array.length;
    }

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }

}

