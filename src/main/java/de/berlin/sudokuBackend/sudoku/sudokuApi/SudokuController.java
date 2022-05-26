package de.berlin.sudokuBackend.sudoku.sudokuApi;


import de.berlin.sudokuBackend.sudoku.Difficulty;
import de.berlin.sudokuBackend.sudoku.Sudoku;
import de.berlin.sudokuBackend.sudoku.SudokuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SudokuController {

    @Autowired
    private SudokuService sudokuService;

    @CrossOrigin( {"https://my5ud0ku-app.herokuapp.com/","http://localhost:3000"})
    @GetMapping(value = "sudoku/get/{difficulty}")
    public ResponseEntity<SudokuDTO> getSudokuForDiff(@PathVariable(value = "difficulty") Difficulty difficulty) {
        Sudoku sudoku = sudokuService.getSudokuForDiff(difficulty);
        SudokuDTO sudokuDTO = new SudokuDTO(sudoku);
        return new ResponseEntity<>(sudokuDTO, HttpStatus.OK);
    }
}
