<?php
include 'logic.php';

session_start();
if (!isset($_SESSION['history'])) {
    $_SESSION['history'] = [];
}

if ($_POST) {
    if ($_POST["shoot"]=="true") {
        shoot();        
    }
    if ($_POST["clearHistory"]=="true") {
        clear_history();
    }
}



function clear_history() {
    $_SESSION['history'] = [];
    if (isset($_SESSION['last_attempt'])) {
        array_push($_SESSION['history'], $_SESSION['last_attempt']);
    }
}