<%@ page import="info.AttemptInfo" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.Reader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="info.ReactionsInfo" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.HistoryManager" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/style/response.css" rel="stylesheet">
    <title>Response</title>
</head>
<%
    @SuppressWarnings({"unchecked"})
    HistoryManager<AttemptInfo> historyManager = (HistoryManager<AttemptInfo>) application.getAttribute(HistoryManager.NAME);

    AttemptInfo attemptInfo;
    try {
        attemptInfo = (AttemptInfo) request.getAttribute("attemptInfo");
        historyManager.put(attemptInfo);
    } catch (ClassCastException e) {
        attemptInfo = AttemptInfo.empty();
    }

    ReactionsInfo reactionsInfo;
    try {
        InputStream inputStream = application.getResourceAsStream("/WEB-INF/reactions.json");
        Reader reader = new InputStreamReader(inputStream);
        ObjectMapper mapper = new ObjectMapper();
        reactionsInfo = mapper.readValue(reader, ReactionsInfo.class);
    } catch (Exception e) {
        reactionsInfo = ReactionsInfo.empty();
    }
%>
<%! public String historyToTable(List<AttemptInfo> history) {
    StringBuilder builder = new StringBuilder();
    int i = 1;
    for (AttemptInfo attemptInfo : history) {
        attemptInfo.toHtmlRow(builder, i);
        i++;
    }
    return builder.toString();
}%>
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
            <%= attemptInfo.res() ? reactionsInfo.randomHit() : reactionsInfo.randomMiss()%>
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
                    <%= historyToTable(historyManager.get())%>
                </table>
            </div>
        </td>
    </tr>
</table>
</body>
</html>
