package de.berlin.sudokuBackend.sudoku;

import de.berlin.sudokuBackend.sudoku.generator.solvers.LogicalSolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LogicalSolverTest {

    int[][] game1_easy = {{4, 0, 0, 0, 5, 8, 0, 0, 6},
            {0, 6, 0, 4, 0, 3, 0, 0, 0},
            {0, 3, 5, 0, 0, 0, 0, 9, 4},
            {0, 0, 0, 0, 4, 0, 1, 0, 0},
            {0, 5, 0, 0, 1, 0, 0, 3, 0},
            {0, 0, 6, 0, 2, 0, 0, 0, 0},
            {2, 1, 0, 0, 0, 0, 6, 5, 0},
            {0, 0, 0, 6, 0, 1, 0, 8, 0},
            {6, 0, 0, 2, 3, 0, 0, 0, 7}};

    int[][] game1_easy_solved = {{4, 2, 1, 9, 5, 8, 3, 7, 6},
            {8, 6, 9, 4, 7, 3, 5, 2, 1},
            {7, 3, 5, 1, 6, 2, 8, 9, 4},
            {3, 7, 2, 5, 4, 9, 1, 6, 8},
            {9, 5, 4, 8, 1, 6, 7, 3, 2},
            {1, 8, 6, 3, 2, 7, 9, 4, 5},
            {2, 1, 3, 7, 8, 4, 6, 5, 9},
            {5, 4, 7, 6, 9, 1, 2, 8, 3},
            {6, 9, 8, 2, 3, 5, 4, 1, 7}};

    int[][] game2_hard = {{1, 0, 0, 0, 0, 4, 0, 8, 0},
            {0, 4, 0, 0, 0, 0, 0, 1, 0},
            {8, 0, 6, 2, 0, 0, 0, 0, 0},
            {0, 0, 0, 5, 2, 0, 7, 0, 0},
            {0, 0, 7, 0, 4, 0, 2, 0, 0},
            {0, 0, 1, 0, 9, 3, 0, 0, 0},
            {0, 0, 0, 0, 0, 2, 5, 0, 3},
            {0, 8, 0, 0, 0, 0, 0, 6, 0},
            {0, 9, 0, 3, 0, 0, 0, 0, 4}};

    int[][] game3_hard = {{0, 0, 0, 0, 8, 1 , 7, 0, 0},
            {0, 0, 4, 0, 0, 0, 0, 9, 0},
            {0, 7, 0, 5, 0, 4, 0, 8, 0},
            {0, 0, 0, 4, 0, 0, 6, 7, 0},
            {9, 0, 0, 1, 0, 7, 0, 0, 2},
            {0, 3, 7, 0, 0, 9, 0, 0, 0},
            {0, 5, 0, 3, 0, 2, 0, 4, 0},
            {0, 1, 0, 0, 0, 0, 3, 0, 0},
            {0, 0, 2, 8, 1, 0, 0, 0, 0}};


    @Test
    public void solveSudoku() {
        Sudoku toSolve = new Sudoku(game1_easy, game1_easy_solved, Difficulty.BEGINNER);

        LogicalSolver logicalSolver = new LogicalSolver(toSolve);

        Assertions.assertTrue(toSolve.gameSolved());
    }


}
