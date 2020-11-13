package ch.bzz.it.flugzeug.model;


import javax.validation.constraints.*;
import javax.ws.rs.FormParam;
import java.math.BigDecimal;
/**
 * short description
 * <p>
 * Flugzeug
 *
 * @author Kaushican Uthayakumaran
 * @version 1.0
 * @since 15.03.20
 */
public class Passagier{

    @FormParam("passagierUUID")
    private String passagierUUID;

    @FormParam("vorname")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String vorname;

    @FormParam("nachname")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String nachname;

    @FormParam("geschlecht")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String geschlecht;

    @FormParam("alter")
    @DecimalMax(value = "130")
    @DecimalMin(value = "1")
    private BigDecimal alter;

    @FormParam("adresse")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String adresse;


    @FormParam("eMail")
    @NotEmpty
    @Size(min = 2, max = 40)
    private String eMail;

    private Flugzeug flugzeug;


    /**
     * Gets the passagierUUID
     *
     * @return value of passagierUUID
     */
    public String getPassagierUUID() {
        return passagierUUID;
    }

    /**
     * Sets the passagierUUID
     *
     * @param passagierUUID the value to set
     */

    public void setPassagierUUID(String passagierUUID) {
        this.passagierUUID = passagierUUID;
    }

    /**
     * Gets the vorname
     *
     * @return value of vorname
     */
    public String getVorname() {
        return vorname;
    }

    /**
     * Sets the vorname
     *
     * @param vorname the value to set
     */

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    /**
     * Gets the nachname
     *
     * @return value of nachname
     */
    public String getNachname() {
        return nachname;
    }

    /**
     * Sets the nachname
     *
     * @param nachname the value to set
     */

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * Gets the geschlecht
     *
     * @return value of geschlecht
     */
    public String getGeschlecht() {
        return geschlecht;
    }

    /**
     * Sets the geschlecht
     *
     * @param geschlecht the value to set
     */

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * Gets the alter
     *
     * @return value of alter
     */
    public BigDecimal getAlter() {
        return alter;
    }

    /**
     * Sets the alter
     *
     * @param alter the value to set
     */

    public void setAlter(BigDecimal alter) {
        this.alter = alter;
    }

    /**
     * Gets the adresse
     *
     * @return value of adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Sets the adresse
     *
     * @param adresse the value to set
     */

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * Gets the eMail
     *
     * @return value of eMail
     */
    public String geteMail() {
        return eMail;
    }

    /**
     * Sets the eMail
     *
     * @param eMail the value to set
     */

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }



    /**
     * Gets the flugzeug
     *
     * @return value of flugzeug
     */
    public Flugzeug getFlugzeug() {
        return flugzeug;
    }


    /**
     * Sets the flugzeug
     *
     * @param flugzeug the value to set
     */

    public void setFlugzeug(Flugzeug flugzeug) {
        this.flugzeug = flugzeug;
    }
}
