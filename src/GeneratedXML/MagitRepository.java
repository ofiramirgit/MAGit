
package GeneratedXML;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


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
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="MagitBlobs">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MagitBlob" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="last-updater" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="last-update-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MagitFolders">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MagitSingleFolder" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="last-updater" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="last-update-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="items">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
 *                                         &lt;complexType>
 *                                           &lt;simpleContent>
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                               &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                                               &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                             &lt;/extension>
 *                                           &lt;/simpleContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                           &lt;attribute name="is-root" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MagitCommits">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="MagitSingleCommit" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="root-folder">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="date-of-creation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="preceding-commits" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="preceding-commit">
 *                                         &lt;complexType>
 *                                           &lt;simpleContent>
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                               &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                             &lt;/extension>
 *                                           &lt;/simpleContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="MagitBranches">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="head" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="MagitSingleBranch" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                             &lt;element name="pointed-commit">
 *                               &lt;complexType>
 *                                 &lt;simpleContent>
 *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *                                   &lt;/extension>
 *                                 &lt;/simpleContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "location",
    "magitBlobs",
    "magitFolders",
    "magitCommits",
    "magitBranches"
})
@XmlRootElement(name = "MagitRepository")
public class MagitRepository {

    @XmlElement(required = true)
    protected String location;
    @XmlElement(name = "MagitBlobs", required = true)
    protected MagitRepository.MagitBlobs magitBlobs;
    @XmlElement(name = "MagitFolders", required = true)
    protected MagitRepository.MagitFolders magitFolders;
    @XmlElement(name = "MagitCommits", required = true)
    protected MagitRepository.MagitCommits magitCommits;
    @XmlElement(name = "MagitBranches", required = true)
    protected MagitRepository.MagitBranches magitBranches;
    @XmlAttribute(name = "name")
    protected String name;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocation(String value) {
        this.location = value;
    }

    /**
     * Gets the value of the magitBlobs property.
     * 
     * @return
     *     possible object is
     *     {@link MagitRepository.MagitBlobs }
     *     
     */
    public MagitRepository.MagitBlobs getMagitBlobs() {
        return magitBlobs;
    }

    /**
     * Sets the value of the magitBlobs property.
     * 
     * @param value
     *     allowed object is
     *     {@link MagitRepository.MagitBlobs }
     *     
     */
    public void setMagitBlobs(MagitRepository.MagitBlobs value) {
        this.magitBlobs = value;
    }

    /**
     * Gets the value of the magitFolders property.
     * 
     * @return
     *     possible object is
     *     {@link MagitRepository.MagitFolders }
     *     
     */
    public MagitRepository.MagitFolders getMagitFolders() {
        return magitFolders;
    }

    /**
     * Sets the value of the magitFolders property.
     * 
     * @param value
     *     allowed object is
     *     {@link MagitRepository.MagitFolders }
     *     
     */
    public void setMagitFolders(MagitRepository.MagitFolders value) {
        this.magitFolders = value;
    }

    /**
     * Gets the value of the magitCommits property.
     * 
     * @return
     *     possible object is
     *     {@link MagitRepository.MagitCommits }
     *     
     */
    public MagitRepository.MagitCommits getMagitCommits() {
        return magitCommits;
    }

    /**
     * Sets the value of the magitCommits property.
     * 
     * @param value
     *     allowed object is
     *     {@link MagitRepository.MagitCommits }
     *     
     */
    public void setMagitCommits(MagitRepository.MagitCommits value) {
        this.magitCommits = value;
    }

    /**
     * Gets the value of the magitBranches property.
     * 
     * @return
     *     possible object is
     *     {@link MagitRepository.MagitBranches }
     *     
     */
    public MagitRepository.MagitBranches getMagitBranches() {
        return magitBranches;
    }

