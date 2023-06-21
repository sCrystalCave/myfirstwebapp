<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
    <div>Welcome ${name}</div>
    <hr>
    <h1>Your Plays</h1>
    <table class="table">
        <thead>
            <tr>
                <th>id</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Done?</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${plays}" var="play">
            <tr>
                <td>${play.id}</td>
                <td>${play.description}</td>
                <td>${play.targetDate}</td>
                <td>${play.done}</td>
                <td> <a href="delete-play?id=${play.id}" class="btn btn-warning">Delete</a>   </td>
                <td> <a href="update-play?id=${play.id}" class="btn btn-success">Update</a>   </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <a href="add-play" class="btn btn-success">Add Play</a>
</div>
<%@ include file="common/footer.jspf" %>
