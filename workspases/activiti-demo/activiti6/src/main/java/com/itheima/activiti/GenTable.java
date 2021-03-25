package com.itheima.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

public class GenTable {
	
	/**
	 * 表生成
	 */

	public static void main(String[] args) {
		// 条件：1.activiti配置文件名称：activiti.cfg.xml 2.bean的id="processEngineConfiguration"
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		System.out.println(processEngine);

		// HistoryService historyService = processEngine.getHistoryService();

	}

}
