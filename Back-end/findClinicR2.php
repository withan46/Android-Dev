<?php
    $data = array();
    $clinicName = $_GET["name"];
    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";

    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "SELECT vat_number FROM clinic WHERE name='" . $clinicName ."'";
    $result = mysqli_query($dbh, $sql);
    while ($row = mysqli_fetch_array($result)) {
        $data[$row['vat_number']] = $row['vat_number'];
    }
    header("Content-Type: application/json");
    echo json_encode($data);
    mysqli_close($dbh);
?>