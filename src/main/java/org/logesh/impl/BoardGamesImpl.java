package org.logesh.impl;

import org.logesh.BoardGamesService;
import org.logesh.ExternalService;

import java.util.ArrayList;
import java.util.List;

// Class that is going to utilise the External Service
public class BoardGamesImpl implements BoardGamesService {

    ExternalService externalService;

    @Override
    public List<String> fetchAvailableBoardGames() {
        return externalService.getBoardGames();
    }

    @Override
    public List<String> deleteNonBoardGames() {
        List<String> availBoardGames = externalService.getBoardGames();

        for (String boardGame : availBoardGames) {
            if (boardGame.equalsIgnoreCase("cricket")) {
                externalService.deleteBoardGame(boardGame);
            }
        }

        return availBoardGames;
    }

    public List<String> deleteGame(List<String> availBoardGames, String gameToDelete) {
        availBoardGames.removeIf(game -> game.equalsIgnoreCase(gameToDelete));
        return availBoardGames;
    }



    /*
    Note:

    This implementation doesn't work properly. Since, we are not having External Service.
    To just know about the testing we are having this class.
     */
}
