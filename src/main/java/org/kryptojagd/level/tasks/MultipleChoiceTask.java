package org.kryptojagd.level.tasks;

/**
 * The class describes a task, where you have to answer multiple choice questions
 *
 * @author Sonja Kuklok
 */
public class MultipleChoiceTask implements Task {

    private final String question;
    private final String correctAnswer;
    private final String[] answerOptions;
    private boolean taskCompleted = false;
    private static final String TASK_NAME = "MultipleChoiceTask";
    private String name = TASK_NAME;

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

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
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
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean proveAnswer(String answer) {
        if (this.correctAnswer.equals(answer)) {
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

    @Override
    public void setTaskCompletedEnd() {
        this.taskCompleted = false;
    }

}
