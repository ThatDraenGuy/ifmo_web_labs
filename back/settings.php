<?php
include 'constraints.php';
foreach (glob("coordinates/*.php") as $filename)
{
    include_once $filename;
}
// a point in program that defines the way user chooses parameters for his shot. Changing this "setting" will automatically update the html page.
// Currently supported: Radio buttons, represented by Options class & Text field, represented by RAnge class.
$constraints = array(
    'x' => new Options([-4,-3,-2,-1,0,1,2,3,4]),
    'y' => new Range(-3,3),
    'r' => new Options([1, 1.5, 2, 2.5, 3])
);

// a point in program that defines the graphic user shoots at. Changing this "setting" will automatically update the html page & backend logic.
// the numbers in constructors define their respective quadrant, with 1st number being a sign of X and 2nd being the sign of Y.
$quadrants = array(
    new CircleQuadrant(1,1),
    new SquareQuadrant(-1,1),
    new TriangleQuadrant(-1,-1),
    new EmptyQuadrant(1, -1)
);

// if set to true back-end will check that given values pass the constraints check
$check_constraints = false;