import implementations.ArrayList;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        data.add("5");
        data.add("6");


        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));
        System.out.println(data.get(3));
        System.out.println(data.get(4));
        System.out.println(data.get(5));

        data.remove(1);
        System.out.println();
        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }
    }
}
