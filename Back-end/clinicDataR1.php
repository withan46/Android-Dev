<?php
	 $data = array();
	 $vat_number = $_GET["vat_number"];
	 $name = $_GET["name"];
	 $address = $_GET["address"];
	 $phone_number = $_GET["phone_number"];
	 $email = $_GET["email"];
	 
	 
	 $host="localhost";
	 $uname="root";
	 $pass="";
	 $dbname="flexfit";
	 $dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	 mysqli_select_db($dbh, $dbname);
	 
	 $sql = "INSERT into clinic values('" . $vat_number . "','" . $name . "','" . $address . "','". $phone_number . "','". $email . "')";
	
	 echo $sql;
	 mysqli_query($dbh, $sql);
	 mysqli_close($dbh);
?>