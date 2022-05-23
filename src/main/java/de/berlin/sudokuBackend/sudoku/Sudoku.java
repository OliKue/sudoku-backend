package de.berlin.sudokuBackend.sudoku;

import javax.persistence.*;

@Entity
@Table(name = "sudoku")
public class Sudoku {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String unsolvedGame;

    @Column
    private String solvedGame;

    @Column
    private Difficulty difficulty;

    public Sudoku() {
        StringBuilder sb = new StringBuilder("0");
        for(int i =1; i<81; i++){
            sb.append(", 0");
        }
        this.unsolvedGame= sb.toString();
        this.solvedGame= sb.toString();
    }

    public Sudoku(int[][] unsolvedGame){
        StringBuilder sb = new StringBuilder("0");
        for(int i =1; i<81; i++){
            sb.append(", 0");
        }
        this.solvedGame= sb.toString();
        this.unsolvedGame= stringFrom2DArray(unsolvedGame);
    }


    public Sudoku(int[][] unsolvedGame, int[][] solvedGame, Difficulty difficulty) {
        this.unsolvedGame = stringFrom2DArray(unsolvedGame);
        this.solvedGame = stringFrom2DArray(solvedGame);
        this.difficulty=difficulty;
    }

    public Sudoku(String unsolvedGame, String solvedGame, Difficulty difficulty) {
        this.unsolvedGame = unsolvedGame;
        this.solvedGame = solvedGame;
        this.difficulty=difficulty;
    }

    private String stringFrom2DArray(int[][] board){
        StringBuilder sb = new StringBuilder();
        for(int[] row: board){
            for(int cell:row){
                sb.append(cell);
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
    private int[][] arrayFromString(String string){
        int[][] board= new int[9][9];
        String[] splitt = string.replaceAll(" ", "").split(",");

        for(int i=0;i<81;i++){
            board[i / 9][i % 9] = Integer.parseInt(splitt[i]);
        }
        return board;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Sudoku toCompare = (Sudoku) obj;

        return this.getUnsolvedGame().equals(toCompare.getUnsolvedGame()) && this.getSolvedGame().equals(toCompare.getSolvedGame());
    }

    /**
     * method to check if sudoku is solved
     * @return
     */
    public boolean gameSolved() {
        int sum = 0;
        for (int[] row : getGame()) {
            for (int cell : row) {
                sum+=cell;
            }
        }
        return sum==405;
    }

    @Override
    public String toString(){
        return getUnsolvedGame();
    }

    // Getter Setter
    public int[][] getGame(){
        return arrayFromString(unsolvedGame);
    }

    public void setNumber(int x, int y, int number){
        int[][] tempBoard = arrayFromString(unsolvedGame);
        tempBoard[x][y] = number;
        this.unsolvedGame = stringFrom2DArray(tempBoard);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public void setSolvedGame(int[][] solvedGame) {
        this.solvedGame = stringFrom2DArray(solvedGame);
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}
