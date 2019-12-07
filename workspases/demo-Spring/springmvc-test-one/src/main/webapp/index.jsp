<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Hello World!</h2>
	<br>

	<a href="/springmvc/helloworld">Hello World</a>
	<br><br>

	<a href="springmvc/testRequestMapping">Test RequestMapping</a>
	<br><br>

	<form action="springmvc/testMethod" method="post">
		<input type="submit" value="submit"/>
	</form>
	<br><br>

	<a href="springmvc/testParamsAndHeaders?username=atguigu&age=10">Test ParamsAndHeaders</a>
	<br><br>

	<a href="springmvc/testAntPath/mnxyz/abc">Test AntPath</a>
	<br><br>
	
	<a href="springmvc/testPathVariable/1">Test PathVariable</a>
	<br><br>
	
	<a href="springmvc/testRest/1">Test Rest Get</a>
	<br><br>
	
	<form action="springmvc/testRest" method="post">
		<input type="submit" value="TestRest POST"/>
	</form>
	<br><br>
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="TestRest DELETE"/>
	</form>
	<br><br>
	
	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="TestRest PUT"/>
	</form>
	<br><br>
	
	
</body>
</html>
