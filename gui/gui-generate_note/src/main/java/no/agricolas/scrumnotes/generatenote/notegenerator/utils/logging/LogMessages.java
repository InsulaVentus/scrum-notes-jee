package no.agricolas.scrumnotes.generatenote.notegenerator.utils.logging;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Simen Søhol
 */
public class LogMessages {
    public static final String LOG_DEFAULT_START_MESSAGE = StringUtils.leftPad("Scrumnote generator log", 95, " ");
    public static final String LOG_EMPTY = " ";
    public static final String LOG_SEPARATOR = StringUtils.rightPad("", 140, '-');
    public static final String LOG_NUMBER_OF_GENERATED_NOTES = "Generated %s's %s subtasks";
    public static final String LOG_GENERATED_SINGLE_NOTE = "Generated the task \"%s\"";
    public static final String LOG_SAVE_DIR = "\"%s\" was saved to: %s";
}
