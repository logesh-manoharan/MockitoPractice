import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    public static List list = null;

    @BeforeClass
    public static void setup() {
        // Mocking List Interface
        list = mock(List.class);
    }

    // mocking the list.get(0) method
    @Test
    public void testList_MockListGet() {
        when(list.get(0)).thenReturn("Logesh");
        assertEquals("Logesh", list.get(0));
    }

    // Argument Matchers - anyInt(), anyString(), any()...etc.,
    @Test
    public void testList_ArgMatcher() {
        when(list.get(anyInt())).thenReturn("Logesh");
        assertEquals("Logesh", list.get(0));
        assertEquals("Logesh", list.get(1));
        assertEquals("Logesh", list.get(2));
    }

    // Multiple Returns
    @Test
    public void testList_MultipleReturns() {
        when(list.get(anyInt())).thenReturn("Logesh").thenReturn("Vasanth").thenReturn("Kamalesh");
        assertEquals("Logesh", list.get(0));
        assertEquals("Vasanth", list.get(1));
        assertEquals("Kamalesh", list.get(2));
    }

    // Throwing Exceptions
    @Test(expected = RuntimeException.class)
    public void testList_ThrowException() {
        when(list.get(anyInt())).thenThrow(new RuntimeException("Testing Runtime Exception"));
        assertEquals("Logesh", list.get(0));
    }
}
