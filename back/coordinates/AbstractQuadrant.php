<?php
include_once 'Quadrant.php';
abstract class AbstractQuadrant implements Quadrant {
    private int $x_sign;
    private int $y_sign;
    protected string $type;
    public function __construct(int $x_sign, int $y_sign, string $type) {
        $this->x_sign = sign($x_sign);
        $this->y_sign = sign($y_sign);
        $this->type = $type;
    }

    private function checkSign(float $x, float $y): bool {
        return ((sign($x)==$this->x_sign || sign($x)==0) && (sign($y)==$this->y_sign || sign($y)==0));
    }
    public function checkHit(float $x, float $y, float $r): bool {
        if ($this->checkSign($x,$y)) {
            return $this->calcHit($x,$y,$r);
        }
        return false;
    }
    protected abstract function calcHit(float $x, float $y, float $r): bool;
    public function jsonSerialize() {
        return [
            'type' => $this->type,
            'x_sign' => $this->x_sign,
            'y_sign' => $this->y_sign
        ];
    }
}
function sign($n) {
    return ($n > 0) - ($n < 0);
}