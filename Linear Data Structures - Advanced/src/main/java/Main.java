import implementations.DoublyLinkedList;


public class Main {
    public static void main(String[] args) {

        DoublyLinkedList list = new DoublyLinkedList();

        list.addFirst("first");
        list.addFirst("second");
        list.addFirst("third");
        list.addFirst("fourth");


        for (Object o : list) {
            System.out.print(o + " ");
        }

        System.out.println();
        list.removeLast();

        for (Object o : list) {
            System.out.print(o + " ");
        }

    }
}
