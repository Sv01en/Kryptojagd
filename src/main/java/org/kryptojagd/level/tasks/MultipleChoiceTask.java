package org.kryptojagd.level.tasks;

/**
 * The class describes a task, where you have to answer multiple choice questions
 *
 * @author Sonja
 */
public class MultipleChoiceTask implements Task {

    private String question;
    private String correctAnswer;
    private String[] answerOptions;
    private int timeInSec;

    /**
     * Creates a {@link MultipleChoiceTask}
     *
     * @param question the question, which you have to answer
     * @param answer the right answer of the question
     * @param possibilities the possibilities to answer
     */
    public MultipleChoiceTask(String question, String[] answerOptions, String correctAnswer, int timeInSec) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerOptions = answerOptions;
        this.timeInSec = timeInSec;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getPossibilities() {
        return possibilities;
    }

    @Override
    public boolean proofAnswer(String answer) {
        return this.correctAnswer.equals(answer);
    }

    @Override
    public String toString() {
        return "";
    }

}

