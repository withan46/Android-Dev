<?php
	$data = array();
	$host = "localhost";
	$uname = "root";
	$pass = "";
	$dbname = "flexfit";

	// Connect to the database
	$dbh = mysqli_connect($host, $uname, $pass, $dbname) or die("cannot connect");

	// Execute the query
	$sql = "SELECT name, email, password  FROM user";
	$result = mysqli_query($dbh, $sql) or die("query failed");

	// Fetch the results and populate the array
	while ($row = mysqli_fetch_array($result)) {
		$data[] = array(
			'name' => $row['name'],
			'email' => $row['email'],
			'password' => $row['password']
		);
	}

	// Output the results as JSON
	header("Content-Type: application/json");
	echo json_encode($data);

	// Close the database connection
	mysqli_close($dbh);
?>
