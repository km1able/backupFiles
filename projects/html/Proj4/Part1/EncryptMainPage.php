
<html> 
<body> 
<?php echo "This section is for Working/Broken File Storage Encryption " ?> 
<form action = "DataCrypt.php" method = "post">  
First Name: 
<input type ="text" name = "FirstName" > <br> 
Last Name:  
<input type ="text"  name = "LastName" > <br> 
Enter one word data to encrypt
<input type ="text"  name = "DataVal"  maxlength = "50"> <br> 

<input type = "submit"> 
</form> 


<form action = "DataUncrypt.php" method = "post"> 
First Name: 
<input type ="text" name = "FirstName" > <br> 
Last Name:  
<input type ="text"  name = "LastName" > <br> 
Enter one word data to encrypt
<input type ="text"  name = "DataVal"  maxlength = "50"> <br> 

<input type = "submit"> 
</form> 

