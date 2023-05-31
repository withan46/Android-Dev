<?php

    $time = $_GET["time"];
    $date = $_GET["date"];
    $tos = $_GET["tos"];
    $clinicVATNumber = $_GET["clinicVATNumber"];
    $patient_ssn = $_GET["patient_ssn"];
    $accepted = $_GET["accepted"];

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);
    $sql = "INSERT into appointment (time, date, tos, clinic_vat_number, patient_ssn, accepted) values('" . $time . "','" . $date . "','" .$tos . "', '" . $clinicVATNumber . "', '" . $patient_ssn . "' , '" . $accepted . "')";
    echo $sql;
    mysqli_query($dbh, $sql);
    mysqli_close($dbh);

?>