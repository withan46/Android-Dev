<?php

    $clinicVATNumber = $_GET["clinicVATNumber"];
    $host="Localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "SELECT code, GROUP_CONCAT(name) AS grouped_names, GROUP_CONCAT(description) AS grouped_descriptions, GROUP_CONCAT(price) AS grouped_prices, GROUP_CONCAT(clinic_vat_number) AS grouped_clinicVATNumbers FROM `service` WHERE clinic_vat_number =" . $clinicVATNumber . " GROUP BY code";
    $result = mysqli_query($dbh, $sql);
    while ($row = mysqli_fetch_array($result)) {
    
        $nested_data = array();
        $nested_data['grouped_names'] = $row['grouped_names'];
        $nested_data['grouped_descriptions'] = $row['grouped_descriptions'];
        $nested_data['grouped_prices'] = $row['grouped_prices'];
        $nested_data['grouped_clinicVATNumbers'] = $row['grouped_clinicVATNumbers'];
        $data[$row['code']] = $nested_data;
    }

    header("Content-Type: application/json");
    echo json_encode($data);

    mysqli_close($dbh);
?>