//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.07.24 at 10:44:24 AM CEST 
//


package fr.treeptik.springexercice.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="com_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="art_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="com_nom" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="com_email" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="com_texte" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="com_date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="com_validation" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "comId",
    "artId",
    "comNom",
    "comEmail",
    "comTexte",
    "comDate",
    "comValidation"
})
@XmlRootElement(name = "commentaires")
public class Commentaires {

    @XmlElement(name = "com_id")
    protected int comId;
    @XmlElement(name = "art_id")
    protected int artId;
    @XmlElement(name = "com_nom", required = true)
    protected String comNom;
    @XmlElement(name = "com_email", required = true)
    protected String comEmail;
    @XmlElement(name = "com_texte", required = true)
    protected String comTexte;
    @XmlElement(name = "com_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar comDate;
    @XmlElement(name = "com_validation")
    protected boolean comValidation;

    /**
     * Gets the value of the comId property.
     * 
     */
    public int getComId() {
        return comId;
    }

    /**
     * Sets the value of the comId property.
     * 
     */
    public void setComId(int value) {
        this.comId = value;
    }

    /**
     * Gets the value of the artId property.
     * 
     */
    public int getArtId() {
        return artId;
    }

    /**
     * Sets the value of the artId property.
     * 
     */
    public void setArtId(int value) {
        this.artId = value;
    }

    /**
     * Gets the value of the comNom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComNom() {
        return comNom;
    }

    /**
     * Sets the value of the comNom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComNom(String value) {
        this.comNom = value;
    }

    /**
     * Gets the value of the comEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComEmail() {
        return comEmail;
    }

    /**
     * Sets the value of the comEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComEmail(String value) {
        this.comEmail = value;
    }

    /**
     * Gets the value of the comTexte property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComTexte() {
        return comTexte;
    }

    /**
     * Sets the value of the comTexte property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComTexte(String value) {
        this.comTexte = value;
    }

    /**
     * Gets the value of the comDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getComDate() {
        return comDate;
    }

    /**
     * Sets the value of the comDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setComDate(XMLGregorianCalendar value) {
        this.comDate = value;
    }

    /**
     * Gets the value of the comValidation property.
     * 
     */
    public boolean isComValidation() {
        return comValidation;
    }

    /**
     * Sets the value of the comValidation property.
     * 
     */
    public void setComValidation(boolean value) {
        this.comValidation = value;
    }

}
