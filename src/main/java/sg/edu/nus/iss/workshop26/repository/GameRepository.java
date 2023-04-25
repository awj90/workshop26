package sg.edu.nus.iss.workshop26.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.workshop26.exception.GameNotFoundException;
import sg.edu.nus.iss.workshop26.models.Game;

@Repository
public class GameRepository {
    
    private static final String COLLECTION_NAME="game";

    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<List<Game>> getGames(Integer offset, Integer limit) {
        Query query = new Query().skip(offset).limit(limit);
        List<Game> result = mongoTemplate.find(query, Document.class, COLLECTION_NAME).stream().map(document -> Game.create(document)).collect(Collectors.toList());
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result);
        }
    }

    public Optional<List<Game>> getGamesSortedByRanking(Integer offset, Integer limit) {
        Query query = new Query().with(Sort.by(Sort.Direction.ASC, "ranking"))
                                .skip(offset).limit(limit);
        List<Game> result = mongoTemplate.find(query, Document.class, COLLECTION_NAME).stream().map(document -> Game.create(document)).collect(Collectors.toList());
        if (result.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(result);
        }
    }

    public Optional<Document> findGameByObjectId(String _id) throws GameNotFoundException {
        try {
            Query query = new Query(Criteria.where("_id").is(new ObjectId(_id)));
            Document document = mongoTemplate.findOne(query, Document.class, COLLECTION_NAME);
            return Optional.ofNullable(document);
        } catch (Exception e) {
            throw new GameNotFoundException("Game ObjectId %s does not exist".formatted(_id));
        }
    }
}
