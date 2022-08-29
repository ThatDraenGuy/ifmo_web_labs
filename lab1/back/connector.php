<?php
include_once 'settings.php';
function sendData() {
    global $quadrants;
    global $constraints;
    $history = $_SESSION['history'];
    echo json_encode([
        'quadrants' => $quadrants,
        'constraints' => $constraints,
        'history' => $history
    ]);
}