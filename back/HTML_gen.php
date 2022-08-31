<?php
include_once 'logic.php';

function gen_response($x,$y,$r, bool $result, string $message, float $start_time) {
    $info = new AttemptInfo($x,$y,$r,$result,$message,$start_time);
    array_push($_SESSION['history'], $info);
    $_SESSION['last_attempt'] = $info;
    gen_html();
}

function gen_html() {
    $result = $_SESSION['last_attempt']->res;
    $history_table = gen_history_table();
    $scream = get_scream($result);
    $reaction_image = gen_reaction_image($result);
    $res = '
    <html>
    <head>
        <link href="../front/style/response.css" rel="stylesheet">
    </head>
    <body>
        <table class="response-table">
        <colgroup>
            <col class="result">
            <col class="history">
        </colgroup>
        <tr class="header">
            <td>
                <div class="result-scream-cell">
                    ' . $scream . '
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
                ' . $reaction_image . '
            </td>
            <td class="history-table-cell">
                ' . $history_table . '
            </td>
        </tr>
    </table>
    </body>
    </html>';
    echo $res;
}

function gen_history_table() : string {
    $history = $_SESSION['history'];
    if (empty($history)) return '';
    $res = '
    <div id="history">
    <table class="history-table">';
    $res .= '
    <tr>
        <td class="header-history-cell">Attempt №</td>
        <td class="header-history-cell">X</td>
        <td class="header-history-cell">Y</td>
        <td class="header-history-cell">R</td>
        <td class="header-history-cell">Result</td>
        <td class="header-history-cell">Execution time</td>
        <td class="header-history-cell">Attempt time</td>
    </tr>';
    foreach ($history as $i => $info) {
        $res .= gen_table_row($info, $i+1);
    }
    $res .= '</table></div>';
    return $res;
}

function gen_table_row(AttemptInfo $info, int $attempt) : string {
    return '
    <tr>
    <td class="history-cell">' . $attempt . '</td>
    <td class="history-cell">' . $info->x . '</td>
    <td class="history-cell">' . $info->y . '</td>
    <td class="history-cell">' . $info->r . '</td>
    <td class="history-cell">' . $info->message . '</td>
    <td class="history-cell">' . $info->exec_time . '</td>
    <td class="history-cell">' . $info->curr_time . '</td>
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
