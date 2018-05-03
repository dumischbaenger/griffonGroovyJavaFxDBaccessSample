package de.dumischbaenger

import griffon.javafx.JavaFXGriffonApplication

public class Launcher {
  static {
    //BD Tell Hibernate to use SLF4J
    //BD https://stackoverflow.com/questions/11639997/how-do-you-configure-logging-in-hibernate-4-to-use-slf4j
    System.setProperty("org.jboss.logging.provider", "slf4j");
    System.setProperty("griffon.full.stacktrace", "true");
  }
  public static void main(String[] args) throws Exception {
    JavaFXGriffonApplication.main(args)
  }
}