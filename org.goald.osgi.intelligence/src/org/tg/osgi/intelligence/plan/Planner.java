package org.tg.osgi.intelligence.plan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Logger;

//import goald.evaluation.ExperimentTimerImpl;
import goald.evaluation.fillingstation.FillingStationStudyCase;
import goald.evaluation.model.ExecResult;

//import javax.inject.Inject;

//import org.slf4j.Logger;

import goald.exputil.AbstractStudyCase;
import goald.exputil.ExperimentTimer;
import goald.exputil.WriteService;
import goald.model.Artifact;
import goald.model.ArtifactBuilder;
import goald.model.DeploymentRequest;
import goald.model.DeploymentRequestBuilder;
import goald.systems.Agent;
import goald.systems.AgentBuilder;
import goald.systems.DeploymentPlanningResult;
import goald.systems.IDeploymentPlanner;
import goald.systems.IRepository;
import goald.systems.PlanSelectionException;
import goald.systems.RepositoryBuilder;

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
	
	public Planner (){
		experimentName = "";
		scenario = new ArrayList<String>();
		timer = new ExperimentTimerImpl();
	}
	
	public void setExpName (String expName) {
		experimentName = expName;
	}
	
	public void setTimer () {
		timer = new ExperimentTimerImpl();
	}
	
	public void setScenario (List<String> scenario) {
		this.scenario = scenario;
	}
	
	public String getExpName () {
		return experimentName;
	}
	
	public List<String> getScenario () {
		return scenario;
	}
	
	public ExperimentTimerImpl getTimer () {
		return (ExperimentTimerImpl) timer;
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
	
	public void setResult (ExecResult res) {
		result = res;
	}
	
	public ExecResult getPlan () {
		try {
			exec();
		} catch (PlanSelectionException e) {
			System.out.println("ERROR: Could not do the planning for " + experimentName);
			e.printStackTrace();
			return null;
		}
		if (getResult() == null) {
			System.out.println("ERROR: Could not do the planning for " + experimentName);
			return null;
		}
		return result;
	}
	
	public void scenario(String experimentName, Consumer<AgentBuilder> exec) {
		
		//log.info("Executing experiment {}", experimentName); 
		//run execution
		timer.begin();
		
		result = new ExecResult();
		DeploymentRequest request = DeploymentRequestBuilder.create()
				.addGoal(experimentName)
				.build();
		
		
		result.setRequest(request);

		AgentBuilder agentBuilder =  AgentBuilder.create();
		exec.accept(agentBuilder);
		
		Agent agent = agentBuilder.build();

		DeploymentPlanningResult planningResult;
		try {
			timer.split("setup:" + experimentName);
			planningResult = planner.doPlan(request, agent);
			Number responseResult = timer.split("execution:" + experimentName);
			//ds.log(experimentName, responseResult);
		} catch (Exception e) {
			//log.error(e.getMessage());
			throw new RuntimeException(e);
		}
		
		result.setResultPlan(planningResult);
		Number responseResult = timer.split("execution:" + experimentName);

		validateResult(result);
		timer.split("validation");
		//echo(result.getResultPlan(), responseResult);
		timer.finish();
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
