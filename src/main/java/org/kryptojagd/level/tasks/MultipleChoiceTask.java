package org.kryptojagd.level.tasks;

import org.kryptojagd.level.PointSystem;

/**
 * The class describes a task, where you have to answer multiple choice questions
 *
 * @author Sonja Kuklok, Amelie Reichert
 */
public class MultipleChoiceTask implements Task {

    private final String question;
    private final String correctAnswer;
    private final String[] answerOptions;
    private String helpText;
    private boolean taskCompleted = false;
    private static final String TASK_NAME = "MultipleChoiceTask";
    private String name = TASK_NAME;
    private final int points = 5;

    /**
     * Creates a {@link MultipleChoiceTask}
     *
     * @param question      the question, which you have to answer
     * @param answerOptions the possibilities to answer
     * @param correctAnswer the right answer of the question
     * @param helpText      the help text
     */
    public MultipleChoiceTask(String question, String[] answerOptions, String correctAnswer, String helpText) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answerOptions = answerOptions;
        this.helpText = helpText;
    }

    /**
     * Set name.
     *@param name given as a String
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the fitting text-based help.
     *
     * @return the helpText
     */
    public String getHelpText() {
        return helpText;
    }

    /**
     * Set helpText.
     *@param newHelpText the new text-based help
     */
    public void setHelpText(String newHelpText) {
        this.helpText = newHelpText;
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
            if (PointSystem.getMultipleChoiceTaskFinished() < 8) {
                POINT_SYSTEM.setScore(PointSystem.getScore() + points);
                PointSystem.setMultipleChoiceTaskFinished(1);
            }
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
     * Set score.
     *@param score given as an integer
     */
    public void setScore(int score) {
        POINT_SYSTEM.setScore(score);
    }
    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return PointSystem.getScore();
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
