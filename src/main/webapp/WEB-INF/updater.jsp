<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data Updater</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/updater.css">
    <script type="module" src="${pageContext.request.contextPath}/static/script/updater.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/static/script/formHandler.js"></script>
</head>
<%
    List<String> quadrantNames = List.of("square", "circle", "triangle", "empty");
    String quadrants = "square,circle,triangle,empty";
%>
<body>
    <form action="/webLab-2.0-SNAPSHOT/stuff" method="post" id="updater-form">
        <input type="hidden" name="updateData" value="true">
        <div class="quadrant-chooser">
            <table class="quadrant-table">
                <tr>
                    <% for (int i=1; i <= 4; i++) { %>
                    <td class="updater-cell updater-header">
                            Quadrant <%=i%>
                    </td>
                    <%}%>
                </tr>
                <tr>
                    <% for (int i=1; i <= 4; i++) { %>
                    <td class="updater-cell">
                        <quadrant-chooser index="<%=i%>" quadrants="<%=quadrants%>" x-min="<%= i==1 || i==4 ? 0 : -1%>" y-min="<%= i==1 || i==2 ? 0 : -1%>"></quadrant-chooser>
                    </td>
                    <%}%>
                </tr>
            </table>
            <input class="submit" type="submit">
        </div>
    </form>
</body>
</html>
