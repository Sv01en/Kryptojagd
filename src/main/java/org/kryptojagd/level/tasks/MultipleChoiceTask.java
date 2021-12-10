package org.kryptojagd.level.tasks;

import java.util.HashMap;

public class MultipleChoiceTask implements Task {

    public MultipleChoiceTask(HashMap<String,Object> input) {
    }

    @Override
    public boolean proofAnswer(String answer) {
        return false;
    }
}
