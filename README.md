# GoalD OSGi Execution Environment

Intro 
=====

This project intends to create an execution environment for the Goal-Driven Deployment Framework (https://github.com/lesunb/goald).
It is built on top of the OSGi technology for the creation and deployment of the bundles.

Bundles are created as java projects with OSGi metadata and exported with Maven to a repository.
The intelligence is responsible for starting an OSGi Framework, fetching the repository (with Apache Felix BundleRepository) and starting the bundles in order to provide a service.

## Setup

### JDK8 the compiler and virtual machine

Install JDK8: 
	http://www.oracle.com/technetwork/java/javase/downloads/index.html

	
### Eclipse (Neon) the IDE
	http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/

	
### Maven: Dependency Manager and Build	

It should come with Eclipse, don't worry.

### Apache Feliz Bundle Repository (2.0.10)
    https://mvnrepository.com/artifact/org.apache.felix/org.apache.felix.bundlerepository/2.0.10
    

## Building and Running GOALD

### clone repos: 
  * git clone https://github.com/jcosta9/OSGi.git
  * git clone https://github.com/lesunb/goald/ 

### Importing projects into Eclipse

 * Open Eclipse
 * File > Import > Existing Project
 * Find the folder where you cloned goald project
 * Accept the defaults

### Add Dependencies to the Project
 * Project > Preferences > Lib Path
 * Add GoalD and Apache Bundle Repository as a dependencies

### Running
 * Run as a Java Project

## Bugs? Doubts?

* Look for existing issues or create a new one describing your problem or doubt
* Contact the author by email
	* jpaulo.caraujo@gmail.com

<h1><img src="http://enroute.osgi.org/img/enroute-logo-64.png" witdh=40px style="float:left;margin: 0 1em 1em 0;width:40px">
OSGi enRoute Archetype</h1>

This repository represents a template workspace for bndtools, it is the easiest way to get started with OSGi enRoute. The workspace is useful in an IDE (bndtools or Intellij) and has support for [continuous integration][2] with [gradle][3]. If you want to get started with enRoute, then follow the steps in the [quick-start guide][1].

[1]: http://enroute.osgi.org/quick-start.html
[2]: http://enroute.osgi.org/tutorial_base/800-ci.html
[3]: https://www.gradle.org/