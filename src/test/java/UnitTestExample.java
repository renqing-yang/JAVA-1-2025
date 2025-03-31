import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UnitTestExample {
    private A a;
    private B b;
    @BeforeEach
    public void init() {
        b = Mockito.mock(B.class);
//        B b = Mockito.spy(B.class);
        when(b.factor(10)).thenReturn(20);
        a = new A(b);
    }

    @Test
    public void myFirstTest() {
        assertEquals(1, 2);
    }

    @Test
    public void myMockTest() {
//        assertEquals(a.multiply(5, 10), 50);
        a.multiply(5, 5);
        verify(b, times(1)).factor(5);
    }
}

class A {
    private B b;

    public A(B b) {
        this.b = b;
    }

    public int multiply(int val, int fac) {
        return val * b.factor(fac);
    }
}
class B {
    public int factor(int input) {
        return input;
    }
}