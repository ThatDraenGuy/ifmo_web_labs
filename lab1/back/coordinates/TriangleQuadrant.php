<?php
class TriangleQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'triangle');
    }
    public function calcHit(float $x, float $y, float $r): bool {
        return (abs($x)+abs($y)<=$r);
    }
}