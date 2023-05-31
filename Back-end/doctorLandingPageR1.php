<?php
	$data = array();
	$host = "localhost";
	$uname = "root";
	$pass = "";
	$dbname = "flexfit";
	$email = $_GET['email'];
	$first_name = $_GET['first_name'];
	$last_name = $_GET['last_name'];
	
	// Connect to the database
	$dbh = mysqli_connect($host, $uname, $pass, $dbname) or die("cannot connect");

	// Execute the query
	$sql = "SELECT * FROM doctor WHERE email = '$email' AND name = '$first_name' AND surname = '$last_name'";
	$result = mysqli_query($dbh, $sql) or die("query failed");

	// Fetch the results and populate the array
	while ($row = mysqli_fetch_array($result)) {
		$data[] = array(
			'vat_reg_number' => $row['vat_reg_num'],
			'name' => $row['name'],
			'surname' => $row['surname'],
			'email' => $row['email'],
			'clinic_vat_number' => $row['clinic_vat_number']
		);
	}

	// Output the results as JSON
	header("Content-Type: application/json");
	echo json_encode($data);

	// Close the database connection
	mysqli_close($dbh);
?>
