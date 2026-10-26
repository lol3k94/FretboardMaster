package pl.pg.eti.fretboardmaster;

public class ChordModel {
    private String name;
    private int[] fretPositions;
    private int baseFret;

    public ChordModel(String name, int[] fretPositions, int baseFret) {
        this.name = name;
        this.fretPositions = fretPositions;
        this.baseFret = baseFret;
    }

    public String getName() {
        return name;
    }

    public int[] getFretPositions() {
        return fretPositions;
    }

    public int getBaseFret() {
        return baseFret;
    }

    public boolean isCorrect(int[] userPositions) {
        if (userPositions == null || userPositions.length != 6) {
            return false;
        }
        for (int i = 0; i < 6; i++) {
            if (userPositions[i] != this.fretPositions[i]) {
                return false;
            }
        }
        return true;
    }
}