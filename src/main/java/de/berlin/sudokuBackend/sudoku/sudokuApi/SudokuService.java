package de.berlin.sudokuBackend.sudoku.sudokuApi;

import de.berlin.sudokuBackend.sudoku.Difficulty;
import de.berlin.sudokuBackend.sudoku.Sudoku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class SudokuService {
    @Autowired
    private SudokuRepository sudokuRepository;

    Random rand = new Random();

    /**
     * return a sudoku for given difficulty
     * returns null if no sudoku was found
     * @param difficulty
     * @return
     */
    public Sudoku getSudokuForDiff(Difficulty difficulty){
        List<Sudoku> sudokus = sudokuRepository.findSudokuByDifficulty(difficulty);

        if(sudokus.size()==0){
            return null;
        }else {
            int randomIndex = rand.nextInt(sudokus.size());
            return sudokus.get(randomIndex);
        }
    }

}
