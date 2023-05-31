<?php

    $data = array();

    $patient_ssn = $_GET['patient_ssn']; 
    $tos = $_GET['tos'];
    $time = $_GET['time'];
    $date = $_GET['date'];
    $description = $_GET['description'];

    // Create Arguments for Connecting
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "flexfit";

    // Making Connection
    $dbh = mysqli_connect($host,$uname,$pass) or die("Cannot Connect");
    mysqli_select_db($dbh, $dbname);

    // Adding Data
    $sql = "INSERT INTO history (patient_ssn, tos, time, date, description) VALUES ($patient_ssn, $tos, $time, $date, $description)";
    mysqli_query($dbh, $sql);

    // Closing Connection
    mysqli_close($dbh); 


    // TESTED USING THIS LINK
    // http://localhost/AndroidDev/setHistory.php?patient_ssn=100&tos=%22%CE%91%CF%80%CE%BB%CE%AE%20%CE%A6%CF%85%CF%83%CE%B9%CE%BA%CE%BF%CE%B8%CE%B5%CF%81%CE%B1%CF%80%CE%B5%CE%AF%CE%B1%22&time=%2209:30:00%22&date=%222022-05-05%22&description=%22%CE%8C%CE%BB%CE%B1%20%CF%80%CE%AE%CE%B3%CE%B1%CE%BD%20%CE%BA%CE%B1%CE%BB%CE%AC%22

?>