<?php
include_once 'settings.php';
include_once 'constraints.php';
include_once 'HTML_gen.php';


foreach (glob("coordinates/*.php") as $filename)
{
    include_once $filename;
}

function shoot() {
    $start_time = microtime(true);
    try {
        $x = handle_value('x');
        $y = handle_value('y');
        $r = handle_value('r');
        $hit_res = check_hit($x,$y,$r);
        handle_result($x,$y,$r, $hit_res->isSuccess(), $hit_res->getMessage(), $start_time);
    } catch (ValueException $e) {
        handle_result($_POST['x'],$_POST['y'],$_POST['r'], false, $e->getMessage(), $start_time);
    }
}

function handle_value(string $name) : float {
    $value = $_POST[$name];
    check_param($name, $value);
    return floatval($value);
}
function check_param(string $name, $value) {
    global $check_constraints;
    if (is_null($value) || $value=='') throw new ValueException('Value ' . $name . ' wasn\'t inputted!');
    if (!is_numeric($value)) {
        throw new ValueException('value ' . $name . ' is not a number, but a "' . $value . '"');
    }
    if ($check_constraints && !get_param_constraints($name)->checkValue($value)) {
        throw new ValueException('Value of ' . $name . ' did not pass constraints check');
    }
}

function get_param_constraints(string $name) : Constraints {
    global $constraints;
    return $constraints[$name];
}

function check_hit(float $x, float $y, float $r) : Result {
    global $quadrants;
    foreach ($quadrants as $i => $quadrant) {
        if ($quadrant->checkHit($x,$y,$r)) {
            return new Result(true, 'that\'s a hit! ');
        };
    }
    return new Result(false, 'LOL, that\'s a miss! ');
}

function handle_error(string $message) {
    echo $message;
}

function handle_result($x,$y,$r, bool $result, string $message, float $start_time) {
    $curr_time = date('Y-m-d H:i:s');
    $time = time_elapsed(microtime(true) - $start_time);
    echo gen_response($x,$y,$r,$result,$message,$time,$curr_time);
}


class Result {
    private bool $res;
    private string $message;

    public function __construct(bool $res, string $message) {
        $this->res = $res;
        $this->message = $message;
    }
    public function isSuccess() : bool {
        return $this->res;
    }
    public function getMessage() : string {
        return $this->message;
    }
}

class ValueException extends Exception {
    public function __construct(string $message) {
        parent::__construct($message);
    }
}