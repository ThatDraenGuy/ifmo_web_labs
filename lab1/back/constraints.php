<?php

interface Constraints {
    public function check_value($value) : bool;
    public function gen_shooting_table_row(string $param_name) : string;
}

class Options implements Constraints {
    private array $options;
    public function __construct(array $options) {
        $this->options = $options;
    }
    public function check_value($value) : bool{
        return (in_array($value, $this->options));
    }
    public function gen_shooting_table_row(string $param_name) : string {
        $row = '
        <tr>
            <td>
                ' . strtoupper($param_name) . ': ';
        foreach ($this->options as $i => $option) {
            $line = $option . ' <input type="radio" name="' . $param_name . '" value="' . $option . '" class="radio-input"  oninput="paramChanged(name)">';
            $row .=$line;
        }
        $row .= '<br>
        <span class="input-message" name="'. $param_name . '" id="message" style="visibility: hidden;">message</span>
        </td>
        </tr>';
        return $row;
    }
}

class Range implements Constraints {
    private int $lower_bound;
    private int $higher_bound;

    public function __construct(int $lower_bound, int $higher_bound) {
        $this->lower_bound = $lower_bound;
        $this->higher_bound = $higher_bound;
    }
    public function check_value($value) : bool{
        return ($value >= $this->lower_bound && $value <= $this->higher_bound);
    }
    public function gen_shooting_table_row(string $param_name) : string {
        $row = '
        <tr>
            <td>
                ' . strtoupper($param_name) . ': 
                <input type="text" name="' . $param_name . '" class="text-input" data-min="' . $this->lower_bound . '"; data-max="' . $this->higher_bound . '"; oninput="paramChanged(name)"><br>
                <span class="input-message" name="' . $param_name . '" id="message" style="visibility: hidden;">message</span>
            </td>
        </tr>';
        return $row;
    }
}