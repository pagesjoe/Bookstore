<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>

<body>
	<div class="container">
		<h1 class="text-center">Add Book</h1>
		<br>
		<form action="Home" method="post">
			<input type="hidden" name="command" value="add">
			<div class="form-group">
			    <label>Title</label>
			    <input name="title" class="form-control" placeholder="Title" required>
		  </div>
		  <div class="form-group">
			    <label>Author</label>
			    <input name="author" class="form-control" placeholder="Author" required>
		  </div>
		  <div class="form-group">
			    <label>Price</label>
			    <input name="price" type="number" step="0.01" class="form-control" placeholder="Price" required>
		  </div>
		  <br>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	
</body>
</html>