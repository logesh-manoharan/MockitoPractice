import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.logesh.ExternalService;
import stubs.BoardGamesStubs;

import java.util.List;

public class BoardGamesImplStubsTest {

    public static ExternalService externalService = null;

    @BeforeClass
    public static void setup() {
        externalService = new BoardGamesStubs(); // Using the Stubs to fetch the Dummy data / mocked data.
    }

    @Test
    public void testFetchAvailableBoardGames() {
        List<String> availBoardGames = externalService.getBoardGames();
        Assert.assertEquals(3, availBoardGames.size());
    }

}
