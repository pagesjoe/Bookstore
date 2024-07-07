<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
<body>
	<h1 class="text-center">Book Management</h1>
	<br>
	
	<div class="text-center">
		<button class ="btn btn-primary" onclick="location.href='add_book.jsp'" 
		type="button">Add Book</button>
	</div>
	
	<br>
	<div >
		<table class="table table-primary d-flex justify-content-evenly">
			<tr>
				<th>ID</th>
				<th>Title</th>
				<th>Author</th>
				<th>Price</th>
				<th><span class="text-center">Actions</span></th>
			</tr>
			
			<c:forEach var="book" items="${books}">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.price }</td>
				<td><div class="d-flex justify-content-between gap-3">
						<div>
							<form action="Home" method="get">
								<input type="hidden" name="command" value="edit">
								<input type="hidden" name="id" value=${book.id }>
								<button type="submit" class="btn btn-warning">Edit</button>
							</form>
						</div>
						<div>
							<form action="Home" method="post">
								<input type="hidden" name="command" value="delete">
								<input type="hidden" name="id" value=${book.id }>
 								<button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</div>
						
					</div>
				</td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>