package sg.edu.nus.iss.workshop26.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

public class Games implements Serializable {
    private List<Game> games;
    private Integer offset;
    private Integer limit;
    private Integer total;
    private long timestamp;

    public List<Game> getGames() {
        return games;
    }
    public void setGames(List<Game> games) {
        this.games = games;
    }
    public Integer getOffset() {
        return offset;
    }
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
    public Integer getLimit() {
        return limit;
    }
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
    public Integer getTotal() {
        return total;
    }
    public void setTotal(Integer total) {
        this.total = total;
    }
    public long getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public JsonObject toJsonObject() {
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        for (Game game : this.games) {
            jsonArrayBuilder.add(game.toJsonObject());
        }
        JsonArray jsonArray = jsonArrayBuilder.build();
        return Json.createObjectBuilder()
                    .add("games", jsonArray)
                    .add("offset", this.getOffset())
                    .add("limit", this.getLimit())
                    .add("total", this.getTotal())
                    .add("timestamp", new Date(this.getTimestamp()).toString())
                    .build();
    }
}
