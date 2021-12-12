package org.kryptojagd.level.tasks;

import org.kryptojagd.level.Level;

/**
 * The class describes a task, where you have to answer multiple choice questions
 *
 * @author Sonja
 */
public class MultipleChoiceTask implements Task {

    private String question;
    private String answer;
    private String[] possibilities;

    /**
     * Creates a {@link MultipleChoiceTask}
     *
     * @param question the question, which you have to answer
     * @param answer the right answer of the question
     * @param possibilities the possibilities to answer
     */
    public MultipleChoiceTask(String question, String answer, String[] possibilities) {
        this.question = question;
        this.answer = answer;
        this.possibilities = possibilities;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return this.answer.equals(answer);
    }

    @Override
    public String toString() {
        String multipleChoice = "";
        return multipleChoice;
    }

}
