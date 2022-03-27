//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.17 at 10:24:25 PM EET 
//


package at.jku.ssw.tcxparser.schema;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TrainingCenterDatabase_t complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TrainingCenterDatabase_t">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Folders" type="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}Folders_t" minOccurs="0"/>
 *         &lt;element name="Activities" type="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}ActivityList_t" minOccurs="0"/>
 *         &lt;element name="Workouts" type="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}WorkoutList_t" minOccurs="0"/>
 *         &lt;element name="Courses" type="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}CourseList_t" minOccurs="0"/>
 *         &lt;element name="Author" type="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}AbstractSource_t" minOccurs="0"/>
 *         &lt;element name="Extensions" type="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}Extensions_t" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TrainingCenterDatabase_t", propOrder = {
    "folders",
    "activities",
    "workouts",
    "courses",
    "author",
    "extensions"
})
public class TrainingCenterDatabaseT {

    @XmlElement(name = "Folders")
    protected FoldersT folders;
    @XmlElement(name = "Activities")
    protected ActivityListT activities;
    @XmlElement(name = "Workouts")
    protected WorkoutListT workouts;
    @XmlElement(name = "Courses")
    protected CourseListT courses;
    @XmlElement(name = "Author")
    protected AbstractSourceT author;
    @XmlElement(name = "Extensions")
    protected ExtensionsT extensions;

    /**
     * Gets the value of the folders property.
     * 
     * @return
     *     possible object is
     *     {@link FoldersT }
     *     
     */
    public FoldersT getFolders() {
        return folders;
    }

    /**
     * Sets the value of the folders property.
     * 
     * @param value
     *     allowed object is
     *     {@link FoldersT }
     *     
     */
    public void setFolders(FoldersT value) {
        this.folders = value;
    }

    /**
     * Gets the value of the activities property.
     * 
     * @return
     *     possible object is
     *     {@link ActivityListT }
     *     
     */
    public ActivityListT getActivities() {
        return activities;
    }

    /**
     * Sets the value of the activities property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActivityListT }
     *     
     */
    public void setActivities(ActivityListT value) {
        this.activities = value;
    }

    /**
     * Gets the value of the workouts property.
     * 
     * @return
     *     possible object is
     *     {@link WorkoutListT }
     *     
     */
    public WorkoutListT getWorkouts() {
        return workouts;
    }

    /**
     * Sets the value of the workouts property.
     * 
     * @param value
     *     allowed object is
     *     {@link WorkoutListT }
     *     
     */
    public void setWorkouts(WorkoutListT value) {
        this.workouts = value;
    }

    /**
     * Gets the value of the courses property.
     * 
     * @return
     *     possible object is
     *     {@link CourseListT }
     *     
     */
    public CourseListT getCourses() {
        return courses;
    }

    /**
     * Sets the value of the courses property.
     * 
     * @param value
     *     allowed object is
     *     {@link CourseListT }
     *     
     */
    public void setCourses(CourseListT value) {
        this.courses = value;
    }

    /**
     * Gets the value of the author property.
     * 
     * @return
     *     possible object is
     *     {@link AbstractSourceT }
     *     
     */
    public AbstractSourceT getAuthor() {
        return author;
    }

    /**
     * Sets the value of the author property.
     * 
     * @param value
     *     allowed object is
     *     {@link AbstractSourceT }
     *     
     */
    public void setAuthor(AbstractSourceT value) {
        this.author = value;
    }

    /**
     * Gets the value of the extensions property.
     * 
     * @return
     *     possible object is
     *     {@link ExtensionsT }
     *     
     */
    public ExtensionsT getExtensions() {
        return extensions;
    }

    /**
     * Sets the value of the extensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtensionsT }
     *     
     */
    public void setExtensions(ExtensionsT value) {
        this.extensions = value;
    }

}
