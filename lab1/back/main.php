<?php
include 'logic.php';

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
}



function clear_history() {
    $_SESSION['history'] = [];
    if (isset($_SESSION['last_attempt'])) {
        array_push($_SESSION['history'], $_SESSION['last_attempt']);
    }
}