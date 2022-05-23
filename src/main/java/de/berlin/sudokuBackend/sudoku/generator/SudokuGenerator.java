package de.berlin.sudokuBackend.sudoku.generator;

import de.berlin.sudokuBackend.sudoku.Difficulty;
import de.berlin.sudokuBackend.sudoku.Sudoku;
import de.berlin.sudokuBackend.sudoku.generator.solvers.LogicalSolver;

import java.util.Arrays;
import java.util.Random;

public class SudokuGenerator {
    Random random = new Random();
    int[][] board = {
            {1, 2, 3, 4, 5, 6, 7, 8, 9},
            {4, 5, 6, 7, 8, 9, 1, 2, 3},
            {7, 8, 9, 1, 2, 3, 4, 5, 6},

            {2, 3, 1, 5, 6, 4, 8, 9, 7},
            {5, 6, 4, 8, 9, 7, 2, 3, 1},
            {8, 9, 7, 2, 3, 1, 5, 6, 4},

            {3, 1, 2, 6, 4, 5, 9, 7, 8},
            {6, 4, 5, 9, 7, 8, 3, 1, 2},
            {9, 7, 8, 3, 1, 2, 6, 4, 5}
    };

    public Sudoku generateNew(int removeCount) {
        shuffleBoard();

        int[][] copy = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);

        int count = 0;
        while (count < removeCount) {
            count = 0;
            board = Arrays.stream(copy).map(int[]::clone).toArray(int[][]::new);
            removeValues(board);
            for (int[] row : board) {
                for (int cell : row) {
                    if (cell == 0) {
                        count++;
                    }
                }
            }

        }
        Sudoku toReturn = new Sudoku(board);
        toReturn.setSolvedGame(copy);
        toReturn.setDifficulty(Difficulty.EASY);
        return toReturn;
    }

    private boolean removeValues(int[][] board) {

        int randomX = random.nextInt(9);
        int randomY = random.nextInt(9);

        board[randomX][randomY] = 0;

        Sudoku sudoku = new Sudoku(board);
        LogicalSolver solver = new LogicalSolver(sudoku);

        if (solver.solveableSudoku()) {
            return removeValues(board);
        } else {
            return false;
        }

    }

    void shuffleBoard() {
        shuffleNumbers();
        shuffleCols();
        shuffleRows();
        shuffle3X3Cols();
        shuffle3X3Rows();
    }

    void shuffleNumbers() {
        for (int i = 1; i < 10; i++) {
            int ranNum = random.nextInt(8) + 1;
            swapNumbers(i, ranNum);
        }
    }

    private void swapNumbers(int n1, int n2) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                if (board[x][y] == n1) {
                    board[x][y] = n2;
                } else if (board[x][y] == n2) {
                    board[x][y] = n1;
                }
            }
        }
    }

    void shuffleRows() {
        int blockNumber;

        for (int i = 0; i < 9; i++) {
            int ranNum = random.nextInt(3);
            blockNumber = i / 3;
            swapRows(i, blockNumber * 3 + ranNum);
        }
    }

    void swapRows(int r1, int r2) {
        int[] row = board[r1];
        board[r1] = board[r2];
        board[r2] = row;
    }

    void shuffleCols() {
        int blockNumber;

        for (int i = 0; i < 9; i++) {
            int ranNum = random.nextInt(3);
            blockNumber = i / 3;
            swapCols(i, blockNumber * 3 + ranNum);
        }
    }

    void swapCols(int c1, int c2) {
        int colVal;
        for (int i = 0; i < 9; i++) {
            colVal = board[i][c1];
            board[i][c1] = board[i][c2];
            board[i][c2] = colVal;
        }
    }

    void shuffle3X3Rows() {

        for (int i = 0; i < 3; i++) {
            int ranNum = random.nextInt(3);
            swap3X3Rows(i, ranNum);
        }
    }

    void swap3X3Rows(int r1, int r2) {
        for (int i = 0; i < 3; i++) {
            swapRows(r1 * 3 + i, r2 * 3 + i);
        }
    }

    void shuffle3X3Cols() {

        for (int i = 0; i < 3; i++) {
            int ranNum = random.nextInt(3);
            swap3X3Cols(i, ranNum);
        }
    }

    private void swap3X3Cols(int c1, int c2) {
        for (int i = 0; i < 3; i++) {
            swapCols(c1 * 3 + i, c2 * 3 + i);
        }
    }
}
