package botkill;

import botkill.enums.GameEnvironment;
import botkill.enums.GameMode;
import botkill.handler.GameStateHandler;
import botkill.handler.Handler;
import com.google.gson.Gson;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: Hell
 * Date: 23.11.2014
 * Time: 23:30
 */
public class Game {

    private Handler<GameState> gameStateHandler;

    private String id;
    private int rounds;
    private int currentRound;
    private int roundTime;
    private GameEnvironment environment;
    private GameMode mode;
    private float rain;
    private float darkness;
    private int playerCount;

    private Player myPlayer;

    public Game(JSONObject game) {
        this.id = game.getString("gameId");
        this.rounds = game.getInt("rounds");
        this.currentRound = 1;
        this.roundTime = game.getInt("roundTime");
        this.environment = GameEnvironment.valueOf(game.getString("environment"));
        this.mode = GameMode.valueOf(game.getString("gameMode"));
        this.rain = (float)game.getDouble("rain");
        this.darkness = (float)game.getDouble("darkness");
        this.playerCount = game.getInt("playerCount");

        gameStateHandler = new GameStateHandler();
    }

    public void reset() {
        this.currentRound++;
    }

    public String update(JSONObject state) {
        GameState gameState = new Gson().fromJson(state.toString(), GameState.class);
        gameStateHandler.handle(gameState);
        return gameStateHandler.getMessage();
    }

    public String createPlayer() {
        // If we already have a player, don't create a new one.
        // This will occur because game data is received after every round.
        if (myPlayer != null) {
            return null;
        }

        myPlayer = new Player(this);
        return myPlayer.getCreatePlayerMessage();
    }

    public Player getMyPlayer() {
        return myPlayer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public void setRoundTime(int roundTime) {
        this.roundTime = roundTime;
    }

    public GameEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(GameEnvironment environment) {
        this.environment = environment;
    }

    public GameMode getMode() {
        return mode;
    }

    public void setMode(GameMode mode) {
        this.mode = mode;
    }

    public float getRain() {
        return rain;
    }

    public void setRain(float rain) {
        this.rain = rain;
    }

    public float getDarkness() {
        return darkness;
    }

    public void setDarkness(float darkness) {
        this.darkness = darkness;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}
