Logback Custom Highlighting Color
====================

logback provides `%highlight` keyword for coloring output based on log level. It's a very useful but less known feature. One problem is that the default color scheme is too dark to read on a black/dark console.

Here is a converter allowing you to customize the color scheme. What you need to do is simply:

1. Add `HighlightingCompositeConverterEx.java` to CLASSPATH (or just add it to your project)
1. load the converter class in `logback.xml` by using `<conversionRule>`
1. Use your conversion word (`highlightex` in the example) in `<pattern>`

```Java
package code.nighma.logging.utils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.color.ANSIConstants;
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase;

public class LogbackHighlightCompositeConverterEx extends ForegroundCompositeConverterBase<ILoggingEvent> {

  @Override
  protected String getForegroundColorCode(ILoggingEvent event) {
    Level level = event.getLevel();
    return switch (level.toInt()) {
      case Level.ERROR_INT -> ANSIConstants.BOLD + ANSIConstants.RED_FG;
      case Level.WARN_INT -> ANSIConstants.BOLD + ANSIConstants.MAGENTA_FG;
      case Level.INFO_INT -> ANSIConstants.BOLD + ANSIConstants.CYAN_FG;
      case Level.DEBUG_INT -> ANSIConstants.BOLD + ANSIConstants.YELLOW_FG;
      case Level.TRACE_INT -> ANSIConstants.BOLD + ANSIConstants.WHITE_FG;
      default -> ANSIConstants.BOLD + ANSIConstants.DEFAULT_FG;
    };
  }
}
```

```XML
<configuration>

  <conversionRule conversionWord="highlightex" converterClass="code.nighma.logging.utils.HighlightingCompositeConverterEx"/>

  <appender name="Console" class="ch.qos.logback.core.ConsoleAppender">
    <layout class="ch.qos.logback.classic.PatternLayout">
      <Pattern>%green(%d{ISO8601}) [%blue(%t)] %highlightex(%-5level) %yellow(%C{5}): %msg%n%throwable</Pattern>
    </layout>
  </appender>

  <root level="info">
    <appender-ref ref="Console"/>
  </root>

  <logger name="code.nighma" level="trace" additivity="false">
    <appender-ref ref="Console"/>
  </logger>

</configuration>
```
