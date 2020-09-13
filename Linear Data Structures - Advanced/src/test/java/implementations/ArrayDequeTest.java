package implementations;

import org.apache.commons.beanutils.LazyDynaClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void ArrayDeque() {

        ArrayDeque<String> arrayDeque = new ArrayDeque();

        System.out.println(arrayDeque.get("2"));
    }

}