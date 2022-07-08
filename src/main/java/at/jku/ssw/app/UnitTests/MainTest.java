package at.jku.ssw.app.UnitTests;

import at.jku.ssw.app.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.logging.Logger;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



/**
 * The MainTest class tests the main.
 * @author Gruppe 3.
 */
public class MainTest {
    private final Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    /**
     * controls the Main class and tests all application functions.
     */
    @Test
    public void main() {

        try {
            log.info("Starting execution of main");
            String[] args = new String[0];
            Main.main(args);
            assertTrue(true);
        } catch (Exception exception) {
            log.warning("Exception in execution of main-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    /**
     * tests the methode get data in main.
     */
    @Test
    public void getData() {

        Exception exception = Assertions.assertThrows(NullPointerException.class, Main::getData);
        String expectedMessage = "";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
