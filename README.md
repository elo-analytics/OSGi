# GoalD OSGi Execution Environment

Intro 
=====

This project intends to create an execution environment for the Goal-Driven Deployment Framework (https://github.com/lesunb/goald/wiki/Goald[GoalD])



= Setup

== JDK8 the compiler and virtual machine

Install JDK8: 
	http://www.oracle.com/technetwork/java/javase/downloads/index.html

	
== Eclipse (Mars) the IDE
	http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/mars2

	
== Maven: Dependency Manager and Build	

It should come with Eclipse, don't worry.


= Building and Running GOALD

 * clone the repo: 
  $ git clone https://github.com/lesunb/goald/ 

== Importing project into Eclipse

 * Open Eclipse
 * File > Import > Existing Project
 * Find the folder where you cloned goald project
 * Accept the defaults

== Run Tests into Eclipse

 * Right click in the goald-core project
 * Run As > JUnit Tests

== Run Scalability Evaluation

 * Right click in the evaluation project
 * Run As > Java Application
 * Choose EvaluationMain class
 * The evaluation experiment should init and you should see logs into the console of Eclipse.
 * Final result should be created at evaluation > result
 * Evaluation param can be changed at the class
 
goald.evaluation.plans.CreateExperimentsToEvaluateScalabilityOverNumberOfGoalsAndContexts3d


== Bugs? Doubts?

* Look for existing issues or create a new one describing your problem or doubt
* Contact the author by email
	* gabrielsr@gmail.com

<h1><img src="http://enroute.osgi.org/img/enroute-logo-64.png" witdh=40px style="float:left;margin: 0 1em 1em 0;width:40px">
OSGi enRoute Archetype</h1>

This repository represents a template workspace for bndtools, it is the easiest way to get started with OSGi enRoute. The workspace is useful in an IDE (bndtools or Intellij) and has support for [continuous integration][2] with [gradle][3]. If you want to get started with enRoute, then follow the steps in the [quick-start guide][1].

[1]: http://enroute.osgi.org/quick-start.html
[2]: http://enroute.osgi.org/tutorial_base/800-ci.html
[3]: https://www.gradle.org/