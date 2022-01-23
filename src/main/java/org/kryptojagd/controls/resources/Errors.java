package org.kryptojagd.controls.resources;

/**
 * This class provides resources.
 *
 * @author Sonja Kuklok
 */
public final class Errors {

    /////////////////////////////
    // Application
    /////////////////////////////

    /** Error-Message that the gamepiece can not be placed on top of another game piece.
     * Format with game piece identifier and a representation of the existing game piece */
    public static final String GAMEPIECE_PLACE_ON_TOP_ILLEGAL = "the gamepiece '%s' cannot be placed on top of '%s'.";


    /* private utility-class constructor */
    private Errors() { }
}