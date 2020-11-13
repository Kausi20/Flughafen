package ch.bzz.it.flugzeug.model;


import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

/**
 * <p>
 * flughafen
 *
 * @author Kaushican Uthayakumaran
 */
public class Flugzeug {
    @FormParam("flugzeugUUID")
    private String flugzeugUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String name;

    @FormParam("hoechstgeschwindigkeit")
    @DecimalMax(value = "2000")
    @DecimalMin(value = "300")
    private BigDecimal hoechstgeschwindigkeit;

    @FormParam("laenge")
    @DecimalMax(value = "100")
    @DecimalMin(value = "30")
    private BigDecimal laenge;

    @FormParam("spannweite")
    @DecimalMax(value = "90")
    @DecimalMin(value = "20")
    private BigDecimal spannweite;

    @FormParam("anzPlaetze")
    @DecimalMax(value = "1000")
    @DecimalMin(value = "10")
    private BigDecimal anzPlaetze;

    private Hersteller hersteller;


    /**
     * Gets the flugzeugUUID
     *
     * @return value of flugzeugUUID
     */
    public String getFlugzeugUUID() {
        return flugzeugUUID;
    }

    /**
     * Sets the flugzeugUUID
     *
     * @param flugzeugUUID the value to set
     */

    public void setFlugzeugUUID(String flugzeugUUID) {
        this.flugzeugUUID = flugzeugUUID;
    }

    /**
     * Gets the name
     *
     * @return value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name
     *
     * @param name the value to set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the hoechstgeschwindigkeit
     *
     * @return value of hoechstgeschwindigkeit
     */
    public BigDecimal getHoechstgeschwindigkeit() {
        return hoechstgeschwindigkeit;
    }

    /**
     * Sets the hoechstgeschwindigkeit
     *
     * @param hoechstgeschwindigkeit the value to set
     */

    public void setHoechstgeschwindigkeit(BigDecimal hoechstgeschwindigkeit) {
        this.hoechstgeschwindigkeit = hoechstgeschwindigkeit;
    }

    /**
     * Gets the laenge
     *
     * @return value of laenge
     */
    public BigDecimal getLaenge() {
        return laenge;
    }

    /**
     * Sets the laenge
     *
     * @param laenge the value to set
     */

    public void setLaenge(BigDecimal laenge) {
        this.laenge = laenge;
    }

    /**
     * Gets the spannweite
     *
     * @return value of spannweite
     */
    public BigDecimal getSpannweite() {
        return spannweite;
    }

    /**
     * Sets the spannweite
     *
     * @param spannweite the value to set
     */

    public void setSpannweite(BigDecimal spannweite) {
        this.spannweite = spannweite;
    }

    /**
     * Gets the anzPlaetze
     *
     * @return value of anzPlaetze
     */
    public BigDecimal getAnzPlaetze() {
        return anzPlaetze;
    }

    /**
     * Sets the anzPlaetze
     *
     * @param anzPlaetze the value to set
     */

    public void setAnzPlaetze(BigDecimal anzPlaetze) {
        this.anzPlaetze = anzPlaetze;
    }

    /**
     * Gets the hersteller
     *
     * @return value of hersteller
     */
    public Hersteller getHersteller() {
        return hersteller;
    }

    /**
     * Sets the hersteller
     *
     * @param hersteller the value to set
     */

    public void setHersteller(Hersteller hersteller) {
        this.hersteller = hersteller;
    }
}

