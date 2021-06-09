package com.itheima.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

/**
 * 测试类 作用：测试activiti所需要的25张表的生成
 */
public class ActivitiTest {

	/**
	 * 表生成
	 */
	@Test
	public void testGenTable() {
		// 条件：1.activiti配置文件名称：activiti.cfg.xml 2.bean的id="processEngineConfiguration"
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println(processEngine);

		// HistoryService historyService = processEngine.getHistoryService();

	}

	// 流程定义部署
	@Test
	public void testDeployment() throws FileNotFoundException {
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

		// 获取资源相对路径
		String bpmnPath = "diagram/holiday.bpmn";
		String pngPath = "diagram/holiday.png";
		// 读取资源作为一个输入流
		FileInputStream bpmnfileInputStream = new FileInputStream(bpmnPath);
		FileInputStream pngfileInputStream = new FileInputStream(pngPath);

		Deployment deployment = processEngine.getRepositoryService()// 获取流程定义和部署对象相关的Service
				.createDeployment()// 创建部署对象
				.addInputStream("leave.bpmn", bpmnfileInputStream).addInputStream("leave.png", pngfileInputStream)
				.deploy();// 完成部署
		System.out.println("部署ID：" + deployment.getId());// 1
		System.out.println("部署时间：" + deployment.getDeploymentTime());

	}

//    @Test
//    public void testGenTable(){
//        //1.创建ProcessEngineConfiguration对象  第一个参数:配置文件名称  第二个参数是processEngineConfiguration的bean的id
//        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
//                .createProcessEngineConfigurationFromResource("activiti.cfg.xml","processEngineConfiguration01");
//        //2.创建ProcesEngine对象
//        ProcessEngine processEngine = configuration.buildProcessEngine();
//
//        //3.输出processEngine对象
//        System.out.println(processEngine);
//
//
//
//    }
}
