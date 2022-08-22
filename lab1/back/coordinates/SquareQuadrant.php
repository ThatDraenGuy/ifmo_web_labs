<?php
class SquareQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'square');
    }
    public function calcHit(float $x, float $y, float $r): bool {
        return (abs($x)<=$r && abs($y)<=$r);
    }
}