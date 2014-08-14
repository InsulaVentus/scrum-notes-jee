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
    public void getListWithoutUnvalidColors() throws Exception {
        assertList(colorSorter.getListWithoutUnvalidColors());
    }

    private void assertList(List<Colour> colourList) {
        for (Colour colour : colourList) {
            assertThat(isThisAUnvalidColor(colour), is(false));
        }
    }

    private boolean isThisAUnvalidColor(Colour colour) {
        for (Colour darkColor : ColorFilter.getColorsToFilter()) {
            if (colour == darkColor) {
                return true;
            }
        }
        return false;
    }
}