
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html lang="en">
<script type=”text/javascript” src=”bootstrap/js/bootstrap.min.js”></script>

<link rel="stylesheet" href="bootstrap/css/bootstrap-min.css">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<body>
	<div class="row">
		<h3>URL Shortner Application</h3>
		<form action="generate" method="post">
			<table style="with: 50%">
				<tr>
					<td><label for="url">URL</label></td>
					<td><input type="text" name="url"
						placeholder="Please Enter Your URL" /></td>
				</tr>
				<tr>
					<td><label for="expDate">Expiration Date</label></td>
					<td><input type="text" name="expirationDate"
						placeholder="Please Enter Expiration Date" /></td>
				</tr>
			</table>
			<input type="submit" value="Submit" />
		</form>
	</div>
	<div class="row">
		<div>
			<h3>To get the counts of link please press the button below!</h3>
			<form action="loadCount" method="post">
				<input type="submit" value="count" class="btn btn-outline-primary" />
			</form>
		</div>
	</div>

</body>

</html>