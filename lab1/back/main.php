<?php
include 'logic.php';
include_once 'connector.php';

session_start();
if (!isset($_SESSION['history'])) {
    $_SESSION['history'] = [];
}

if ($_POST) {
    if (isset($_POST["shoot"])) {
        shoot();        
    }
    if (isset($_POST["clearHistory"])) {
        clear_history();
    }
    if (isset($_POST["getData"])) {
        sendData();
    }
}



function clear_history() {
    $_SESSION['history'] = [];
    if (isset($_SESSION['last_attempt'])) {
    }
}