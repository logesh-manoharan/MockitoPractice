package org.logesh;

import java.util.List;

// Consider this as a External Service we are going to access. Like Client Class in our projects
public interface ExternalService {

    public List<String> getBoardGames();

}
