//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.12.17 at 10:24:25 PM EET 
//


package at.jku.ssw.model.schema;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TriggerMethod_t.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TriggerMethod_t">
 *   &lt;restriction base="{http://www.garmin.com/xmlschemas/TrainingCenterDatabase/v2}Token_t">
 *     &lt;enumeration value="Manual"/>
 *     &lt;enumeration value="Distance"/>
 *     &lt;enumeration value="Location"/>
 *     &lt;enumeration value="Time"/>
 *     &lt;enumeration value="HeartRate"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TriggerMethod_t")
@XmlEnum
public enum TriggerMethodT {

    @XmlEnumValue("Manual")
    MANUAL("Manual"),
    @XmlEnumValue("Distance")
    DISTANCE("Distance"),
    @XmlEnumValue("Location")
    LOCATION("Location"),
    @XmlEnumValue("Time")
    TIME("Time"),
    @XmlEnumValue("HeartRate")
    HEART_RATE("HeartRate");
    private final String value;

    TriggerMethodT(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TriggerMethodT fromValue(String v) {
        for (TriggerMethodT c: TriggerMethodT.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
