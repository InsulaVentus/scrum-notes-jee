package no.agricolas.scrumnotes.domain;

import jxl.format.Colour;

/**
 * @author Simen Søhol
 */
public enum SubtaskType {
    UTVIKLING(Colour.LIGHT_BLUE),
    TEST(Colour.LIGHT_GREEN),
    ANNET(Colour.LIGHT_ORANGE);

    private Colour colour;

    private SubtaskType(Colour colour) {
        setColour(colour);
    }

    private void setColour(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return this.colour;
    }
}
