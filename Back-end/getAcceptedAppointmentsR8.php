    <?php

    $data = array();
    $clinic_vat_number = $_GET['clinic_vat_number'];  // GET gets the number from Java Code

    // Create Arguments for Connecting
    $host = "localhost";
    $uname = "root";
    $pass = "";
    $dbname = "flexfit";

    // Making Connection
    $dbh = mysqli_connect($host,$uname,$pass) or die("Cannot Connect");
    mysqli_select_db($dbh, $dbname);


    // Fetching Data
    $sql = "SELECT clinic_vat_number, GROUP_CONCAT(appointment.time) as grouped_times, GROUP_CONCAT(appointment.date) as grouped_dates, GROUP_CONCAT(appointment.tos) as grouped_tos, GROUP_CONCAT(patient.ssn) as grouped_ssn, GROUP_CONCAT(patient.name) as grouped_names
            FROM appointment
            JOIN patient ON patient.ssn = appointment.patient_ssn
            WHERE clinic_vat_number = $clinic_vat_number";
    $result = mysqli_query($dbh, $sql);

    while ($row = mysqli_fetch_array($result)) {

        $nested_data = array();

        // Converting our SQL Query to Code
        $nested_data['grouped_times']= $row['grouped_times'];
        $nested_data['grouped_dates']= $row['grouped_dates'];
        $nested_data['grouped_tos']= $row['grouped_tos'];
        $nested_data['grouped_ssn']= $row['grouped_ssn'];
        $nested_data['grouped_names'] = $row['grouped_names'];
        
        $data[$row['clinic_vat_number']] = $nested_data;
    }

    header("Content-Type: application/json");
    echo json_encode($data);


    // Closing Connection
    mysqli_close($dbh);

?>