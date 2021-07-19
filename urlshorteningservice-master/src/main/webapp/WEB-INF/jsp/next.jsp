<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<link rel="stylesheet" type="text/css" href="css/style.css" />

<body>
	<div class="row">
		<h1>Short Url</h1>
		<ul>
			<li><p>
					<label for="original_Url">original Url</label> ${originalUrl}
				</p></li>
			<li><p>
					<label for="shortLink">Short Link</label> ${shortLink}

				</p></li>
			<li><p>
					<label for="Count">Count</label> ${count}

				</p></li>
		</ul>
	</div>
</body>

</html>
