<?php
	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="flexfit";

	$patient_ssn = $_GET['patient_ssn'];
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
	
	$sql = "SELECT GROUP_CONCAT(date) AS grouped_date, GROUP_CONCAT(tos) AS grouped_tos, GROUP_CONCAT(cost) AS grouped_cost FROM economic_movements WHERE patient_ssn = $patient_ssn";	
	$result = mysqli_query($dbh, $sql);
	
	$data = array();
	
	while ($row = mysqli_fetch_array($result)) { 
		$nested_data = array();
		$nested_data['grouped_date'] = $row['grouped_date'];
		$nested_data['grouped_tos'] = $row['grouped_tos'];
		$nested_data['grouped_cost'] = $row['grouped_cost'];
		$data[$patient_ssn] = $nested_data;
	}

	header("Content-Type: application/json");
	echo json_encode($data);
	mysqli_close($dbh);
?>