<!DOCTYPE html>
<html lang="en">
<head>
    <title>lab1</title>
    <link href="front/style.css" rel="stylesheet">
    <script src="front/script.js"></script>
</head>
<body>
    <table class="main-table">
        <tr>
            <th></th>
            <th></th>
            <th id="header">
                Haikin Oleg<br>
                P3231<br>
                var 3116<br>
                <div id="src-link">
                    <a href="https://github.com/ThatDraenGuy">Github</a><br>
                    <p id="tooltip">Arguably the best developer in all of /dev/null!</p>
                </div>
                <img src="https://c.tenor.com/y7mpHtEr-voAAAAd/chihuahua-chihuahua-spin.gif" width="100" height="100">
            </th>
        </tr>
        <tr>
            <td class="side-cell"></td>
            <td class="center-cell">
                <table class="inside-table">
                    <td class="side-cell"></td>
                    <td class="content-cell">
                        <table class="content-table">
                            <tr>
                                <td>
                                    <form action="back/main.php" method="post" target="result" onsubmit="return validateSubmission()">
                                        <?php
                                        include_once 'back/HTML_gen.php';
                                        echo gen_shooting_table();
                                        ?>
                                        <!-- <table id="shoot-table">
                                            <tr>
                                                <td>
                                                    X:
                                                    -4<input type="radio" name="x" value="-4" class="radio-input"  oninput="paramChanged(name)">
                                                    -3<input type="radio" name="x" value="-3" class="radio-input"  oninput="paramChanged(name)">
                                                    -2<input type="radio" name="x" value="-2" class="radio-input"  oninput="paramChanged(name)">
                                                    -1<input type="radio" name="x" value="-1" class="radio-input"  oninput="paramChanged(name)">
                                                    0<input type="radio" name="x" value="0" class="radio-input"  oninput="paramChanged(name)">
                                                    1<input type="radio" name="x" value="1" class="radio-input"  oninput="paramChanged(name)">
                                                    2<input type="radio" name="x" value="2" class="radio-input"  oninput="paramChanged(name)">
                                                    3<input type="radio" name="x" value="3" class="radio-input"  oninput="paramChanged(name)">
                                                    4<input type="radio" name="x" value="4" class="radio-input"  oninput="paramChanged(name)"><br>
                                                    <span class="input-message" name="x" id="message" style="visibility: hidden;">message</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    Y:
                                                    <input type="text" name="y" class="text-input" data-min="-3"; data-max="3"; oninput="paramChanged(name)"><br>
                                                    <span class="input-message" name="y" id="message" style="visibility: hidden;">message</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    R:
                                                    1<input type="radio" name="r" value="1" class="radio-input"  oninput="paramChanged(name)">
                                                    1.5<input type="radio" name="r" value="1.5" class="radio-input"  oninput="paramChanged(name)">
                                                    2<input type="radio" name="r" value="2" class="radio-input"  oninput="paramChanged(name)">
                                                    2.5<input type="radio" name="r" value="2.5" class="radio-input"  oninput="paramChanged(name)">
                                                    3<input type="radio" name="r" value="3" class="radio-input"  oninput="paramChanged(name)"><br>
                                                    <span class="input-message" name="r" id="message" style="visibility: hidden;">message</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <input type="submit" name="shoot!">
                                                </td>
                                            </tr>
                                        </table> -->
                                    </form>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <iframe name="result" id="result">
                                        stuff
                                    </iframe>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td class="side-cell"></td>
                </table>
            </td>
            <td class="side-cell"></td>
        </tr>
    </table>
</body>
</html>