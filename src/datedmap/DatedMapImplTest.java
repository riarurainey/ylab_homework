package datedmap;

public class DatedMapImplTest {

    public static void main(String[] args) {
        DatedMap datedMap = new DatedMapImpl();
        datedMap.put("Ключ", "Значение");
        System.out.println(datedMap.getKeyLastInsertionDate("Ключ"));
        System.out.println(datedMap.getKeyLastInsertionDate("Нет такого ключа"));
        datedMap.remove("Ключ");
        System.out.println(datedMap.getKeyLastInsertionDate("Ключ"));

    }
}
