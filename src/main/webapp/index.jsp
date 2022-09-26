<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/static/img/favicon.ico">
    <title>lab2</title>
    <link href="static/style/main.css" rel="stylesheet">
    <link href="static/style/content.css" rel="stylesheet">
    <link href="static/style/header.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/superagent"></script>
    <script type="module" src="static/script/connector.js"></script>
    <script type="module" src="static/script/paramChecker.js"></script>
    <script type="module" src="static/script/utils.js"></script>
</head>
<body>
    <table class="main-table">
        <colgroup>
            <col class="left">
            <col class="middle">
            <col class="right">
        </colgroup>
        <tr class="header-row">
            <td class="empty"></td>
            <!-- graph -->
            <td class="graph-cell" rowspan="2">
                <div class="blob graph">
                    <canvas id="graph" width=400 height=400></canvas>
                </div>
            </td>
            <!-- header drop button -->
            <td class="header-button-cell">
                <div class="header-button-blob">
                    <!-- checkbox hack -->
                    <input type="checkbox" id="header-button" oninput="showHeader(this);">
                    <label class="header-button" for="header-button">
                        <img class="header-button-img" src="${pageContext.request.contextPath}/static/img/giphy.gif" alt="">
                        <img class="arrow-button-img" src="${pageContext.request.contextPath}/static/img/arrow.png" alt="click">
                    </label>
                </div>
            </td>
        </tr>
        <tr class="content-row">
            <!-- coord chooser -->
            <td class="coord-choosing-cell">
                <div class="blob coord-chooser">
                    <form id="shooting-form" action="" method="post" target="result" onsubmit="return validateSubmission()">
                        <input type="hidden" name="shoot" value="true">
                        <table id="shoot-table">
                            <img class="placeholder" id="shoot-load" src="${pageContext.request.contextPath}/static/img/load.gif" alt="loading...">
                            <!-- generated in run-time -->
                        </table>
                    </form>
                </div>
            </td>
            <!-- header -->
            <td class="header-cell">
                <div class="blob header" id="header">
                    <table class="header-table">
                        <colgroup>
                            <col>
                            <col>
                        </colgroup>
                        <tr>
                            <td class="contacts">
                                <h1>Хайкин Олег Игоревич</h1>
                                <a href="https://github.com/ThatDraenGuy"><h2>@ThatDraenGuy</h2></a>
                            </td>
                            <td class="fun">
                                <img class="header-img" src="${pageContext.request.contextPath}/static/img/chihuahua-spin.gif" alt="no logo :(">
                            </td>
                        </tr>
                        <tr>
                            <td class="labInfo">
                                <h3>group 32312</h3>
                                <h3>var 3116</h3>
                            </td>
                            <td class="source">
                                <a href="https://github.com/ThatDraenGuy/webLab"><img class="source-img" src="${pageContext.request.contextPath}/static/img/github.png" alt="github"></a>
                            </td>
                        </tr>
                    </table>
                </div>
            </td>
        </tr>
        <tr class="content-row">
            <!-- dataUpdater -->
            <td>
                <div class="updater blob">
                    <jsp:include page="updater.jsp">
                        <jsp:param name="" value=""/>
                    </jsp:include>
<%--                    <a href="updater.jsp">Change graph data</a>--%>
<%--                    <iframe src="updater.jsp" id="updater">--%>
<%--                        bruh--%>
<%--                    </iframe>--%>
                </div>
            </td>
            <!-- response -->
            <td class="response-cell" colspan="2">
                <div class="result blob" hidden="true">
                    <iframe name="result" scrolling="no" id="result" onload="resizeIframe(this)">
                        stuff
                    </iframe>
                </div>
            </td>
        </tr>
    </table>
</body>