package com.test.spring.springmvc_test_one;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Hello world!
 *
 */
@Controller
@RequestMapping("/springmvc")
public class App {
	
	private static final String SUCCESS = "success";
	
	//��һ������
	@RequestMapping("helloworld")
	public String helloWorld() {
		return SUCCESS;
	}
	
	/**
	 * 1. @RequestMapping �������η���, ������������ 2. 1). �ඨ�崦: �ṩ����������ӳ����Ϣ������� WEB Ӧ�õĸ�Ŀ¼
	 * 2). ������: �ṩ��һ����ϸ��ӳ����Ϣ�� ������ඨ�崦�� URL�����ඨ�崦δ��ע @RequestMapping���򷽷�����ǵ� URL
	 * ����� WEB Ӧ�õĸ�Ŀ¼
	 */
	@RequestMapping("/testRequestMapping")
	public String testRequestMapping() {
		System.out.println("testRequestMapping");
		return SUCCESS;
	}
	
	
	/**
	 * ����: ʹ�� method ������ָ������ʽ
	 */
	@RequestMapping(value = "/testMethod",method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	/**
	 *  headers = { "Accept-Language=zh-CN,zh;q=0.8"}  ����404
	 * @return
	 */
	@RequestMapping(value = "/testParamsAndHeaders",params = { "username",
	"age" },method = RequestMethod.GET)
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
	
	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
	
	/**
	 * @PathVariable ������ӳ�� URL �е�ռλ����Ŀ�귽���Ĳ�����.
	 * @param id
	 * @return
	 */
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable: " + id);
		return SUCCESS;
	}
	
	
	/**
	 * Rest ���� URL. �� CRUD Ϊ��: ����: /order POST �޸�: /order/1 PUT update?id=1 ��ȡ:
	 * /order/1 GET get?id=1 ɾ��: /order/1 DELETE delete?id=1
	 * 
	 * ��η��� PUT ����� DELETE ������ ? 1. ��Ҫ���� HiddenHttpMethodFilter 2. ��Ҫ���� POST ����
	 * 3. ��Ҫ�ڷ��� POST ����ʱЯ��һ�� name="_method" ��������, ֵΪ DELETE �� PUT
	 * 
	 * �� SpringMVC ��Ŀ�귽������εõ� id ��? ʹ�� @PathVariable ע��
	 * 
	 */
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest GET: " + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest", method = RequestMethod.POST)
	public String testRest() {
		System.out.println("testRest POST");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.DELETE)
	public String testRestDelete(@PathVariable Integer id) {
		System.out.println("testRest Delete: " + id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}", method = RequestMethod.PUT)
	public String testRestPut(@PathVariable Integer id) {
		System.out.println("testRest Put: " + id);
		return SUCCESS;
	}


	
}
