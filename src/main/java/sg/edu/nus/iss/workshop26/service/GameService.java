package sg.edu.nus.iss.workshop26.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop26.exception.GameNotFoundException;
import sg.edu.nus.iss.workshop26.models.Game;
import sg.edu.nus.iss.workshop26.repository.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;

    public Optional<List<Game>> getGames(Integer offset, Integer limit) {
        return gameRepository.getGames(offset, limit);
    }

    public Optional<List<Game>> getGamesSortedByRanking(Integer offset, Integer limit) {
        return gameRepository.getGamesSortedByRanking(offset, limit);
    }

    public Optional<Document> findGameByObjectId(String _id) throws GameNotFoundException {
        return gameRepository.findGameByObjectId(_id);
    }
}
