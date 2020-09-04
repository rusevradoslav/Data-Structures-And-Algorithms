import implementations.ArrayList;
import implementations.SinglyLinkedList;
import interfaces.LinkedList;
import interfaces.List;
import org.apache.tools.ant.types.resources.First;

import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> data = new SinglyLinkedList<>();

        data.addLast("1");
        data.addLast("2");
        data.addLast("3");
        data.addLast("4");
        data.addLast("5");
        data.addLast("6");
        data.addLast("New Node");


        System.out.println(data.getLast());
        System.out.println(data.removeLast());
        System.out.println(data.getLast());


    }
}
