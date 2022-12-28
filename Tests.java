import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){
        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()-> test.JvmUtilities.objectFootprint(s1));

        logger.info(()-> test.JvmUtilities.objectFootprint(s1,s2));

        logger.info(()-> test.JvmUtilities.objectTotalSize(s1));

        logger.info(() -> test.JvmUtilities.jvmInfo());
    }
}
