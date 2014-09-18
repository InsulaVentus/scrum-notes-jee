package no.agricolas.scrumnotes.excel.utils;

import jxl.format.Colour;

import java.util.List;

import static java.util.Arrays.asList;

/**
 * This class contains one method that returns all the dark colors in <code>Colour.getAllColours</code>
 *
 * @author Simen Søhol
 */
public class ColorFilter {

    public static List<Colour> getColorsToFilter() {
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
                Colour.BLUE2,
                Colour.DEFAULT_BACKGROUND,
                Colour.DEFAULT_BACKGROUND1,
                Colour.PALETTE_BLACK,
                Colour.UNKNOWN,
                Colour.LIGHT_TURQUOISE,
                Colour.LIGHT_GREEN,
                Colour.LIGHT_ORANGE,
                Colour.PINK,
                Colour.PINK2,
                Colour.BROWN,
                Colour.AUTOMATIC,
                Colour.GRAY_25,
                Colour.GRAY_50,
                Colour.GRAY_80,
                Colour.BRIGHT_GREEN,
                Colour.WHITE,
                Colour.INDIGO,
                Colour.RED
        );
    }
}
