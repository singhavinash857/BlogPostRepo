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
												var hiddenEmployeeId = document
														.getElementById("employeeId").value;
												if (hiddenEmployeeId == "") {
													console
															.log("save employee calling ::");
													saveEmployeeData();
												} 
											}
										});
						function saveEmployeeData() {

							var formData = {
								'employeeFirstName' : $(
										'input[name=employeeFirstName]').val(),
								'employeeLastName' : $(
										'input[name=employeeLastName]').val(),
								'employeeMailId' : $(
										'input[name=employeeMailId]').val(),
								'employeePassword' : $(
										'input[name=employeePassword]').val(),
								'employeeConfirmPassword' : $(
										'input[name=employeeConfirmPassword]')
										.val(),
								'employeeRole' : [ $(
										"#employeeRoles option:selected").val() ]

							};

							console.log(formData);
							$
									.ajax({
										crossDomain : true,
										url : '/home/employee_controller/saveEmployee',
										method : 'POST',
										dataType : 'json',
										contentType : "application/json; charset=utf-8",
										async : false,
										data : JSON.stringify(formData),
										success : function(data) {
											console.log(data.status);
											if (data.status) {
												alert(data.message);
												loadData(currentPageNumber,pageSize);
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

<script type="text/javascript">
	function isPasswordMatch() {
		var password = $("#txtPassword").val();
		var confirmPassword = $("#txtConfirmPassword").val();

		if (password != confirmPassword)
			$("#divCheckPasswordMatch").html("Passwords do not match!").css(
					'color', 'red');
		else
			$("#divCheckPasswordMatch").html("Passwords match.").css('color',
					'green');
	}

	$(document).ready(function() {
		$("#txtConfirmPassword").keyup(isPasswordMatch);
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
						<label for="employeeFirstName"><b>First Name* :</b></label> <input
							type="text" class="form-control" id="employeeFirstName"
							placeholder="Enter First Name" name="employeeFirstName" value=""
							required />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="employeeLastName"><b>Last Name* :</b></label> <input
							type="text" class="form-control" id="employeeLastName"
							placeholder="Enter Last Name" name="employeeLastName" value=""
							required />
					</div>
				</div>
				

			</div>
			
			<div class="row register-form">
				<div class="col-md-4">
					<div class="form-group">
						<label for="employeeRoles"><b>Roles* :</b></label> <select
							class="form-control" name="employeeRoles" id="employeeRoles"
							required>
							<option value="" selected disabled>-- Select Roles --</option>
							<option value="ADMIN" selected>ADMIN</option>
							<option value="EMPLOYEE">EMPLOYEE</option>
						</select>
					</div>
				</div>
				
				<div class="col-md-4">
					<div class="form-group">
						<label for="employeeMailId"><b>Email* :</b></label> <input
							type="email" class="form-control" id="employeeMailId"
							placeholder="Enter mail id" name="employeeMailId" value=""
							required />
					</div>
				</div>
			</div>
			<div class="row register-form">
				<div class="col-md-4">
					<div class="form-group">
						<label for="employeePassword"><b>Password* :</b></label> <input
							type="password" class="form-control" placeholder="Password *"
							id="txtPassword" name="employeePassword"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 or more characters"
							required />
					</div>
				</div>
				<div class="col-md-4">
					<div class="form-group">
						<label for="employeeConfirmPassword"><b>Confirm
								Password* :</b> </label> <input type="password" class="form-control"
							placeholder="Confirm Password *" onChange="isPasswordMatch();"
							name="employeeConfirmPassword" id="txtConfirmPassword"
							pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}"
							title="Must contain at least one number and one uppercase and lowercase letter, and at least 5 or more characters"
							required />
						<div class="registrationFormAlert" id="divCheckPasswordMatch"></div>
					</div>
				</div>
				<div class="col-md-4">
					<input type="hidden" value="" id="employeeId" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<button type="submit" value="Submit" class="btn btn-primary"
						id="btnSubmit">Register</button>
				</div>
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
			</div>
		</form>
		</div>
</body>
</html>
