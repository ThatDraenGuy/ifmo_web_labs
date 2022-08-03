<?php

interface Quadrant extends JsonSerializable {
    public function check_hit(float $x, float $y, float $r) : bool;
}
abstract class AbstractQuadrant implements Quadrant {
    private int $x_sign;
    private int $y_sign;
    protected string $type;
    public function __construct(int $x_sign, int $y_sign, string $type) {
        $this->x_sign = sign($x_sign);
        $this->y_sign = sign($y_sign);
        $this->type = $type;
    }

    private function check_sign(float $x, float $y): bool {
        return ((sign($x)==$this->x_sign || sign($x)==0) && (sign($y)==$this->y_sign || sign($y)==0));
    }
    public function check_hit(float $x, float $y, float $r): bool {
        if ($this->check_sign($x,$y)) {
            return $this->calc_hit($x,$y,$r);
        }
        return false;
    }
    protected abstract function calc_hit(float $x, float $y, float $r): bool;
    public function jsonSerialize() {
        return [
            'type' => $this->type,
            'x_sign' => $this->x_sign,
            'y_sign' => $this->y_sign
        ];
    }
}

class EmptyQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'empty');
    }
    public function calc_hit(float $x, float $y, float $r): bool {
        return false;
    }
}

class SquareQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'square');
    }
    public function calc_hit(float $x, float $y, float $r): bool {
        return (abs($x)<=$r && abs($y)<=$r);
    }
}
class TriangleQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'triangle');
    }
    public function calc_hit(float $x, float $y, float $r): bool {
        return (abs($x)+abs($y)<=$r);
    }
}
class CircleQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'circle');
    }
    public function calc_hit(float $x, float $y, float $r): bool {
        return (pow($x,2)+ pow($y,2) <= pow($r,2));
    }
}

function sign($n) {
    return ($n > 0) - ($n < 0);
}