package org.kryptojagd.level.tasks;

/**
 * The class describes a task, where you have to answer multiple choice questions
 *
 * @author Sonja Kuklok
 */
public class MultipleChoiceTask implements Task {

    private String question;
    private String correctAnswer;
    private String[] answerOptions;
    private boolean taskCompleted = false;

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

    /**
     * Returns the possibilities as a string array.
     *
     * @return possibilities as a string array
     */
    public String[] getPossibilities() {
        return answerOptions;
    }

    /**
     * Returns the questions text as a string.
     *
     * @return text as a string
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Proves the given answer.
     *
     * @param answer given by the gui
     * @return true or false as a boolean
     */
    @Override
    public boolean proveAnswer(String answer) {
        if(this.correctAnswer.equals(answer)) {
            taskCompleted = true;
            return true;
        }
        return false;
    }

    @Override
    public boolean getTaskCompleted() {
        return taskCompleted;
    }

    /**
     * Returns an empty string.
     *
     * @return empty string
     */
    @Override
    public String toString() {
        return "MultipleChoiceTask";
    }

}