    /**
     * Sets the value of the magitBranches property.
     * 
     * @param value
     *     allowed object is
     *     {@link MagitRepository.MagitBranches }
     *     
     */
    public void setMagitBranches(MagitRepository.MagitBranches value) {
        this.magitBranches = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }


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
     *         &lt;element name="MagitBlob" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="last-updater" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="last-update-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "magitBlob"
    })
    public static class MagitBlobs {

        @XmlElement(name = "MagitBlob")
        protected List<MagitRepository.MagitBlobs.MagitBlob> magitBlob;

        /**
         * Gets the value of the magitBlob property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the magitBlob property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMagitBlob().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MagitRepository.MagitBlobs.MagitBlob }
         * 
         * 
         */
        public List<MagitRepository.MagitBlobs.MagitBlob> getMagitBlob() {
            if (magitBlob == null) {
                magitBlob = new ArrayList<MagitRepository.MagitBlobs.MagitBlob>();
            }
            return this.magitBlob;
        }


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
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="last-updater" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="last-update-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "name",
            "lastUpdater",
            "lastUpdateDate",
            "content"
        })
        public static class MagitBlob {

            @XmlElement(required = true)
            protected String name;
            @XmlElement(name = "last-updater", required = true)
            protected String lastUpdater;
            @XmlElement(name = "last-update-date", required = true)
            protected String lastUpdateDate;
            @XmlElement(required = true)
            protected String content;
            @XmlAttribute(name = "id")
            protected Byte id;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the lastUpdater property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastUpdater() {
                return lastUpdater;
            }

            /**
             * Sets the value of the lastUpdater property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastUpdater(String value) {
                this.lastUpdater = value;
            }

            /**
             * Gets the value of the lastUpdateDate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastUpdateDate() {
                return lastUpdateDate;
            }

            /**
             * Sets the value of the lastUpdateDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastUpdateDate(String value) {
                this.lastUpdateDate = value;
            }

            /**
             * Gets the value of the content property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getContent() {
                return content;
            }

            /**
             * Sets the value of the content property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setContent(String value) {
                this.content = value;
            }

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setId(Byte value) {
                this.id = value;
            }

        }

    }


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
     *         &lt;element name="head" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="MagitSingleBranch" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="pointed-commit">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "head",
        "magitSingleBranch"
    })
    public static class MagitBranches {

        @XmlElement(required = true)
        protected String head;
        @XmlElement(name = "MagitSingleBranch")
        protected List<MagitRepository.MagitBranches.MagitSingleBranch> magitSingleBranch;

        /**
         * Gets the value of the head property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHead() {
            return head;
        }

        /**
         * Sets the value of the head property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHead(String value) {
            this.head = value;
        }

        /**
         * Gets the value of the magitSingleBranch property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the magitSingleBranch property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMagitSingleBranch().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MagitRepository.MagitBranches.MagitSingleBranch }
         * 
         * 
         */
        public List<MagitRepository.MagitBranches.MagitSingleBranch> getMagitSingleBranch() {
            if (magitSingleBranch == null) {
                magitSingleBranch = new ArrayList<MagitRepository.MagitBranches.MagitSingleBranch>();
            }
            return this.magitSingleBranch;
        }


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
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="pointed-commit">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
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
            "name",
            "pointedCommit"
        })
        public static class MagitSingleBranch {

            @XmlElement(required = true)
            protected String name;
            @XmlElement(name = "pointed-commit", required = true)
            protected MagitRepository.MagitBranches.MagitSingleBranch.PointedCommit pointedCommit;

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the pointedCommit property.
             * 
             * @return
             *     possible object is
             *     {@link MagitRepository.MagitBranches.MagitSingleBranch.PointedCommit }
             *     
             */
            public MagitRepository.MagitBranches.MagitSingleBranch.PointedCommit getPointedCommit() {
                return pointedCommit;
            }

            /**
             * Sets the value of the pointedCommit property.
             * 
             * @param value
             *     allowed object is
             *     {@link MagitRepository.MagitBranches.MagitSingleBranch.PointedCommit }
             *     
             */
            public void setPointedCommit(MagitRepository.MagitBranches.MagitSingleBranch.PointedCommit value) {
                this.pointedCommit = value;
            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class PointedCommit {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "id")
                protected Byte id;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Byte }
                 *     
                 */
                public Byte getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Byte }
                 *     
                 */
                public void setId(Byte value) {
                    this.id = value;
                }

            }

        }

    }


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
     *         &lt;element name="MagitSingleCommit" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="root-folder">
     *                     &lt;complexType>
     *                       &lt;simpleContent>
     *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                         &lt;/extension>
     *                       &lt;/simpleContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="date-of-creation" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="preceding-commits" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="preceding-commit">
     *                               &lt;complexType>
     *                                 &lt;simpleContent>
     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                                   &lt;/extension>
     *                                 &lt;/simpleContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "magitSingleCommit"
    })
    public static class MagitCommits {

        @XmlElement(name = "MagitSingleCommit")
        protected List<MagitRepository.MagitCommits.MagitSingleCommit> magitSingleCommit;

        /**
         * Gets the value of the magitSingleCommit property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the magitSingleCommit property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMagitSingleCommit().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MagitRepository.MagitCommits.MagitSingleCommit }
         * 
         * 
         */
        public List<MagitRepository.MagitCommits.MagitSingleCommit> getMagitSingleCommit() {
            if (magitSingleCommit == null) {
                magitSingleCommit = new ArrayList<MagitRepository.MagitCommits.MagitSingleCommit>();
            }
            return this.magitSingleCommit;
        }


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
         *         &lt;element name="root-folder">
         *           &lt;complexType>
         *             &lt;simpleContent>
         *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *               &lt;/extension>
         *             &lt;/simpleContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="author" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="date-of-creation" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="preceding-commits" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="preceding-commit">
         *                     &lt;complexType>
         *                       &lt;simpleContent>
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *                         &lt;/extension>
         *                       &lt;/simpleContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "rootFolder",
            "message",
            "author",
            "dateOfCreation",
            "precedingCommits"
        })
        public static class MagitSingleCommit {

            @XmlElement(name = "root-folder", required = true)
            protected MagitRepository.MagitCommits.MagitSingleCommit.RootFolder rootFolder;
            @XmlElement(required = true)
            protected String message;
            @XmlElement(required = true)
            protected String author;
            @XmlElement(name = "date-of-creation", required = true)
            protected String dateOfCreation;
            @XmlElement(name = "preceding-commits")
            protected MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits precedingCommits;
            @XmlAttribute(name = "id")
            protected Byte id;

            /**
             * Gets the value of the rootFolder property.
             * 
             * @return
             *     possible object is
             *     {@link MagitRepository.MagitCommits.MagitSingleCommit.RootFolder }
             *     
             */
            public MagitRepository.MagitCommits.MagitSingleCommit.RootFolder getRootFolder() {
                return rootFolder;
            }

            /**
             * Sets the value of the rootFolder property.
             * 
             * @param value
             *     allowed object is
             *     {@link MagitRepository.MagitCommits.MagitSingleCommit.RootFolder }
             *     
             */
            public void setRootFolder(MagitRepository.MagitCommits.MagitSingleCommit.RootFolder value) {
                this.rootFolder = value;
            }

            /**
             * Gets the value of the message property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getMessage() {
                return message;
            }

            /**
             * Sets the value of the message property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setMessage(String value) {
                this.message = value;
            }

            /**
             * Gets the value of the author property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getAuthor() {
                return author;
            }

            /**
             * Sets the value of the author property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setAuthor(String value) {
                this.author = value;
            }

            /**
             * Gets the value of the dateOfCreation property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getDateOfCreation() {
                return dateOfCreation;
            }

            /**
             * Sets the value of the dateOfCreation property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setDateOfCreation(String value) {
                this.dateOfCreation = value;
            }

            /**
             * Gets the value of the precedingCommits property.
             * 
             * @return
             *     possible object is
             *     {@link MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits }
             *     
             */
            public MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits getPrecedingCommits() {
                return precedingCommits;
            }

            /**
             * Sets the value of the precedingCommits property.
             * 
             * @param value
             *     allowed object is
             *     {@link MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits }
             *     
             */
            public void setPrecedingCommits(MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits value) {
                this.precedingCommits = value;
            }

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setId(Byte value) {
                this.id = value;
            }


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
             *         &lt;element name="preceding-commit">
             *           &lt;complexType>
             *             &lt;simpleContent>
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
             *               &lt;/extension>
             *             &lt;/simpleContent>
             *           &lt;/complexType>
             *         &lt;/element>
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
                "precedingCommit"
            })
            public static class PrecedingCommits {

                @XmlElement(name = "preceding-commit", required = true)
                protected MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits.PrecedingCommit precedingCommit;

                /**
                 * Gets the value of the precedingCommit property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits.PrecedingCommit }
                 *     
                 */
                public MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits.PrecedingCommit getPrecedingCommit() {
                    return precedingCommit;
                }

                /**
                 * Sets the value of the precedingCommit property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits.PrecedingCommit }
                 *     
                 */
                public void setPrecedingCommit(MagitRepository.MagitCommits.MagitSingleCommit.PrecedingCommits.PrecedingCommit value) {
                    this.precedingCommit = value;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class PrecedingCommit {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "id")
                    protected Byte id;

                    /**
                     * Gets the value of the value property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * Gets the value of the id property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Byte }
                     *     
                     */
                    public Byte getId() {
                        return id;
                    }

                    /**
                     * Sets the value of the id property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Byte }
                     *     
                     */
                    public void setId(Byte value) {
                        this.id = value;
                    }

                }

            }


            /**
             * <p>Java class for anonymous complex type.
             * 
             * <p>The following schema fragment specifies the expected content contained within this class.
             * 
             * <pre>
             * &lt;complexType>
             *   &lt;simpleContent>
             *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
             *     &lt;/extension>
             *   &lt;/simpleContent>
             * &lt;/complexType>
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "value"
            })
            public static class RootFolder {

                @XmlValue
                protected String value;
                @XmlAttribute(name = "id")
                protected Byte id;

                /**
                 * Gets the value of the value property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getValue() {
                    return value;
                }

                /**
                 * Sets the value of the value property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setValue(String value) {
                    this.value = value;
                }

                /**
                 * Gets the value of the id property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link Byte }
                 *     
                 */
                public Byte getId() {
                    return id;
                }

                /**
                 * Sets the value of the id property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link Byte }
                 *     
                 */
                public void setId(Byte value) {
                    this.id = value;
                }

            }

        }

    }


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
     *         &lt;element name="MagitSingleFolder" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="last-updater" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="last-update-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                   &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="items">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
     *                               &lt;complexType>
     *                                 &lt;simpleContent>
     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *                                     &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                                     &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                                   &lt;/extension>
     *                                 &lt;/simpleContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
     *                 &lt;attribute name="is-root" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "magitSingleFolder"
    })
    public static class MagitFolders {

        @XmlElement(name = "MagitSingleFolder")
        protected List<MagitRepository.MagitFolders.MagitSingleFolder> magitSingleFolder;

        /**
         * Gets the value of the magitSingleFolder property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the magitSingleFolder property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMagitSingleFolder().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link MagitRepository.MagitFolders.MagitSingleFolder }
         * 
         * 
         */
        public List<MagitRepository.MagitFolders.MagitSingleFolder> getMagitSingleFolder() {
            if (magitSingleFolder == null) {
                magitSingleFolder = new ArrayList<MagitRepository.MagitFolders.MagitSingleFolder>();
            }
            return this.magitSingleFolder;
        }


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
         *         &lt;element name="last-updater" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="last-update-date" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="items">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
         *                     &lt;complexType>
         *                       &lt;simpleContent>
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
         *                           &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
         *                           &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *                         &lt;/extension>
         *                       &lt;/simpleContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *       &lt;/sequence>
         *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
         *       &lt;attribute name="is-root" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "lastUpdater",
            "lastUpdateDate",
            "name",
            "items"
        })
        public static class MagitSingleFolder {

            @XmlElement(name = "last-updater", required = true)
            protected String lastUpdater;
            @XmlElement(name = "last-update-date", required = true)
            protected String lastUpdateDate;
            protected String name;
            @XmlElement(required = true)
            protected MagitRepository.MagitFolders.MagitSingleFolder.Items items;
            @XmlAttribute(name = "id")
            protected Byte id;
            @XmlAttribute(name = "is-root")
            protected String isRoot;

            /**
             * Gets the value of the lastUpdater property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastUpdater() {
                return lastUpdater;
            }

            /**
             * Sets the value of the lastUpdater property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastUpdater(String value) {
                this.lastUpdater = value;
            }

            /**
             * Gets the value of the lastUpdateDate property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getLastUpdateDate() {
                return lastUpdateDate;
            }

            /**
             * Sets the value of the lastUpdateDate property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setLastUpdateDate(String value) {
                this.lastUpdateDate = value;
            }

            /**
             * Gets the value of the name property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getName() {
                return name;
            }

            /**
             * Sets the value of the name property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setName(String value) {
                this.name = value;
            }

            /**
             * Gets the value of the items property.
             * 
             * @return
             *     possible object is
             *     {@link MagitRepository.MagitFolders.MagitSingleFolder.Items }
             *     
             */
            public MagitRepository.MagitFolders.MagitSingleFolder.Items getItems() {
                return items;
            }

            /**
             * Sets the value of the items property.
             * 
             * @param value
             *     allowed object is
             *     {@link MagitRepository.MagitFolders.MagitSingleFolder.Items }
             *     
             */
            public void setItems(MagitRepository.MagitFolders.MagitSingleFolder.Items value) {
                this.items = value;
            }

            /**
             * Gets the value of the id property.
             * 
             * @return
             *     possible object is
             *     {@link Byte }
             *     
             */
            public Byte getId() {
                return id;
            }

            /**
             * Sets the value of the id property.
             * 
             * @param value
             *     allowed object is
             *     {@link Byte }
             *     
             */
            public void setId(Byte value) {
                this.id = value;
            }

            /**
             * Gets the value of the isRoot property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getIsRoot() {
                return isRoot;
            }

            /**
             * Sets the value of the isRoot property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setIsRoot(String value) {
                this.isRoot = value;
            }


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
             *         &lt;element name="item" maxOccurs="unbounded" minOccurs="0">
             *           &lt;complexType>
             *             &lt;simpleContent>
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
             *                 &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
             *                 &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
             *               &lt;/extension>
             *             &lt;/simpleContent>
             *           &lt;/complexType>
             *         &lt;/element>
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
                "item"
            })
            public static class Items {

                protected List<MagitRepository.MagitFolders.MagitSingleFolder.Items.Item> item;

                /**
                 * Gets the value of the item property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the item property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getItem().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link MagitRepository.MagitFolders.MagitSingleFolder.Items.Item }
                 * 
                 * 
                 */
                public List<MagitRepository.MagitFolders.MagitSingleFolder.Items.Item> getItem() {
                    if (item == null) {
                        item = new ArrayList<MagitRepository.MagitFolders.MagitSingleFolder.Items.Item>();
                    }
                    return this.item;
                }


                /**
                 * <p>Java class for anonymous complex type.
                 * 
                 * <p>The following schema fragment specifies the expected content contained within this class.
                 * 
                 * <pre>
                 * &lt;complexType>
                 *   &lt;simpleContent>
                 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
                 *       &lt;attribute name="type" type="{http://www.w3.org/2001/XMLSchema}string" />
                 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}byte" />
                 *     &lt;/extension>
                 *   &lt;/simpleContent>
                 * &lt;/complexType>
                 * </pre>
                 * 
                 * 
                 */
                @XmlAccessorType(XmlAccessType.FIELD)
                @XmlType(name = "", propOrder = {
                    "value"
                })
                public static class Item {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "type")
                    protected String type;
                    @XmlAttribute(name = "id")
                    protected Byte id;

                    /**
                     * Gets the value of the value property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getValue() {
                        return value;
                    }

                    /**
                     * Sets the value of the value property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setValue(String value) {
                        this.value = value;
                    }

                    /**
                     * Gets the value of the type property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getType() {
                        return type;
                    }

                    /**
                     * Sets the value of the type property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setType(String value) {
                        this.type = value;
                    }

                    /**
                     * Gets the value of the id property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link Byte }
                     *     
                     */
                    public Byte getId() {
                        return id;
                    }

                    /**
                     * Sets the value of the id property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link Byte }
                     *     
                     */
                    public void setId(Byte value) {
                        this.id = value;
                    }

                }

            }

        }

    }

}
