<?php

    $ssn = $_GET["ssn"];
    $email = $_GET["email"];
    $name = $_GET["name"];
    $phone_number = $_GET["phone_number"];
    $vat_reg_num = $_GET["vat_reg_num"];

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh,$dbname);

    $sql = "INSERT INTO patient VALUES('" . $ssn  ."','" .  $email ."','" . $name ."','" . $phone_number ."','" . $vat_reg_num ."')";

    echo $sql;
    mysqli_query($dbh,$sql);
    mysqli_close($dbh);

?>