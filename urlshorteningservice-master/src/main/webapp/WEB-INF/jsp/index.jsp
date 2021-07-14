
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%><!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<body>

	<form action="generate" method="post">
		<table style="with: 50%">
			<tr>
				<td>URL</td>
				<td><input type="text" name="url" /></td>
			</tr>
			<tr>
				<td>Expiration Date</td>
				<td><input type="text" name="expirationDate" /></td>
			</tr>
		</table>
		<input type="submit" value="Submit" />
	</form>
	<div class="row">
		<div>
			Get the counts of link
			<form action="loadCount" method="post">
				<input type="submit" value="count" class="btn btn-outline-primary" />
			</form>
		</div>
	</div>

</body>
</html>
