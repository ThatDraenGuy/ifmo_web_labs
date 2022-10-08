<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Data Updater</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style/updater.css">
    <script type="module" src="${pageContext.request.contextPath}/static/script/updater.js"></script>
    <script type="module" src="${pageContext.request.contextPath}/static/script/formHandler.js"></script>
</head>
<c:set var="quadrants" value="square,circle,triangle,empty" scope="page"/>
<body>
    <form action="${pageContext.request.contextPath}/stuff" method="post" id="updater-form">
        <input type="hidden" name="updateData" value="true">
        <div class="quadrant-chooser">
            <table class="quadrant-table">
                <tr>
                    <c:forEach var="i" begin="1" end="4">
                        <td class="updater-cell updater-header">
                            Quadrant ${i}
                        </td>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach var="i" begin="1" end="4">
                        <td class="updater-cell">
                            <quadrant-chooser index="${i}" quadrants="${quadrants}"
                                              x-min="<c:choose><c:when test="${i==1 || i==4}">0</c:when><c:otherwise>-1</c:otherwise></c:choose>"
                                              y-min="<c:choose><c:when test="${i==1 || i==2}">0</c:when><c:otherwise>-1</c:otherwise></c:choose>">
                            </quadrant-chooser>
                        </td>
                    </c:forEach>
                </tr>
            </table>
            <input class="submit" type="submit">
        </div>
    </form>
</body>
</html>
