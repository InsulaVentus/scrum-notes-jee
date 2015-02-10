package no.agricolas.scrumnotes.excel.utils;

import jxl.format.Colour;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author Simen Søhol
 */
public class ColorSorter {

    /**
     * Removes all the dark colors in <code>Colour.getAllColours()</code>
     *
     * @return colourlist without dark colors
     */
    public List<Colour> getListWithoutDarkColors() {
        List<Colour> lightList = new ArrayList<>();
        for (Colour colour : asList(Colour.getAllColours())) {
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
        for (Colour darkColor : ColorFilter.getColorsToFilter()) {
            if (colour == darkColor) {
                return true;
            }
        }
        return false;
    }
}
