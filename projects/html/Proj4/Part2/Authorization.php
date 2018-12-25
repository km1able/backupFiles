
<html> 
<body> 
<?php echo "Entered authorized information:  " ?> 
<form action = "authorized.php" method = "post">  
First Name: 
<input type ="text" name = "FirstName" > <br> 
Last Name:  
<input type ="text"  name = "LastName" > <br> 
Enter one word data to encrypt
<input type ="text"  name = "DataVal"  maxlength = "50"> <br> 

<input type = "submit"> 
</form> 

<?php echo "Enter unauthorized information:  " ?> 
<form action = "unauthorized.php" method = "post">  
First Name: 
<input type ="text" name = "FirstName" > <br> 
Last Name:  
<input type ="text"  name = "LastName" > <br> 
Enter one word data to encrypt
<input type ="text"  name = "DataVal"  maxlength = "50"> <br> 

<input type = "submit"> 
</form> 

</html> 