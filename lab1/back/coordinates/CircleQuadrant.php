<?php
class CircleQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'circle');
    }
    public function calcHit(float $x, float $y, float $r): bool {
        return (pow($x,2)+ pow($y,2) <= pow($r,2));
    }
}