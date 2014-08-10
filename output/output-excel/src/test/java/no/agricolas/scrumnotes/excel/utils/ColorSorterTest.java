package no.agricolas.scrumnotes.excel.utils;

import jxl.format.Colour;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Simen Søhol
 */
public class ColorSorterTest {
    private ColorSorter colorSorter;

    @Before
    public void setUp() {
        colorSorter = new ColorSorter();
    }

    @Test
    public void getListWithoutDarkColors() throws Exception {
        assertList(colorSorter.getListWithoutDarkColors());
    }

    private void assertList(List<Colour> colourList) {
        for (Colour colour : colourList) {
            assertThat(isThisADarkColor(colour), is(false));
        }
    }

    private boolean isThisADarkColor(Colour colour) {
        for (Colour darkColor : DarkColorFilter.getDarkColorsToFilter()) {
            if (colour == darkColor) {
                return true;
            }
        }
        return false;
    }
}