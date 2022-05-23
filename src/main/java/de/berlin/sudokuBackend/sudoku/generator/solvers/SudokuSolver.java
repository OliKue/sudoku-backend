package de.berlin.sudokuBackend.sudoku.generator.solvers;


import de.berlin.sudokuBackend.sudoku.Sudoku;

public abstract class SudokuSolver {

    // Values
    protected static final int BOARD_SIZE = 9;
    protected static final int SUBSECTION_SIZE = 3;
    protected static final int BOARD_START_INDEX = 0;

    protected static final int NO_VALUE = 0;
    protected static final int MIN_VALUE = 1;
    protected static final int MAX_VALUE = 9;

    protected Sudoku sudoku;

    public SudokuSolver(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    public abstract Sudoku solveSudoku();
}
