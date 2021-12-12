package org.kryptojagd.level.tasks;

public class MultipleChoiceTask implements Task {

    private String question;
    private String answer;
    private String[] possibilities;

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
