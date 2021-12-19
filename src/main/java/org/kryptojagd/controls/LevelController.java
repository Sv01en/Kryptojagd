package org.kryptojagd.controls;

import org.kryptojagd.level.Level;

/**
 * The class handles a level in the game
 *
 * @author Sonja
 */
public class LevelController {

    private Level level;

    public LevelController(Level level) {
        this.level = level;
    }

    /**
     * Runs the whole level, with every task
     */
    public void runLevel() {

    }

    /**
     * Runs the multiple choice tasks
     * First it gives the first multiple choice question.
     * Than it proofs the given answer and proofs the next multiple choice question
     * until every question is answered
     *
     */
    private void runMultipleChoice(){
        //Isn't finished!
        String output;
        output = level.getCurrentMultipleChoiceTask().getQuestion();
        String input = "";
        level.proofMultipleChoice(input);
        while (!level.multipleChoiceIsFinished()) {
            output = level.proofMultipleChoice(input);
        }
    }

}
