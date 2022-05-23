package de.berlin.sudokuBackend.sudoku.generator.solvers;


import de.berlin.sudokuBackend.sudoku.Sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogicalSolver extends SudokuSolver {
    //If a number is possible it is marked as true
    //If [][][0] is true number was found
    boolean[][][] possible = new boolean[9][9][10];

    int verbose = 0;


    public LogicalSolver(Sudoku sudoku) {
        super(sudoku);
        //Fill Array with true
        for (boolean[][] field : possible) {
            for (boolean[] row : field) {
                Arrays.fill(row, true);
                row[0] = false;
            }
        }

        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (sudoku.getGame()[row][column] != 0) {
                    setNumber(row, column, sudoku.getGame()[row][column]);
                }
            }
        }
    }

    @Override
    public Sudoku solveSudoku() {
        if (verbose >= 2)
            printPossible();

        boolean foundNewOne = true;
        while (foundNewOne) {
            // Unique Solver
            foundNewOne = uniqueSolver();
            // Single Value Solvers
            foundNewOne = foundNewOne || fieldSolver() || rowSolver() || columnSolver();

            // Identical Sibling Solvers
            foundNewOne = foundNewOne || identicalSiblingFieldSolver() || identicalSiblingRowSolver() || identicalSiblingColumnSolver();


            if (foundNewOne && verbose >= 2)
                printPossible();
        }


        return sudoku;
    }

    public boolean solveableSudoku() {

        boolean foundNewOne = true;
        while (foundNewOne) {
            // Unique Solver
            foundNewOne = uniqueSolver();
            // Single Value Solvers
            foundNewOne = foundNewOne || fieldSolver() || rowSolver() || columnSolver();

            // Identical Sibling Solvers
            foundNewOne = foundNewOne || identicalSiblingFieldSolver() || identicalSiblingRowSolver() || identicalSiblingColumnSolver();

            }

        return sudoku.gameSolved();
    }

    private boolean uniqueSolver() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (!possible[row][column][0]) {

                    int count = 0;
                    int value = 0;
                    for (int i = 1; i < 10; i++) {
                        if (possible[row][column][i]) {
                            count++;
                            value = i;
                        }
                    }


                    if (count == 1) {
                        setNumber(row, column, value);
                        if (verbose >= 1) {
                            System.out.println("uniqueSolver found one! row: " + row + ", column: " + column + ", number: " + value);
                        }
                        return true;
                    }
                }
            }
        }
        if (verbose >= 1) {
            System.out.println("uniqueSolver found none...");
        }
        return false;
    }

    private boolean rowSolver() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (!possible[row][column][0]) {
                    boolean[] temp = possible[row][column].clone();

                    for (int r = BOARD_START_INDEX; r < BOARD_SIZE; r++) {
                        for (int i = 1; i < 10; i++) {
                            if (possible[r][column][i] && r != row) {
                                temp[i] = false;
                            }
                        }
                    }

                    int count = 0;
                    int value = 0;
                    for (int i = 1; i < 10; i++) {
                        if (temp[i]) {
                            count++;
                            value = i;
                        }
                    }
                    if (count == 1) {
                        setNumber(row, column, value);
                        if (verbose >= 1) {
                            System.out.println("rowSolver found one! row: " + row + ", column: " + column + ", number: " + value);
                        }
                        return true;
                    }

                }
            }
        }
        if (verbose >= 1) {
            System.out.println("rowSolver found none...");
        }
        return false;
    }

    private boolean columnSolver() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (!possible[row][column][0]) {
                    boolean[] temp = possible[row][column].clone();

                    for (int c = BOARD_START_INDEX; c < BOARD_SIZE; c++) {
                        for (int i = 1; i < 10; i++) {
                            if (possible[row][c][i] && c != column) {
                                temp[i] = false;
                            }
                        }
                    }

                    int count = 0;
                    int value = 0;
                    for (int i = 1; i < 10; i++) {
                        if (temp[i]) {
                            count++;
                            value = i;
                        }
                    }
                    if (count == 1) {
                        setNumber(row, column, value);
                        if (verbose >= 1) {
                            System.out.println("columnSolver found one! row: " + row + ", column: " + column + ", number: " + value);
                        }
                        return true;
                    }
                }
            }

        }
        if (verbose >= 1) {
            System.out.println("columnSolver found none...");
        }
        return false;
    }

    private boolean fieldSolver() {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                if (!possible[row][column][0]) {
                    boolean[] temp = possible[row][column].clone();

                    int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
                    int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

                    int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
                    int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

                    for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
                        for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                            for (int i = 1; i < 10; i++) {
                                if (possible[r][c][i] && r != row) {
                                    temp[i] = false;
                                }
                            }
                        }
                    }

                    for (int c = BOARD_START_INDEX; c < BOARD_SIZE; c++) {
                        for (int i = 1; i < 10; i++) {
                            if (possible[row][c][i] && c != column) {
                                temp[i] = false;
                            }
                        }
                    }

                    int count = 0;
                    int value = 0;
                    for (int i = 1; i < 10; i++) {
                        if (temp[i]) {
                            count++;
                            value = i;
                        }
                    }
                    if (count == 1) {
                        setNumber(row, column, value);
                        if (verbose >= 1) {
                            System.out.println("fieldSolver found one! row: " + row + ", column: " + column + ", number: " + value);
                        }
                        return true;
                    }
                }
            }

        }
        if (verbose >= 1) {
            System.out.println("fieldSolver found none...");
        }
        return false;
    }

    private boolean identicalSiblingRowSolver() {


        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            List<boolean[]> rowPoss = new ArrayList<>();

            for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                rowPoss.add(possible[row][column]);
            }

            for (boolean[] field : rowPoss) {
                // Count possibilities in dup.
                int possCounter = 0;
                for (boolean poss : field) {
                    if (poss) {
                        possCounter++;
                    }
                }

                boolean[] duplicate = null;

                // Count number of dup.
                int counter = 0;
                for (boolean[] fieldToCompare : rowPoss) {
                    // if exactly the same
                    if (field != fieldToCompare) {
                        // if Value the same
                        if (Arrays.equals(field, fieldToCompare)) {
                            counter++;
                            if (counter == possCounter) {
                                duplicate = field;

                            }
                        }
                    }
                }

                if (duplicate != null) {
                    boolean removedOne = false;
                    //remove values from dup. form row
                    for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
                        if (!possible[row][column][0]) {
                            // delete all numbers from duplicate
                            if (!Arrays.equals(duplicate, possible[row][column])) {
                                for (int i = 1; i < 10; i++) {
                                    if (duplicate[i] && possible[row][column][i]) {
                                        possible[row][column][i] = false;
                                        removedOne = true;
                                    }
                                }
                            }

                        }


                        if (removedOne) {
                            if (verbose >= 1) {
                                System.out.println("identicalSiblingRowSolver found duplicate...");
                            }
                            return true;
                        }

                    }
                }
            }
        }
        if (verbose >= 1) {
            System.out.println("identicalSiblingRowSolver found none...");
        }
        return false;
    }

    private boolean identicalSiblingColumnSolver() {


        for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
            List<boolean[]> columnPoss = new ArrayList<>();

            for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
                columnPoss.add(possible[row][column]);
            }

            for (boolean[] field : columnPoss) {
                if (!field[0]) {
                    // Count possibilities in dup.
                    int possCounter = 0;
                    for (boolean poss : field) {
                        if (poss) {
                            possCounter++;
                        }
                    }

                    boolean[] duplicate = null;

                    // Count number of dup.
                    int counter = 0;
                    for (boolean[] fieldToCompare : columnPoss) {

                        // if Value the same
                        if (Arrays.equals(field, fieldToCompare)) {
                            counter++;
                            if (counter == possCounter) {
                                duplicate = field.clone();

                            }

                        }
                    }

                    if (duplicate != null) {
                        boolean removedOne = false;
                        //remove values from dup. form row
                        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
                            if (!possible[row][column][0]) {
                                // delete all numbers from duplicate
                                if (!Arrays.equals(duplicate, possible[row][column])) {
                                    for (int i = 1; i < 10; i++) {
                                        if (duplicate[i] && possible[row][column][i]) {
                                            possible[row][column][i] = false;
                                            removedOne = true;
                                        }
                                    }
                                }

                            }
                        }
                        if (removedOne) {
                            if (verbose >= 1) {
                                System.out.println("identicalSiblingColumnSolver found duplicate...");
                            }
                            return true;
                        }

                    }
                }
            }

        }

        if (verbose >= 1) {
            System.out.println("identicalSiblingColumnSolver found none...");
        }
        return false;
    }

    private boolean identicalSiblingFieldSolver() {

        for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column += 3) {
            List<boolean[]> fieldPoss = new ArrayList<>();

            for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row += 3) {

                int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
                int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

                int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
                int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

                for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
                    for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                        fieldPoss.add(possible[r][c]);
                    }
                }


                for (boolean[] field : fieldPoss) {
                    if (!field[0]) {
                        // Count possibilities in dup.
                        int possCounter = 0;
                        for (boolean poss : field) {
                            if (poss) {
                                possCounter++;
                            }
                        }

                        boolean[] duplicate = null;

                        // Count number of dup.
                        int counter = 0;
                        for (boolean[] fieldToCompare : fieldPoss) {

                            // if Value the same
                            if (Arrays.equals(field, fieldToCompare)) {
                                counter++;
                                if (counter == possCounter) {
                                    duplicate = field.clone();

                                }

                            }
                        }

                        if (duplicate != null) {
                            boolean removedOne = false;
                            //remove values from dup. form row
                            for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
                                for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {

                                    if (!possible[r][c][0]) {
                                        // delete all numbers from duplicate
                                        if (!Arrays.equals(duplicate, possible[r][c])) {
                                            for (int i = 1; i < 10; i++) {
                                                if (duplicate[i] && possible[r][c][i]) {
                                                    possible[r][c][i] = false;
                                                    removedOne = true;
                                                }
                                            }
                                        }

                                    }

                                }
                            }
                            if (removedOne) {
                                if (verbose >= 1) {
                                    System.out.println("identicalSiblingFieldSolver found duplicate...");
                                }
                                return true;
                            }

                        }
                    }
                }
                fieldPoss.clear();
            }

        }

        if (verbose >= 1) {
            System.out.println("identicalSiblingFieldSolver found none...");
        }
        return false;
    }


    private void setNumber(int row, int column, int number) {
        removePossible(row, column, number);
        Arrays.fill(possible[row][column], false);
        this.possible[row][column][number] = true;
        this.possible[row][column][0] = true;
        this.sudoku.setNumber(row, column, number);
    }

    private void removePossible(int row, int column, int number) {
        removePossibleFromRow(row, number);
        removePossibleFromColumn(column, number);
        removePossibleFromField(row, column, number);
    }

    private void removePossibleFromRow(int row, int number) {
        for (int column = BOARD_START_INDEX; column < BOARD_SIZE; column++) {
            possible[row][column][number] = false;
        }
    }

    private void removePossibleFromColumn(int column, int number) {
        for (int row = BOARD_START_INDEX; row < BOARD_SIZE; row++) {
            possible[row][column][number] = false;
        }
    }

    private void removePossibleFromField(int row, int column, int number) {
        int subsectionRowStart = (row / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionRowEnd = subsectionRowStart + SUBSECTION_SIZE;

        int subsectionColumnStart = (column / SUBSECTION_SIZE) * SUBSECTION_SIZE;
        int subsectionColumnEnd = subsectionColumnStart + SUBSECTION_SIZE;

        for (int r = subsectionRowStart; r < subsectionRowEnd; r++) {
            for (int c = subsectionColumnStart; c < subsectionColumnEnd; c++) {
                possible[r][c][number] = false;
            }
        }
    }

    public void printPossible() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < 9; x++) {

            for (int y = 0; y < 9; y++) {
                if (y > 0) {
                    sb.append('|');
                    sb.append(' ');
                }

                for (int i = 1; i < 10; i++) {
                    if (possible[x][y][i]) {
                        sb.append(i);
                    } else {
                        sb.append('.');
                    }
                }

                sb.append(' ');

            }

            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }
}
