<%@ include file="includes.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>URL Aggrigate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="resources/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="resources/css/main_style.css">
</head>
<body>
    <div class="container">
        <div class="page-header col-sm-8">
            <h1>URL Aggrigate</h1>
            <p class="lead col-sm-5" >
				<div class="panel panel-default">
                    <div class="panel-body">
                        
                        
                        <c:set var="AggrigationError"><form:errors path="aggriBean"/></c:set>
		        		<c:if test="${not empty  AggrigationError}">  	 	
			              	 <div class="alert alert-danger">
	                            <button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">×</span><span class="sr-only">Close</span>
	                            </button>
	                            <form:errors path="aggriBean"/>
	                        </div>
		                </c:if>
                        <div style="clear: both;"></div>
                        
                        <form:form modelAttribute="aggriBean" method="post" role="form" action="Welcome">
                            <div class="form-group col-sm-6">
                            	Title
                               <form:input path="title" class="form-control" placeholder="title" required="required"/>
                            </div>
                            <div class="form-group col-sm-6">
                            	URL
                               <form:input path="url" type="input" class="form-control" placeholder="URL" required="required"/>
                            </div>
                            <div class="form-group ">
                            	Description
                            	<form:textarea path="description" class="form-control" required="required"/>
                            </div>
                            <div class="text-center">
                            	<button type="submit" class="btn btn-primary pull-right" >Submit</button>
                                	
                            </div>
                        </form:form>
                    </div>
                </div>
				
			</p>
        </div>
        <br style="clear: both;">
        <div class="list-group">
        	
            <c:forEach items="${Aggri.AggriDBList}" var="lists">     
	            <div class="col-sm-12 list-group-item">
		            <a target="_blank" href="${lists.url}" >
	                	<h4 class="list-group-item-heading">${lists.title}</h4>
		            </a>
		            <p class="list-group-item-text">${lists.description}</p>
		            <a href="#" class="btn btn-default btn-sm">Edit<i class="fa fa-share"></i></a>
	            </div>
             </c:forEach>
           
        </div>
    </div>
</body>
</html>