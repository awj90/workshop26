package sg.edu.nus.iss.workshop26.controller;

import jakarta.json.Json;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.workshop26.exception.GameNotFoundException;
import sg.edu.nus.iss.workshop26.models.Game;
import sg.edu.nus.iss.workshop26.models.Games;
import sg.edu.nus.iss.workshop26.service.GameService;

@RestController
public class GameRestController {
    
    @Autowired
    private GameService gameService;

    @GetMapping(path="/games", produces="application/json")
    public ResponseEntity<String> getGames(@RequestParam(defaultValue="0") Integer offset, @RequestParam(defaultValue="25") Integer limit) {

        Optional<List<Game>> results = gameService.getGames(offset, limit);
        if (results.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(Json.createObjectBuilder().add("error", "Your query returned an empty set").build().toString());
        }
        
        Games games = new Games();
        games.setGames(results.get());
        games.setOffset(offset);
        games.setLimit(limit);
        games.setTotal(results.get().size());
        games.setTimestamp(new Date().getTime());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(games.toJsonObject().toString());
    }

    @GetMapping(path="/games/rank", produces="application/json")
    public ResponseEntity<String> getGamesSortedByRanking(@RequestParam(defaultValue="0") Integer offset, @RequestParam(defaultValue="25") Integer limit) {
        
        Optional<List<Game>> results = gameService.getGamesSortedByRanking(offset, limit);
        if (results.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).contentType(MediaType.APPLICATION_JSON).body(Json.createObjectBuilder().add("error", "Your query returned an empty set").build().toString());
        }

        Games games = new Games();
        games.setGames(results.get());
        games.setOffset(offset);
        games.setLimit(limit);
        games.setTotal(results.get().size());
        games.setTimestamp(new Date().getTime());

        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(games.toJsonObject().toString());
    }

    @GetMapping(path="/game/{id}", produces="application/json")
    public ResponseEntity<String> findGameByObjectId(@PathVariable String id) throws GameNotFoundException {
        
        try {
            Optional<Document> result = gameService.findGameByObjectId(id);
            if (!result.isEmpty()) {
                Game game = Game.create(result.get());
                return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(game.toJsonObject(true).toString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(Json.createObjectBuilder()
                                                .add("error", "Game ObjectId %s does not exist".formatted(id))
                                                .build()
                                                .toString());
            }
        } catch (GameNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Json.createObjectBuilder()
                                            .add("error", ex.getMessage())
                                            .build()
                                            .toString());
        }
    }
}
