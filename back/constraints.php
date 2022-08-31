<?php

interface Constraints extends JsonSerializable {
    public function checkValue($value) : bool;
}

class Options implements Constraints {
    private array $options;
    public function __construct(array $options) {
        $this->options = $options;
    }
    public function checkValue($value) : bool{
        return (in_array($value, $this->options));
    }
    public function jsonSerialize() {
        return [
            'type' => "options",
            'options' => $this->options
        ];
    }
}

class Range implements Constraints {
    private int $lower_bound;
    private int $higher_bound;

    public function __construct(int $lower_bound, int $higher_bound) {
        $this->lower_bound = $lower_bound;
        $this->higher_bound = $higher_bound;
    }
    public function checkValue($value) : bool{
        return ($value >= $this->lower_bound && $value <= $this->higher_bound);
    }
    public function jsonSerialize() {
        return [
            'type' => 'range',
            'range_min' => $this->lower_bound,
            'range_max' => $this->higher_bound
        ];
    }
}