package no.agricolas.scrumnotes.excel.utils;

import jxl.format.Colour;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * This class contains one method that returns all the dark colors in <code>Colour.getAllColours</code>
 *
 * @author Simen Søhol
 */
public class DarkColorFilter {

    public static List<Colour> getDarkColorsToFilter() {
        return asList(
                Colour.DARK_BLUE,
                Colour.DARK_BLUE2,
                Colour.DARK_GREEN,
                Colour.DARK_PURPLE,
                Colour.DARK_RED,
                Colour.DARK_RED2,
                Colour.DARK_TEAL,
                Colour.DARK_YELLOW,
                Colour.BLACK,
                Colour.BLUE,
                Colour.BLUE2
        );
    }
}
