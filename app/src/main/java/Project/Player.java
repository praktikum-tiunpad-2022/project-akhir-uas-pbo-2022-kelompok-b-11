package Project;

public class Player {
    private String name;
    private int moves;
    private int timeCost;

    public Player() {
        this.name = "unknown";
        this.moves = 0;
        this.timeCost = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public void setTimeCost(int time) {
        this.timeCost = time;
    }

    public String getName() {
        return this.name;
    }

    public int getMoves() {
        return this.moves;
    }

    public int getTimeCost() {
        return this.timeCost;
    }
}