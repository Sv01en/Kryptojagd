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

    /**
     * Creates a {@link MultipleChoiceTask}
     *
     * @param question the question, which you have to answer
     * @param correctAnswer the right answer of the question
     * @param answerOptions the possibilities to answer
     */
    public MultipleChoiceTask(String question, String[] answerOptions, String correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerOptions = answerOptions;
    }

    public String[] getPossibilities() {
        return answerOptions;
    }

    public String getQuestion() {
        return question;
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
