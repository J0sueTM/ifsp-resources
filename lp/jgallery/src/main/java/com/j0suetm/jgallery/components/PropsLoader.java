package com.j0suetm.jgallery.components;

import java.lang.Thread;
import java.lang.ClassLoader;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

public class PropsLoader {
  private static final Logger logger =
    Logger.getLogger(PropsLoader.class.getName());

  public static Properties load(String resourceName) {
    ClassLoader classLdr = Thread
      .currentThread()
      .getContextClassLoader();

    Properties props = new Properties();
    try {
      InputStream rsrcStream = classLdr.getResourceAsStream(
        resourceName
      );
      props.load(rsrcStream);
    } catch (IOException ex) {
      logger.log(
        Level.SEVERE,
        "failed to load app props -- {0}",
        ex.getMessage()
      );

      return null;
    }

    logger.log(Level.FINE, "loaded app props");

    return props;
  }
}
