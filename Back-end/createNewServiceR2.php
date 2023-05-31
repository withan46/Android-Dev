<?php
    $data = array();
    $code = $_GET["code"];
    $name = $_GET["name"];
    $description = $_GET["description"];
    $price = $_GET["price"];
    $clinic_vat_number = $_GET["clinic_vat_number"];

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";

    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "INSERT into service values('" . $code . "','" . $name . "','" . $description . "','" . $price . "','" . $clinic_vat_number . "')";
    echo $sql;
    mysqli_query($dbh, $sql);
    mysqli_close($dbh);
?>