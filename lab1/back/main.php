<?php
include 'logic.php';


session_start();
if (!isset($_SESSION['history'])) {
    $_SESSION['history'] = [];
}

if ($_POST) {
    shoot();
}