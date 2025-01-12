import org.junit.BeforeClass;
import org.junit.Test;
import org.logesh.ExternalService;
import org.logesh.impl.BoardGamesImpl;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class BoardGamesImplMockTest {

    public static BoardGamesImpl boardGamesService = null;

    public static ExternalService externalService = null;

    @BeforeClass
    public static void setup() {
        // Mocked ExternalService instead of creating Stubs and intialize it.
        boardGamesService = mock(BoardGamesImpl.class);
        externalService = mock(ExternalService.class);
    }

    @Test
    public void testBoardGamesImpl() {
        List<String> availBoardGames = Arrays.asList(new String[]{"Chess", "Carom", "Ludo"});

        // Mocking the getBoardGames() method. So, 'availBoardGames' will be returned when getBoardGames() method is called.
        // Below single line code needs separate method definition when we use Stubs.
        when(externalService.getBoardGames()).thenReturn(availBoardGames);
        assertEquals(3, externalService.getBoardGames().size());
    }

    @Test
    public void testDeleteNonBoardGames() {

        ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);

        List<String> availBoardGames = Arrays.asList(new String[]{"Chess", "Carom", "Ludo", "Cricket"});
        when(externalService.getBoardGames()).thenReturn(availBoardGames);

        List<String> filteredBoardGames = boardGamesService.deleteNonBoardGames();

        // verify the method deleteGame() was called or not. If 'cricket' is there in availBoardGames. It should be called.
//        verify(externalService).deleteBoardGame(any(String.class));
        verify(externalService, never()).deleteBoardGame(stringArgumentCaptor.capture()); // Capturing the argument which was passed
        verify(externalService, atLeast(5)).deleteBoardGame(any());  // call atLeast 5 times
        verify(externalService, times(5)).deleteBoardGame(any()); // should calls exactly 5 times

        assertEquals("cricket", stringArgumentCaptor.getValue());
        assertEquals(1, stringArgumentCaptor.getAllValues().size());
    }

}
