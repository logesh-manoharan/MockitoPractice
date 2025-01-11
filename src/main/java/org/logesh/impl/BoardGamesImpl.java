package org.logesh.impl;

import org.logesh.ExternalService;

import java.util.List;

// Class that is going to utilise the External Service
public class BoardGamesImpl {

    ExternalService externalService;

    public List<String> fetchAvailableBoardGames() {
        return externalService.getBoardGames();
    }

    /*
    Note:

    This implementation doesn't work properly. Since, we are not having External Service.
    To just know about the testing we are having this class.
     */
}
