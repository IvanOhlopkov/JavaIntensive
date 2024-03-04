import junit.framework.TestCase;

public class MyArrayListTests extends TestCase {

    MyArrayList<String> myArrayListString;

    @Override
    protected void setUp() throws Exception {
        myArrayListString = new MyArrayList<>();
    }

    public void testAddMyArrayList() {
        myArrayListString.add("Добавление строк");
        String expected = "Добавление строк";
        String actual = myArrayListString.get(0);
        assertEquals(expected, actual);
    }

    public void testFirstMyArrayList() {
        MyArrayList<Integer> firstElementList = new MyArrayList<>(10, 1);
        Integer expected = 1;
        Integer actual = firstElementList.get(0);
        assertEquals(expected, actual);

        expected = null;
        firstElementList.deleteFirstItem();
        assertEquals(expected, firstElementList.get(0));
    }

    public void testResizeMyArrayList(){
        assertEquals(10, myArrayListString.getLength());
        myArrayListString.add("Первое значение");
        myArrayListString.add("Второе значение");
        myArrayListString.add("Третье значение");
        myArrayListString.add("Четвертое значение");
        myArrayListString.add("Пятое значение");
        myArrayListString.add("Шестое значение");
        myArrayListString.add("Седьмое значение");
        myArrayListString.add("Восьмое значение");
        myArrayListString.add("Девятое значение");
        myArrayListString.add("Десятое значение");
        assertEquals(20, myArrayListString.getLength());

        myArrayListString.remove(9);
        myArrayListString.remove(8);
        myArrayListString.remove(7);
        myArrayListString.remove(6);
        myArrayListString.remove(5);
        myArrayListString.remove(4);
        assertEquals(10, myArrayListString.getLength());
    }

    public void testRemoveMyArrayList() {
        myArrayListString.add("Это индекс ноль");
        myArrayListString.add("Это индекс один");
        myArrayListString.add("Это индекс два");
        myArrayListString.remove(1);
        assertEquals("Это индекс два", myArrayListString.get(1));
    }
}
