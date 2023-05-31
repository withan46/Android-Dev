<?php

    $host="localhost";
    $uname="root";
    $pass="";
    $dbname="flexfit";
    $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
    mysqli_select_db($dbh, $dbname);

    $sql = "SELECT vat_number, GROUP_CONCAT(name) as grouped_names, GROUP_CONCAT(address) as grouped_addresses, GROUP_CONCAT(phone_number) as grouped_numbers, GROUP_CONCAT(email) as grouped_emails FROM `clinic` GROUP BY vat_number";
    $result = mysqli_query($dbh, $sql);
    while ($row = mysqli_fetch_array($result)) {
    
        $nested_data = array();
        $nested_data['grouped_names'] = $row['grouped_names'];
        $nested_data['grouped_addresses'] = $row['grouped_addresses'];
        $nested_data['grouped_numbers'] = $row['grouped_numbers'];
        $nested_data['grouped_emails'] = $row['grouped_emails'];
        $data[$row['vat_number']] = $nested_data;
    }

    header("Content-Type: application/json");
    echo json_encode($data);

    mysqli_close($dbh);
?>