<?php

    $clinicVATNumber = $_GET["clinicVATNumber"];
    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "SELECT clinic_vat_number, GROUP_CONCAT(id) as grouped_id, GROUP_CONCAT(patient_ssn) as grouped_ssn, GROUP_CONCAT(patient.name) as grouped_names,GROUP_CONCAT(time) as grouped_times, GROUP_CONCAT(date) as grouped_dates, GROUP_CONCAT(tos) as grouped_tos, GROUP_CONCAT(accepted) as grouped_accepted FROM `Appointment` JOIN patient ON patient.ssn = Appointment.patient_ssn WHERE clinic_vat_number =" . $clinicVATNumber . " GROUP BY clinic_vat_number";
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