package code.nighma.logging.utils;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogbackHighlightCompositeConverterExTest {

  @Test
  void fakeTestToDisplayLogs() {
    Logger logger = LoggerFactory.getLogger(LogbackHighlightCompositeConverterExTest.class);
    logger.error("Error");
    logger.warn("Warning");
    logger.info("Info");
    logger.debug("Debug");
    logger.trace("Trace");
  }

}