<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data Updater</title>
    <link rel="stylesheet" href="static/style/updater.css">
    <script type="module" src="static/script/updater.js"></script>
</head>
<%
    List<String> quadrantNames = List.of("square", "circle", "triangle", "empty");
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
                        <table>
                            <tr>
                                <td>
                                    <label for="type<%=i%>">Type:</label>
                                </td>
                                <td>
                                    <select name="quadrants[<%=i%>][type]" id="type<%=i%>">
                                        <%for (String quadrant : quadrantNames) {%>
                                        <option value="<%=quadrant%>"><%=quadrant%></option>
                                        <%}%>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="x_mul<%=i%>">xMul:</label>
                                </td>
                                <td>
                                    <input class="updater-input" name="quadrants[<%=i%>][x_mul]" type="text" id="x_mul<%=i%>" data-min="-1" data-max="1" oninput="paramChanged(this.name)">
                                </td>
                            </tr>
                            <tr class="message-row">
                                <td colspan="2">
                                    <span class="message-coord-cell input-message" name="quadrants[<%=i%>][x_mul]" style="visibility: hidden;">message</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label for="y_mul<%=i%>">yMul:</label>
                                </td>
                                <td>
                                    <input class="updater-input" name="quadrants[<%=i%>][y_mul]" type="text" id="y_mul<%=i%>" data-min="-1" data-max="1" oninput="paramChanged(this.name)">
                                </td>
                            </tr>
                            <tr class="message-row">
                                <td colspan="2">
                                    <span class="message-coord-cell input-message" name="quadrants[<%=i%>][y_mul]" style="visibility: hidden;">message</span>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <%}%>
                </tr>
            </table>
            <input class="submit" type="submit">
<%--            <iframe class="empty" id="empty" name="empty"></iframe>--%>
        </div>
    </form>
</body>
</html>
