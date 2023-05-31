<?php
	$data = array();
	$host = "localhost";
	$uname = "root";
	$pass = "";
	$dbname = "flexfit";
	$email = $_GET['email'];
	$name = $_GET['name'];
	
	// Connect to the database
	$dbh = mysqli_connect($host, $uname, $pass, $dbname) or die("cannot connect");

	// Execute the query
	$sql = "SELECT * FROM patient WHERE email = '$email' AND name = '$name'" ;
	$result = mysqli_query($dbh, $sql);

	// Fetch the results and populate the array
	while ($row = mysqli_fetch_array($result)) {
		$data[] = array(
			'ssn' => $row['ssn'],
			'email' => $row['email'],
			'name' => $row['name'],
			'phone_number' => $row['phone_number'],
			'vat_reg_number' => $row['vat_reg_num']
		);
	}

	// Output the results as JSON
	header("Content-Type: application/json");
	echo json_encode($data);

	// Close the database connection
	mysqli_close($dbh);
?>
