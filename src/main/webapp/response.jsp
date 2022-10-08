<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/style/response.css" rel="stylesheet">

    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/static/script/utils.js"></script>
    <title>Response</title>
</head>
<body>
<form action="${pageContext.request.contextPath}">
    <input type="submit" value="RETURN" class="clear-history-button"/>
</form>
<table class="response-table">
    <colgroup>
        <col class="result">
        <col class="history">
    </colgroup>
    <tr class="header">
        <td>
            <div class="result-scream-cell">
                <c:out value="${requestScope.scream}"/>
            </div>
        </td>
        <td class="history-header-cell">
            <table class="history-header-table">
                <tr>
                    <td>
                        <div class="history-table-name">
                            HISTORY
                        </div>
                    </td>
                    <td>
                        <div class="clear-history-button-cell">
                            <button class="clear-history-button" type="button" onclick="clearHistory();">Clear</button>
                        </div>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr class="content">
        <td class="result-reaction-cell">
            <c:out value="${requestScope.reaction}" escapeXml="false"/>
        </td>
        <td class="history-table-cell">
            <div id="history">
                <table class="history-table">
                    <tr>
                        <td class="header-history-cell">Attempt №</td>
                        <td class="header-history-cell">X</td>
                        <td class="header-history-cell">Y</td>
                        <td class="header-history-cell">R</td>
                        <td class="header-history-cell">Result</td>
                        <td class="header-history-cell">Execution time</td>
                        <td class="header-history-cell">Attempt time</td>
                    </tr>
                    <c:out value="${requestScope.historyTable}" escapeXml="false"/>
                </table>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
