<?php
$patients = array();
// Connect to the MySQL database
$host = "localhost";
$username = "root";
$password = "";
$dbname = "flexfit";
$vatRegNum = $_GET['vatRegNum'];

$dbh = mysqli_connect($host, $username, $password) or die("cannot connect");
mysqli_select_db($dbh, $dbname);

// Get today's date
$today = date("Y-m-d");

// Fetch the patients with appointments scheduled for today
$sql = "SELECT DISTINCT patient.*, appointment.date, appointment.time, appointment.tos
        FROM patient
        LEFT JOIN appointment ON patient.ssn = appointment.patient_ssn
        WHERE appointment.date = '$today' AND patient.vat_reg_num = $vatRegNum";
$result = mysqli_query($dbh, $sql);

if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $patient = array(
            "name" => $row["name"],
            "email" => $row["email"],
            "ssn" => $row["ssn"],
            "phone" => $row["phone_number"],
            "next_appointment_date" => $row["date"],
            "next_appointment_time" => $row["time"],
            "case" => $row["tos"],
            "history" => array() // Initialize an array for patient's history
        );

        // Fetch patient's history data
        $patientSSN = $row["ssn"];
        $historySql = "SELECT date, time FROM appointment WHERE patient_ssn = '$patientSSN' AND date < '$today'";
        $historyResult = mysqli_query($dbh, $historySql);
        if ($historyResult->num_rows > 0) {
            while ($historyRow = $historyResult->fetch_assoc()) {
                $history = array(
                    "date" => $historyRow["date"],
                    "time" => $historyRow["time"]
                );
                array_push($patient["history"], $history);
            }
        }

        array_push($patients, $patient);
    }
}

// Return the patients as JSON data
//header('Content-Type: application/json');
echo json_encode($patients);

// Close the database connection
mysqli_close($dbh);
?>
