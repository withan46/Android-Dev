<?php 
    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $appointmentID = $_GET['appointment_id'];

    $query = "UPDATE appointment set accepted = 'True' where appointment.id =$appointmentID ";

    echo $query;

    mysqli_query($dbh, $query);
    mysqli_close($dbh);
?>