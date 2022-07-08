package at.jku.ssw.app.UnitTests;
import at.jku.ssw.app.Main;
import at.jku.ssw.tcxparser.schema.TrainingCenterDatabaseT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;

public class MainTest {
    private Logger log = Logger.getLogger(String.valueOf(this.getClass()));

    @Test
    public void main() {
        try {
            log.info("Starting execution of main");
            String[] args = new String[0];
            Main main = new Main();
            main.main(args);
            assertTrue(true);
        } catch (Exception exception) {
            log.warning("Exception in execution of main-" + exception);
            exception.printStackTrace();
            assertFalse(false);
        }
    }

    @Test
    public void getData() {
       Exception exception = Assertions.assertThrows(NullPointerException.class, () -> {
           Main.getData();
       });
       String expectedMessage = "";
       String actualMessage = exception.getMessage();
       assertTrue(actualMessage.contains(expectedMessage));
    }
}