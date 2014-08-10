package no.agricolas.scrumnotes.excel.utils;

import jxl.format.Colour;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Simen Søhol
 */
public class ColorSorter {

    public List<Colour> getListWithoutDarkColors() {
        return removeDarkColors(asList(Colour.getAllColours()));
    }

    /**
     * Removes all the dark colors in <code>Colour.getAllColours()</code>
     *
     * @param colourList the colorlist to filter
     * @return colourlist without dark colors
     */
    private List<Colour> removeDarkColors(List<Colour> colourList) {
        List<Colour> lightList = new ArrayList<Colour>();
        for (Colour colour : colourList) {
            if (!darkColor(colour)) {
                lightList.add(colour);
            }
        }

        return lightList;
    }

    /**
     * Compares the given color to a dark color
     *
     * @param colour the color to compare
     * @return true if colour is dark
     */
    private boolean darkColor(Colour colour) {
        for (Colour darkColor : DarkColorFilter.getDarkColorsToFilter()) {
            if (colour == darkColor) {
                return true;
            }
        }
        return false;
    }
}
