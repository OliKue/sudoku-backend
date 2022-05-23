package de.berlin.sudokuBackend.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SudokuTest {

    int[][] zeroGame = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    int[][] invalidGame = {{0, 1, 2, 3, 4, 5, 6, 7, 8},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    @Test
    public void testToString() {

        Sudoku sudoku = new Sudoku(invalidGame);
        System.out.println(sudoku.toString());

    }


    @Test
    public void testEquals() {

        Sudoku sudoku1 = new Sudoku(invalidGame);
        Sudoku sudoku1_ = new Sudoku(invalidGame);

        Sudoku sudoku2 = new Sudoku(zeroGame);

        Assertions.assertFalse(sudoku1.equals(sudoku2));
        Assertions.assertTrue(sudoku1.equals(sudoku1_));

    }
}

