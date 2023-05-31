<?php
    $data = array();

    $date=$_GET['date'];
	$clinic=$_GET['clinic'];
    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);
    $sql = "SELECT GROUP_CONCAT(time) AS grouped_times, GROUP_CONCAT(tos) AS grouped_tos, GROUP_CONCAT(patient.name) AS grouped_names
	FROM appointment JOIN patient ON patient.ssn = appointment.patient_ssn 
	WHERE date = $date AND accepted='True' AND clinic_vat_number = $clinic";
    $result = mysqli_query($dbh, $sql);
    while ($row = mysqli_fetch_array($result)) {
        $nested_data = array();
        $nested_data['grouped_times']= $row['grouped_times'];
        $nested_data['grouped_tos']= $row['grouped_tos'];
        $nested_data['grouped_names']= $row['grouped_names'];
        $data[$date] = $nested_data;
    }
    header("Content-Type: application/json");
    echo json_encode($data);
    mysqli_close($dbh);
?>