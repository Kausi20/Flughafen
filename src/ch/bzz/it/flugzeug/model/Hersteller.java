package ch.bzz.it.flugzeug.model;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;

/**
 * <p>
 * Flughafen
 *
 * @author Kaushican Uthayakumaran
 */
public class Hersteller {
    @FormParam("herstellerUUID")
    private String herstellerUUID;

    @FormParam("name")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String name;

    @FormParam("startJahr")
    @DecimalMax(value = "2030")
    @DecimalMin(value = "1900")
    private BigDecimal startJahr;

    @FormParam("umsatz")
    @DecimalMax(value = "1000000000")
    @DecimalMin(value = "1")
    private BigDecimal umsatz;

    @FormParam("anzModelle")
    @DecimalMax(value = "800")
    @DecimalMin(value = "1")
    private BigDecimal anzModelle;

    @FormParam("hauptsitz")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String hauptsitz;

    @FormParam("anzMitarbeiter")
    @DecimalMax(value = "100000")
    @DecimalMin(value = "2")
    private BigDecimal anzMitarbeiter;

    @FormParam("ceo")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String ceo;

    public Hersteller() {

    }

    /**
     * Gets the herstellerUUID
     *
     * @return value of herstellerUUID
     */
    public String getHerstellerUUID() {
        return herstellerUUID;
    }

    /**
     * Sets the herstellerUUID
     *
     * @param herstellerUUID the value to set
     */

    public void setHerstellerUUID(String herstellerUUID) {
        this.herstellerUUID = herstellerUUID;
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
     * Gets the startJahr
     *
     * @return value of startJahr
     */
    public BigDecimal getStartJahr() {
        return startJahr;
    }

    /**
     * Sets the startJahr
     *
     * @param startJahr the value to set
     */

    public void setStartJahr(BigDecimal startJahr) {
        this.startJahr = startJahr;
    }

    /**
     * Gets the umsatz
     *
     * @return value of umsatz
     */
    public BigDecimal getUmsatz() {
        return umsatz;
    }

    /**
     * Sets the umsatz
     *
     * @param umsatz the value to set
     */

    public void setUmsatz(BigDecimal umsatz) {
        this.umsatz = umsatz;
    }

    /**
     * Gets the anzModelle
     *
     * @return value of anzModelle
     */
    public BigDecimal getAnzModelle() {
        return anzModelle;
    }

    /**
     * Sets the anzModelle
     *
     * @param anzModelle the value to set
     */

    public void setAnzModelle(BigDecimal anzModelle) {
        this.anzModelle = anzModelle;
    }

    /**
     * Gets the hauptsitz
     *
     * @return value of hauptsitz
     */
    public String getHauptsitz() {
        return hauptsitz;
    }

    /**
     * Sets the hauptsitz
     *
     * @param hauptsitz the value to set
     */

    public void setHauptsitz(String hauptsitz) {
        this.hauptsitz = hauptsitz;
    }

    /**
     * Gets the anzMitarbeiter
     *
     * @return value of anzMitarbeiter
     */
    public BigDecimal getAnzMitarbeiter() {
        return anzMitarbeiter;
    }

    /**
     * Sets the anzMitarbeiter
     *
     * @param anzMitarbeiter the value to set
     */

    public void setAnzMitarbeiter(BigDecimal anzMitarbeiter) {
        this.anzMitarbeiter = anzMitarbeiter;
    }

    /**
     * Gets the ceo
     *
     * @return value of ceo
     */
    public String getCeo() {
        return ceo;
    }

    /**
     * Sets the ceo
     *
     * @param ceo the value to set
     */

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }



}

