<?php

    $data = array();
    $patientSSN = $_GET["patientSSN"];
    $date = $_GET["date"];
    $time = $_GET['time'];
    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "SELECT description, time, date, tos FROM `history` WHERE patient_ssn = '$patientSSN' AND date = '$date' AND time = '$time'";
    $result = mysqli_query($dbh, $sql);

    if (mysqli_num_rows($result) > 0) {
        while ($row = mysqli_fetch_array($result)) {

            $nested_data = array();
            $nested_data['description'] = $row['description'];
            $nested_data['time'] = $row['time'];
            $nested_data['date'] = $row['date'];
            $nested_data['tos'] = $row['tos'];
            $data[$patientSSN] = $nested_data;
        }
    }
    
    if(!empty($data))
        echo json_encode($data);
    
    mysqli_close($dbh);
?>