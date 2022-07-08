package at.jku.ssw.app.UnitTests.TcxparserTest;

import at.jku.ssw.model.TcxParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TcxParserTest {

    private TcxParser parser;

    @BeforeEach
    void setUp() throws JAXBException {
        parser = new TcxParser();
    }

    @Test
    void testParseTCXError() throws JAXBException {
        assertThrows(JAXBException.class, () -> parser.parseTCX(new InputStream() {
            @Override
            public int read() throws IOException {
                return 00000;
            }
        }));
    }
}

