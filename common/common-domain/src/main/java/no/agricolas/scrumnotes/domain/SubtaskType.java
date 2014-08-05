package no.agricolas.scrumnotes.domain;

import jxl.format.Colour;

/**
 * @author Simen Søhol
 */
public enum SubtaskType {
    DEVELOPMENT(Colour.LIGHT_TURQUOISE, "Utvikling "),
    TESTING(Colour.LIGHT_GREEN, "Test "),
    OTHER(Colour.LIGHT_ORANGE, "Annet ");

    private Colour colour;

    private String label;

    private SubtaskType(Colour colour, String label) {
        setColour(colour);
        setLabel(label);
    }

    private void setColour(Colour colour) {
        this.colour = colour;
    }

    public Colour getColour() {
        return this.colour;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
