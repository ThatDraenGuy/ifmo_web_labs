<?php
include_once 'logic.php';

$table_header = '
<tr>
    <td class="content">x</td>
    <td class="content">y</td>
    <td class="content">r</td>
    <td class="content">result</td>
    <td class="content">script time</td>
    <td class="content">current time</td>
</tr>
';

function gen_response($x,$y,$r, bool $result, string $message, string $time_of_script, string $current_time) {
    global $table_header;
    $table_row = gen_table_row($x,$y,$r,$message,$time_of_script,$current_time);
    $res = '
    <html>
    <head>
        <link href="../front/style/response.css" rel="stylesheet">
    </head>
    <body>
        <table class="response-table" id="response-table">
            <tr>
                <td>
                    <h2 class="response-header">Result</h2>
                </td>
                <td>
                    <h2 class="response-header">History</h2>
                </td>
            </tr>
            <tr>
                <td class="response-cell">
                    <table class="result-table">
                    '. $table_header .'
                    '. $table_row .'
                    </table><br>
                    <div class="scream">' . get_scream($result) . '</div><br>
                    ' . gen_reaction_image($result) . '<br>
                </td>
                <td class="response-cell">
                    <div class="clear-history">
                    <form action="main.php" method="post">
                        <input type="hidden" name="clearHistory" value="true">
                        <input type="submit" value="Clear history" class="clear-history-button">
                    </form>
                    </div>
                    <table class="history-table">
                    ' . gen_history_table() . '
                    </table>
                </td>
            </tr>
        </table>
    </body>
    </html>';
    array_push($_SESSION['history'], $table_row);
    return $res;
}

function gen_history_table() : string {
    $history = $_SESSION['history'];
    if (empty($history)) return '';
    global $table_header;
    $res = '<table class="history-table">';
    $res .= $table_header;
    foreach ($history as $i => $table_row) {
        $res .= $table_row;
    }
    $res .= '</table>';
    return $res;
}

function gen_table_row($x,$y,$r, string $message, string $time_of_script, string $current_time) : string {
    return '
    <tr>
    <td class="content">' . $x . '</td>
    <td class="content">' . $y . '</td>
    <td class="content">' . $r . '</td>
    <td class="content">' . $message . '</td>
    <td class="content">' . $time_of_script . '</td>
    <td class="content">' . $current_time . '</td>
    </tr>';
}

function gen_reaction_image(bool $result) : string {
    $dir = "../img/result/" . get_dir_name($result);
    $images = scandir($dir);
    $images = array_slice($images, 2);
    return '<img class="result-image" src="' . $dir . '/' . $images[array_rand($images)] . '">';
}

function get_dir_name(bool $result) : string {
    return $result ? "hit" : "miss";
}

function get_scream(bool $result) : string {
    return $result ? "HIT!" : "MISS!";
}

function time_elapsed($secs){
    $bit = array(
        'y' => $secs / 31556926 % 12,
        'w' => $secs / 604800 % 52,
        'd' => $secs / 86400 % 7,
        'h' => $secs / 3600 % 24,
        'm' => $secs / 60 % 60,
        's' => $secs % 60,
        'ms' => $secs * 1000 % 1000,
        'mks' => $secs * 1000000 % 1000
        );
       
    foreach($bit as $k => $v)
        if($v > 0)$ret[] = $v . $k;
       
    return join(' ', $ret);
}