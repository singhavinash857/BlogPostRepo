<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<%-- <spring:url
	value="${pageContext.request.contextPath}/customCss/registrationPageCss.css"
	var="registerPageCss" />
<link href="${registerPageCss}" rel="stylesheet" /> --%>

<style>
body {
	background-image:
		url("${pageContext.request.contextPath}/images/yellowbackground.jpg");
	height: 100%;
}


/* input[type]{
  border-radius: 4px;
  background: transparent;
}  */

.custom-modal-header {
	text-align: center;
	color: #17b6bb;
	border-top: 4px solid;
}


#myModal .modal-dialog:after {
	content: '';
	height: 0px;
	width: 0px;
	/* border-right: 50px solid rgba(255, 0, 0, 0.98); */
	border-right: 50px solid #17b6bb;
	border-bottom: 50px solid transparent;
	position: absolute;
	top: 1px;
	right: 16px;
	z-index: 99;
}

#myModal .modal-dialog:before {
	content: '';
	height: 0px;
	width: 0px;
	border-left: 50px solid #17B6BB;
	border-right: 50px solid transparent;
	border-bottom: 50px solid transparent;
	position: absolute;
	top: 1px;
	left: -14px;
	z-index: 99;
}

#myModal .modal-dialog {
	padding: 0px;
	position: relative;
}

</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#btnSubmit")
								.click(
										function(e) {
											var valid = this.form
													.checkValidity();
											if (valid) {
												e.preventDefault();
												var form = $(this);
												saveBlogData();
											}
										});

						
						function saveBlogData() {
							var formData = {
								'blogTitle' : $(
										'input[name=blogTitle]').val(),
								'blogDescription' : $(
										'input[name=blogDescription]').val(),
							};

							console.log(formData);
							$
									.ajax({
										crossDomain : true,
										url : '/home/blog_controller/saveBlog',
										method : 'POST',
										dataType : 'json',
										contentType : "application/json; charset=utf-8",
										async : false,
										data : JSON.stringify(formData),
										success : function(data) {
											console.log(data.status);
											if (data.status) {
												alert(data.message);
											}
										},
										error : function(e) {
											var errors = e.responseJSON.errors;
											var errorString = '';

											$
													.each(
															errors,
															function(key, value) {
																errorString += value.errorDescription;
															});
											alert(errorString);
										}
									});
						}


					});
</script>

</head>
<body>
	<div id="header">
		<jsp:include page="/WEB-INF/jsp/headerPage/header.jsp" />
	</div>
	<br>
	<br>
	<br>
	<div class="container-fluid">
		<form>
			<div class="row register-form">
				<div class="col-md-4">
					<div class="form-group">
						<label><b>Blog Title* :</b></label> <input
							type="text" class="form-control" id="blogTitle"
							placeholder="Enter Blog Title.." name="blogTitle" value=""
							required />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label><b>Description :</b></label> <input
							type="text" class="form-control" id="blogDescription"
							placeholder="Enter Blog Description.." name="blogDescription" value=""
							required />
					</div>
				</div>
				

			</div>
			

			<div class="row">
				<div class="col-md-4">
					<button type="submit" value="Submit" class="btn btn-primary"
						id="btnSubmit">Post</button>
				</div>
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
			</div>
		</form>
		</div>
</body>
</html>
