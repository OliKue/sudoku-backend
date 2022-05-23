package de.berlin.sudokuBackend.sudoku.sudokuApi;

import de.berlin.sudokuBackend.sudoku.Difficulty;
import de.berlin.sudokuBackend.sudoku.Sudoku;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SudokuRepository extends JpaRepository<Sudoku, Long> {
    List<Sudoku> findSudokuByDifficulty(Difficulty difficulty);
}
