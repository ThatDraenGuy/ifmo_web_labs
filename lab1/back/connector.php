<?php
include 'settings.php';
sendQuadrantData();
function sendQuadrantData() {
    global $quadrants;
    global $constraints;
    echo json_encode([
        'quadrants' => $quadrants,
        'constraints' => $constraints
    ]);
}