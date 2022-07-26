package at.jku.ssw.model;

import at.jku.ssw.model.schema.TrainingCenterDatabaseT;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * Provides a JAXB Unmarshaller which deserializes the XML data into TrainingCenterDatabaseT objects.
 *
 * @author Gruppe 3
 */

public class TcxParser {

    private final Unmarshaller jaxbUnmarshaller;

    public TcxParser() throws JAXBException {
        System.setProperty("com.sun.xml.bind.v2.runtime.reflect.opt.OptimizedAccessorFactory.noOptimization", "true");
        JAXBContext jaxbContext = JAXBContext.newInstance(TrainingCenterDatabaseT.class);
        jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    }

    /**
     * Parses a stream containing TCX data
     *
     * @param stream the input stream
     * @return {@link TrainingCenterDatabaseT} object containing parsed data
     * @throws Exception when TCX file is invalid
     */
    public TrainingCenterDatabaseT parseTCX(InputStream stream) throws JAXBException {
        Source source = new StreamSource(stream);
        JAXBElement<TrainingCenterDatabaseT> root = jaxbUnmarshaller.unmarshal(source, TrainingCenterDatabaseT.class);
        return root.getValue();
    }
}
