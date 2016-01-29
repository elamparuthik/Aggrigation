<%@ include file="includes.jsp" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!--A Design by W3layouts
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<html>
<head>
<title>Login form and sign up</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600' rel='stylesheet' type='text/css'>
<link href="${contextPath}/resources/css/style.css" rel="stylesheet" type="text/css" media="all" />
</head>
<body>
	<!----------start member-login----------->
		<div class="member-login">
			<!----------star form----------->
				<c:set var="loginError"><form:errors path="login"/></c:set>
        		<c:if test="${not empty  loginError}">  	 	
	              	 <div class="error " >
	                    <form:errors path="login"/>
	                </div>
                </c:if>
                
				<form:form modelAttribute="login" class="login"  action="Login" method="post" >
	
					<div class="formtitle">Member Login</div>
					
					<div class="input">
						<form:input path="email"  placeholder="email address" required="required"/>
					</div>
					<div class="input">
						<form:input path="password" type="password" placeholder="password" required="required"/>
					</div>
					<div class="buttons">
						<a href="SignUp">Sign Up</a>
						<input class="bluebutton" type="submit" value="Login" />
						<div class="clear"> </div>
					</div>
		
				</form:form>
				<!----------end form----------->
		</div>
		<!----------end member-login----------->
		
</body>
</html>
