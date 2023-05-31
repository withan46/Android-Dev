<?php

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);
    
    $data = array();
    $clinic_vat_number = $_GET["clinic_vat_number"];
    
    $query = "SELECT patient.ssn, patient.name,appointment.id, appointment.date, appointment.time, appointment.tos
                  FROM patient, appointment
                  WHERE patient.ssn = appointment.patient_ssn
                  AND appointment.clinic_vat_number = $clinic_vat_number 
                  AND   appointment.accepted = 'false'";

    $result = mysqli_query($dbh, $query);
    
    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_array($result)) {
            $data[] = $row;
        }
    }

    echo json_encode($data);
    mysqli_close($dbh);
?>