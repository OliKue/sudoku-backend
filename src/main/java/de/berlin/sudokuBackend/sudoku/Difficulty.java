package de.berlin.sudokuBackend.sudoku;

// Difficulty is taken from https://www.sudokuoftheday.com/difficulty
// The integer value is the minimum score

public enum Difficulty {
    BEGINNER(4000),
    EASY(4900),
    MEDIUM(6000),
    TRICKY(7600),
    FIENDISH(10000),
    DIABOLICAL(18000);

    private final int difficultyScore;

    Difficulty(int difficultyScore) {
        this.difficultyScore = difficultyScore;
    }

    public static Difficulty getDifficulty(int difficultyScore) {
        Difficulty found = BEGINNER;
        for (Difficulty d : values()) {
            if (difficultyScore >= d.difficultyScore) {
                found = d;
            }
        }
        return found;
    }
}
