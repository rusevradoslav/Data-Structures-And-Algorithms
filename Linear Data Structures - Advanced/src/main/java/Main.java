import implementations.Queue;
import interfaces.Deque;

public class Main {
    public static void main(String[] args) {

        Queue queue = new Queue<>();

        queue.offer(10);
        queue.offer(11);
        queue.offer(12);
        queue.offer(13);
        System.out.println(queue.poll());

        System.out.println("New Queue");
        for (Object obj : queue) {
            System.out.println(obj);
        }


    }
}
