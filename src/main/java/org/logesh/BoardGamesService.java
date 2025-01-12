package org.logesh;

import java.util.List;

public interface BoardGamesService {

    public List<String> fetchAvailableBoardGames();

    public List<String> deleteNonBoardGames();
}
