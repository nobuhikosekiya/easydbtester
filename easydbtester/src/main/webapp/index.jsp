<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-31j">
<title>SQL</title>
</head>
<body>
<h1>Select Query Form</h1>
<form method="get" action="select">
	<p>	Use DefaultDatasouce: 
		<input type="radio" name="useDefaultDatasource" id="useDefaultDatasource" value="true">true
		<input type="radio" name="useDefaultDatasource" id="useDefaultDatasource" value="false" checked="true">false
		<br />
		# Input JDBC JNDI if DefaultDatasouce is not used
		<br />
		JDBC JNDI: <input type="text" name="datasource" id="datasource" size="10" /><br/>
		SQL Query: <input type="text" name="sql" id="sql" size="100" /><br/>
 		Loop count: <input type="text" name="loop" id="loop" size="3" value="1"/><br/>
 		Display Error: 
 		<input type="radio" name="displayerror" id="displayerror" value="true" checked="true">true
		<input type="radio" name="displayerror" id="displayerror" value="false">false
		<br />
	</p>
	
	<input type="submit" value="submit">
</form>
<h1>Update Form</h1>
<form method="get" action="update">
	<p>	Use DefaultDatasouce: 
		<input type="radio" name="useDefaultDatasource" id="useDefaultDatasource" value="true">true
		<input type="radio" name="useDefaultDatasource" id="useDefaultDatasource" value="false" checked="true">false
		<br />
		# Input JDBC JNDI if DefaultDatasouce is not used
		<br />
		JDBC JNDI: <input type="text" name="datasource" id="datasource" size="10" /><br/>
		SQL Query: <input type="text" name="sql" id="sql" size="100" /><br/>
 		Loop count: <input type="text" name="loop" id="loop" size="3" value="1"/><br/>
 		Display Error: 
 		<input type="radio" name="displayerror" id="displayerror" value="true" checked="true">true
		<input type="radio" name="displayerror" id="displayerror" value="false">false
		<br />
	</p>
	
	<input type="submit" value="submit">
</form>
</body>
</html>