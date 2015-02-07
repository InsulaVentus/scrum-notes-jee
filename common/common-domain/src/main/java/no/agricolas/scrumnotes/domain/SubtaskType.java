package no.agricolas.scrumnotes.domain;

import jxl.format.Colour;

/**
 * @author Simen Søhol
 */
public enum SubtaskType {
    DEVELOPMENT(Colour.ICE_BLUE, "Utvikling "),
    TESTING(Colour.TAN, "Test "),
    ERROR(Colour.PALETTE_BLACK, "Feilretting "),
    DOCUMENTATION(Colour.LIGHT_GREEN, "SysDoc "),
    OTHER(Colour.GRAY_25, "Annet ");

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

    private void setLabel(String label) {
        this.label = label;
    }
}
