package no.agricolas.scrumnotes.generatenote.notegenerator.utils.validator;

import javax.swing.*;

import static no.agricolas.scrumnotes.generatenote.notegenerator.utils.validator.ValidatorMessages.NO_TASKNAME_MESSAGE;
import static no.agricolas.scrumnotes.generatenote.notegenerator.utils.validator.ValidatorMessages.NO_TASKNAME_TITLE;

/**
 * @author Simen Søhol
 */
public class TaskValidator {
    public boolean validateTask(String taskName) {
        boolean validTask;

        validTask = isTaskNameFieldEmpty(taskName);

        return validTask;
    }

    private boolean isTaskNameFieldEmpty(String taskname) {
        if (taskname.isEmpty()) {
            JOptionPane.showMessageDialog(null, NO_TASKNAME_MESSAGE, NO_TASKNAME_TITLE, JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }
}
