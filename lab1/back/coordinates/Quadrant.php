<?php
interface Quadrant extends JsonSerializable {
    public function checkHit(float $x, float $y, float $r) : bool;
}