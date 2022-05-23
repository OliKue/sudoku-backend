package de.berlin.sudokuBackend.sudoku.generator;

import de.berlin.sudokuBackend.sudoku.Sudoku;
import de.berlin.sudokuBackend.sudoku.sudokuApi.SudokuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuGeneratorController {
    @Autowired
    private SudokuRepository repository;

    @GetMapping(value = "/sudoku/generate", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> generateSudoku() {
        double start = System.currentTimeMillis();
        SudokuGenerator generator = new SudokuGenerator();
        Sudoku sudoku = generator.generateNew(40);
        repository.save(sudoku);

        double end = System.currentTimeMillis();
        double time = (end - start);

        return new ResponseEntity<String>("Created Sudoku with diff " + sudoku.getDifficulty() + " in " + time + "ms!", HttpStatus.OK);
    }
}
