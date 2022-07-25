<?php
include 'constraints.php';
include 'coordinates.php';
include_once 'HTML_gen.php';

$constraints = array(
    'x' => new Options([-4,-3,-2,-1,0,1,2,3,4]),
    'y' => new Range(-3,3),
    'r' => new Options([1, 1.5, 2, 2.5, 3])
);

$quadrants = array(
    new CircleQuadrant(1,1),
    new SquareQuadrant(-1,1),
    new TriangleQuadrant(-1,-1),
    new EmptyQuadrant
);

function shoot() {
    $start_time = microtime(true);
    $x = handle_value('x');
    $y = handle_value('y');
    $r = handle_value('r');
    $hit_res = check_hit($x,$y,$r);
    handle_result($x,$y,$r, $hit_res->get_message(), $start_time);
}

function handle_value(string $name) : float {
    $value = $_POST[$name];
    $res = check_param($name, $value);
    if ($res->is_success()) {
        return floatval($value);
    } else {
        handle_error($res->get_message());
        //TODO: think about how to catch error or smth
    }
}
function check_param(string $name, $value) : Result {
    if (is_null($value)) return new Result(false, 'Value ' . $name . ' wasn\'t inputted!');
    if (is_numeric($value)) {
        if (get_param_constraints($name)->check_value($value)) {
            return new Result(true, '');
        } else {
            return new Result(false, 'Value of ' . $name . 'did not pass constraints check');
        }
    } else {
        return new Result(false, 'value ' . $name . ' is not a number, but a "' . $value . '"');
    }
}

function get_param_constraints(string $name) : Constraints {
    global $constraints;
    return $constraints[$name];
}

function check_hit(float $x, float $y, float $r) : Result {
    global $quadrants;
    foreach ($quadrants as $i => $quadrant) {
        if ($quadrant->check_hit($x,$y,$r)) {
            return new Result(true, 'that\'s a hit! ');
        };
    }
    return new Result(false, 'LOL, that\'s a miss! ');
}

function handle_error(string $message) {
    echo $message;
}

function handle_result($x,$y,$r,$message,$start_time) {
    $time = calc_time($start_time, microtime(true));
    $curr_time = date('Y-m-d H:i:s');
    echo gen_response($x,$y,$r,$message,$time,$curr_time);

}



function calc_time(float $start, float $finish) : string {
    $time = $finish - $start;
    $microtime = floor($time * 1000000);
    return $microtime . ' mks';
}



class Result {
    private bool $res;
    private string $message;

    public function __construct(bool $res, string $message) {
        $this->res = $res;
        $this->message = $message;
    }
    public function is_success() : bool {
        return $this->res;
    }
    public function get_message() : string {
        return $this->message;
    }
}