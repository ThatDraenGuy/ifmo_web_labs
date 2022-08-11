<?php
include_once 'logic.php';

$table_header = '
<tr>
    <td>x</td>
    <td>y</td>
    <td>r</td>
    <td>result</td>
    <td>script time</td>
    <td>current time</td>
</tr>
';

function gen_response($x,$y,$r, string $message, string $time_of_script, string $current_time) {
    global $table_header;
    $table_row = gen_table_row($x,$y,$r,$message,$time_of_script,$current_time);
    $res = '
    <html>
    <head>
        title>Response</title>
        <link href="../front/response-style.css" rel="stylesheet">
    </head>
    <body>
        <table class="response-table">
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
                    </table>
                </td>
                <td class="response-cell">
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
    <td>' . $x . '</td>
    <td>' . $y . '</td>
    <td>' . $r . '</td>
    <td>' . $message . '</td>
    <td>' . $time_of_script . '</td>
    <td>' . $current_time . '</td>
    </tr>';
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