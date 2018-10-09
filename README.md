This is just a tutorial application. It is a quite basic and simple contact manager.

My goals are:
* learn Groovy
* learn JavaFX 
* learn the Griffon framework
* use data binding
* use JPA/Hibernate application managed persistence with Groovy

# Preparations

The first thing is to get the IDE up and running. I use eclipse so there is no perfect "works out of the box" experience.

> Eclipse IDE for Java Developers
> Version: Oxygen.2 Release (4.7.2)

## Groovy Plugin

The Groovy plugin for eclipse is [here](https://github.com/groovy/groovy-eclipse/wiki) available. 

> Groovy compiler 2.4.13.

Attention! There is a [regression](https://github.com/groovy/groovy-eclipse/issues/370) with greclipse and buildship (gradle plugin) at the moment (2018-07-24).

## Sdkman

The next step was the installation of [sdkman](https://sdkman.io/). Sdkman is a tool that allows you to install different JDKs on a host. It enables you to switch between OpenJDK versions, Oracle JDK versions, ... within seconds. I wanted to use Oracle JDK without interfering with the OpenJDK installed as default on Suse Tumbleweed. Oracle JDK brings JavaFX. Unfortunately there is not OpenFx package for Suse Tumbleweed.

BTW: Sdkman supports a bunch of other tools as they are: Gradle, Grails, ...

## lazybones

[Lazybones](https://github.com/pledbrook/lazybones) is a project and code generator for different tools and frameworks. You can install it with sdkman.



### Installation

Install Lazybones with sdkman:

`sdk install lazybones 0.8.3`


### Configuration

Lazybones needs extra proxy settings. Apart from that it needs the URL to find the griffon templates. You can enter this information in  "$HOME/.lazybones/config.groovy":
```groovy
systemProp {
    http {
        proxyHost = "proxy"
        proxyPort = 3128
    }   
    https {
        proxyHost = "proxy"
        proxyPort = 3128
    }
}

bintrayRepositories = [ 
    'griffon/griffon-lazybones-templates',
    'pledbrook/lazybones-templates'
]
```

# First test - create griffon project from scratch


## Create Project

Go to your eclipse workspace directory and call lazybones


```
lazybones create griffon-javafx-groovy groovyGriffonSample
```

## Run Project

Change directory to groovyGriffonSample and call this instruction:

```
gradle run
```

Now command line gradle builds the project and start the app.

## Eclipse

### Import project

Import project with menu file, import. Import as gradle project. Now you have to make some further steps to convert eclipse into a griffon compatible IDE.

### JRE Access Rules

Eclipse does not automatically allow access to classes of ifxrt.jar. You have to add an access rule. Go to project settings, build path, libraries, JRE (open this tree fragment) and add an access rule that allows access for `javafx/**`.

Look here: https://stackoverflow.com/questions/22812488/using-javafx-in-jre-8



### APT Annotation Processing Tool

Now you have to activate APT. You can enable it in the project settings dialogue section annotation processing.

Additionally you have to add some jar files to the factory path at the same section:

* griffon-groovy-compile-...jar"
* griffon-core-compile-...jar"
* jipsy-...jar
* gipsy-...jar


### Syntax errors

Eclipse has problems to "interpret" some of the annotations right and therefore shows some syntax errors. To avoid this:

* Controllers must inherit from AbstractGriffonController
* Models must inherit from AbstractGriffonModel


## Create another MVCGroup

This is not necessary for the first test. I just want to demonstrate how lazybones works.

In the project directory  call:

```
lazybones generate artifact
```

or more direct

```
lazybones generate artifact::mvcgroup
```

# Get started with this project

## Check out project

* open git perspective
* choose clone clone a git repository
* enter URI https://github.com/dumischbaenger/griffonGroovyJavaFxDBaccessSample.git
* follow the assistant

## Import project

* use menu *file*, *import* to import the gradle project

# The application itself

The application consists of:

* Logging
* Multiple MVC Groups
* FX Views defined within FXML Files
* Griffon JPA plugin
* One service for authentication against the database
* One service accessing the database
* Multiple entity beans

## Logging

The app uses *SimpleLogger* via *SLF4J*. The file `.../src/main/resources/simplelogger.properties` contains the complete logger configuration. In order to force *hibernate* to use *SimpleLogger* too, I added this command `System.setProperty("org.jboss.logging.provider", "slf4j");` in `Launcher.groovy`. It must be called in a *static* context.
