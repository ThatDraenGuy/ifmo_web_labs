<?php
class EmptyQuadrant extends AbstractQuadrant {
    public function __construct(int $x_sign, int $y_sign) {
        parent::__construct($x_sign, $y_sign, 'empty');
    }
    public function calcHit(float $x, float $y, float $r): bool {
        return false;
    }
}