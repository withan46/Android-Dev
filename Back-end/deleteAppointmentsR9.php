<?php

    $localDate = $_GET['localDate'];

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "DELETE FROM `appointment` WHERE date < '" . $localDate . "'";

    echo $sql;

    mysqli_query($dbh, $sql);
    mysqli_close($dbh);
?>