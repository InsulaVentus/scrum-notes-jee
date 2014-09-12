package no.agricolas.scrumnotes.generatenote.notegenerator.utils.logging;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Simen Søhol
 */
public class LogMessages {
    public static final String SEPARATOR = StringUtils.rightPad("", 140, '-');
    public static final String NUMBER_OF_GENERATED_NOTES = "Generated %s subtasks from %s";
}
