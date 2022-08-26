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

function gen_response($x,$y,$r, bool $result, string $message, float $start_time) {
    global $table_header;
    $history_table = gen_history_table();
    $scream = get_scream($result);
    $reaction_image = gen_reaction_image($result);
    $info = new AttemptInfo($x,$y,$r,$result,$message,$start_time);
    $table_row = gen_table_row($info);
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
                    <div class="scream">' . $scream . '</div><br>
                    ' . $reaction_image . '<br>
                </td>
                <td class="response-cell">
                    ' . $history_table . '
                </td>
            </tr>
        </table>
    </body>
    </html>';
    array_push($_SESSION['history'], $info);
    $_SESSION['last_attempt'] = $info;
    echo $res;
}

function gen_history_table() : string {
    $history = $_SESSION['history'];
    if (empty($history)) return '';
    global $table_header;
    $res = '
    <div id="history">
    <div class="clear-history">
        <button class="clear-history-button type="button" onclick="clearHistory();">Clear history</button>
    </div><br>
    <table class="history-table">';
    $res .= $table_header;
    foreach ($history as $i => $info) {
        $res .= gen_table_row($info);
    }
    $res .= '</table></div>';
    return $res;
}

function gen_table_row(AttemptInfo $info) : string {
    return '
    <tr>
    <td class="content">' . $info->x . '</td>
    <td class="content">' . $info->y . '</td>
    <td class="content">' . $info->r . '</td>
    <td class="content">' . $info->message . '</td>
    <td class="content">' . $info->exec_time . '</td>
    <td class="content">' . $info->curr_time . '</td>
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
class AttemptInfo {
    public string $x;
    public string $y;
    public string $r;
    public bool $res;
    public string $message;
    public string $exec_time;
    public string $curr_time;
    
    public function __construct(string $x, string $y, string $r, bool $res, string $message, float $start_time) {
        $this->x = $x;
        $this->y = $y;
        $this->r = $r;
        $this->res = $res;
        $this->message = $message;
        $this->exec_time = time_elapsed(microtime(true) - $start_time);
        $this->curr_time = date('Y-m-d H:i:s');
    }
}
