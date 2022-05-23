package de.berlin.sudokuBackend.sudoku;

public class SudokuDTO {
    private String unsolvedGame;
    private String solvedGame;

    public SudokuDTO(Sudoku sudoku) {
        this.unsolvedGame = sudoku.getUnsolvedGame();
        this.solvedGame = sudoku.getSolvedGame();
    }

    // Getter Setter

    public String getUnsolvedGame() {
        return unsolvedGame;
    }

    public void setUnsolvedGame(String unsolvedGame) {
        this.unsolvedGame = unsolvedGame;
    }

    public String getSolvedGame() {
        return solvedGame;
    }

    public void setSolvedGame(String solvedGame) {
        this.solvedGame = solvedGame;
    }
}
