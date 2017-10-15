package org.tg.osgi.intelligence.plan;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import goalp.evaluation.ExperimentTimerImpl;
import goalp.evaluation.fillingstation.FillingStationStudyCase;
import goalp.evaluation.model.ExecResult;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import javax.inject.Inject;

//import javax.inject.Inject;

//import org.slf4j.Logger;

import goalp.exputil.AbstractStudyCase;
import goalp.exputil.ExperimentTimer;
import goalp.exputil.WriteService;
import goalp.model.Artifact;
import goalp.model.ArtifactBuilder;
import goalp.systems.AgentBuilder;
import goalp.systems.IDeploymentPlanner;
import goalp.systems.IRepository;
import goalp.systems.RepositoryBuilder;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.bindings.Parameters;
import org.jboss.weld.environment.se.events.ContainerInitialized;

public class Planner extends AbstractStudyCase {
	
	String experimentName;
	List<String> scenario;
	AgentBuilder agent;
	/*
	IDeploymentPlanner planner;
	
	IRepository repo;
	
	@Inject
	Logger log;
	
	@Inject
	ExperimentTimerImpl timer;
	
	@Inject
	WriteService write;
	
	ExecResult result;
	*/
	public Planner (String experimentName, List<String> scenario) {
		
		this.experimentName = experimentName;
		this.scenario = scenario;
		
		timer = new ExperimentTimerImpl();
		
	}
	
	@Override
	public void caseStudy() {
		scenario(experimentName, agent->{
			agent.addContexts(scenario);
		});
		
	}
	
	public ExecResult getResult() {
		return result;
	}
	
	@Override
	protected void setupEnvironment(RepositoryBuilder repositoryBuilder) {
		repositoryBuilder
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g0")
			//.provides("unb.tg.osgi.g0")
			.dependsOn("unb.tg.osgi.g1.api")
			.dependsOn("unb.tg.osgi.g2.api")
			.dependsOn("unb.tg.osgi.g3.api")
			.dependsOn("unb.tg.osgi.g4.api")
			.dependsOn("unb.tg.osgi.g5.api")
			.dependsOn("unb.tg.osgi.g1")
			.dependsOn("unb.tg.osgi.g2")
			.dependsOn("unb.tg.osgi.g3")
			.dependsOn("unb.tg.osgi.g4")
			.dependsOn("unb.tg.osgi.g5")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g1.api")
			.provides("unb.tg.osgi.g1.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g1.impl.p1")
			.provides("unb.tg.osgi.g1")
			.dependsOn("unb.tg.osgi.g1.api")
			.condition("gps_capability")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g1.impl.p2")
			.provides("unb.tg.osgi.g1")
			.dependsOn("unb.tg.osgi.g1.api")
			.condition("antenna_triangulation")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.api")
			.provides("unb.tg.osgi.g2.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p3")
			.provides("unb.tg.osgi.g2")
			.dependsOn("unb.tg.osgi.g2.api")
			.condition("protocol_on_board_computer_get_distante_to_empty")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p4")
			.provides("unb.tg.osgi.g2")
			.dependsOn("unb.tg.osgi.g2.impl.p4.p5.api")
			.dependsOn("unb.tg.osgi.g2.impl.p4.p6.api")
			.dependsOn("unb.tg.osgi.g2.impl.p4.p5")
			.dependsOn("unb.tg.osgi.g2.impl.p4.p6")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p4.p5.api")
			.provides("unb.tg.osgi.g2.impl.p4.p5.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p4.p5.impl")
			.provides("unb.tg.osgi.g2.impl.p4.p5")
			.dependsOn("unb.tg.osgi.g2.impl.p4.p5.api")
			.condition("protocol_get_fuel_level_and_mileage")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p4.p6.api")
			.provides("unb.tg.osgi.g2.impl.p4.p6.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p4.p6.impl")
			.provides("unb.tg.osgi.g2.impl.p4.p6")
			.dependsOn("unb.tg.osgi.g2.impl.p4.p6.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p7")
			.provides("unb.tg.osgi.g2")
			.dependsOn("unb.tg.osgi.g1.api")
			.dependsOn("unb.tg.osgi.g2.api")
			.dependsOn("unb.tg.osgi.g2.impl.p7.p8.api")
			.dependsOn("unb.tg.osgi.g1")
			.dependsOn("unb.tg.osgi.g2.impl.p7.p8")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p7.p8.api")
			.provides("unb.tg.osgi.g2.impl.p7.p8.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g2.impl.p7.p8.impl")
			.provides("unb.tg.osgi.g2.impl.p7.p8")
			.dependsOn("unb.tg.osgi.g2.impl.p7.p8.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g3.api")
			.provides("unb.tg.osgi.g3.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g3.impl.p10")
			.provides("unb.tg.osgi.g3")
			.dependsOn("unb.tg.osgi.g3.api")
			.condition("internet_connection")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g3.impl.p11")
			.provides("unb.tg.osgi.g3")
			.dependsOn("unb.tg.osgi.g3.api")
			.condition("storage")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g4.api")
			.provides("unb.tg.osgi.g4.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g4.impl")
			.provides("unb.tg.osgi.g4")
			.dependsOn("unb.tg.osgi.g4.api")
			.dependsOn("unb.tg.osgi.g4.impl.p12.api")
			.dependsOn("unb.tg.osgi.g4.impl.p13.api")
			.dependsOn("unb.tg.osgi.g4.impl.p12")
			.dependsOn("unb.tg.osgi.g4.impl.p13")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g4.impl.p12.api")
			.provides("unb.tg.osgi.g4.impl.p12.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g4.impl.p12.impl")
			.provides("unb.tg.osgi.g4.impl.p12")
			.dependsOn("unb.tg.osgi.g4.impl.p12.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g4.impl.p13.api")
			.provides("unb.tg.osgi.g4.impl.p13.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g4.impl.p13.impl")
			.provides("unb.tg.osgi.g4.impl.p13")
			.dependsOn("unb.tg.osgi.g4.impl.p13.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.api")
			.provides("unb.tg.osgi.g5.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.impl.p14")
			.provides("unb.tg.osgi.g5")
			.dependsOn("unb.tg.osgi.g5.api")
			.condition("interface_navigation_system")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.impl.p15.and")
			.provides("unb.tg.osgi.g5")
			.dependsOn("unb.tg.osgi.g5.api")
			.dependsOn("unb.tg.osgi.g5.impl.p15.api")
			.dependsOn("unb.tg.osgi.g5.impl.p15")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.impl.p15.api")
			.provides("unb.tg.osgi.g5.impl.p15.api")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.impl.p15.impl.p16")
			.provides("unb.tg.osgi.g5.impl.p15")
			.dependsOn("unb.tg.osgi.g5.impl.p15.api")
			.condition("synthesized_voice")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.impl.p15.impl.p17")
			.provides("unb.tg.osgi.g5.impl.p15")
			.dependsOn("unb.tg.osgi.g5.impl.p15.api")
			.condition("sound")
			.build())
		.add(
			ArtifactBuilder.create()
			.identification("unb.tg.osgi.g5.impl.p18")
			.provides("unb.tg.osgi.g5")
			.dependsOn("unb.tg.osgi.g5.api")
			.condition("visible_graphical_interface")
			.build());
	}

}
