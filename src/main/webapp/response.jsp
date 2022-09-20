<%@ page import="info.AttemptInfo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/style/response.css" rel="stylesheet">
    <title>Response</title>
</head>
<%
    AttemptInfo attemptInfo = null;
    try {
        attemptInfo = (AttemptInfo) request.getAttribute("attemptInfo");
    } catch (ClassCastException e) {
        attemptInfo = AttemptInfo.empty();
    }
%>
<body>
<table class="response-table">
    <colgroup>
        <col class="result">
        <col class="history">
    </colgroup>
    <tr class="header">
        <td>
            <div class="result-scream-cell">
                <%= attemptInfo.res() ? "HIT!" : "MISS!"%>
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

        </td>
        <td class="history-table-cell">
            <%= attemptInfo.toString()%>
        </td>
    </tr>
</table>
</body>
</html>
