package Project;

public class Player {
    private String name;
    private int moves;

    public Player() {
        this.name = "unknown";
        this.moves = 0;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public String getName() {
        return this.name;
    }

    public int getMoves() {
        return this.moves;
    }
}