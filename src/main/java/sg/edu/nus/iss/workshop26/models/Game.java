package sg.edu.nus.iss.workshop26.models;

import java.io.Serializable;
import java.util.Date;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game implements Serializable {
    
    private String _id;
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
    private Integer usersRated;
    private String url;
    private String image;

    public String get_id() {
        return _id;
    }
    public void set_id(String _id) {
        this._id = _id;
    }
    public Integer getGid() {
        return gid;
    }
    public void setGid(Integer gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public Integer getRanking() {
        return ranking;
    }
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
    public Integer getUsersRated() {
        return usersRated;
    }
    public void setUsersRated(Integer usersRated) {
        this.usersRated = usersRated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public static Game create(Document document) {
        Game game = new Game();
        game.set_id(document.getObjectId("_id").toString());
        game.setGid(document.getInteger("gid"));
        game.setName(document.getString("name"));
        game.setYear(document.getInteger("year"));
        game.setRanking(document.getInteger("ranking"));
        game.setUsersRated(document.getInteger("users_rated"));
        game.setUrl(document.getString("url"));
        game.setImage(document.getString("image"));
        return game;
    }

    public JsonObject toJsonObject() {
        return this.toJsonObject(false);
    }

    public JsonObject toJsonObject(boolean projectAll) {
        if (projectAll) {
            return Json.createObjectBuilder()
            .add("_id", this.get_id())
                    .add("game_id", this.getGid())
                    .add("name", this.getName())
                    .add("year", this.getYear())
                    .add("ranking", this.getRanking())
                    .add("users_rated", this.getUsersRated())
                    .add("url", this.getUrl())
                    .add("timestamp", new Date().toString())
                    .build();
        } else {
            return Json.createObjectBuilder()
                    .add("_id", this.get_id())
                    .add("game_id", this.getGid())
                    .add("name", this.getName())
                    .add("ranking", this.getRanking())
                    .build();
        }
    }
}
