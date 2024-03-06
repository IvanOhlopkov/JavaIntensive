import junit.framework.TestCase;

public class MyHashMapTests extends TestCase {

    MyHashMap<String, Integer> myHashMap;

    @Override
    protected void setUp() throws Exception {
        myHashMap = new MyHashMap<>();
    }

    public void testPutAndGet() {
        myHashMap.put("Иванов", 1);
        int actual = myHashMap.get("Иванов");
        assertEquals(1, actual);
    }

    public void testRepeatPut() {
        myHashMap.put("Иванов", 2);
        int actual = myHashMap.get("Иванов");
        assertEquals(2, actual);
    }

    public void testSeveralPuts() {
        myHashMap.put("Петров", 3);
        myHashMap.put("Сидоров-Суворов", 4);
        int actual1 = myHashMap.get("Петров");
        int actual2 = myHashMap.get("Сидоров-Суворов");
        assertEquals(3, actual1);
        assertEquals(4, actual2);
    }

    public void testRemoveKey() {
        myHashMap.remove("Петров");
        assertNull(myHashMap.get("Петров"));
    }

    public void testClearHashMap() {
        myHashMap.clear();
        assertNull(myHashMap.get("Иванов"));
    }
}
