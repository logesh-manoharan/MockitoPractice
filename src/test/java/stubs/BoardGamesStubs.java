package stubs;

import org.logesh.ExternalService;

import java.util.List;

// Stub class that is going to mock the External Service's method.
// It will return dummy data for all the methods of external service.

// Note: We won't be using this class, after we started using Mockito
public class BoardGamesStubs implements ExternalService {

    @Override
    public List<String> getBoardGames() {
        return List.of("Chess", "Carom", "Ludo");
    }

    @Override
    public void deleteBoardGame(String boardGame) {

    }
}

/*
Disadvantages:

1.Dynamic Condition - If input parameter/conditions changes. We need to separate IF checks
for every input. It will raise to create Boiler plate code.
2.Service Definition - We need to define every method from the service.

*/

