
<html> 
<body> 
<?php echo "This section is for Working/Broken Encryption " ?> 
<form action = "crypto_work.php" method = "post"> 
Input password for working encryption algorithm  <input type ="text" name = "ProcEnter" > <br> 
<input type = "submit"> 
</form> 


<form action = "crpt_brk.php" method = "post"> 
Input password for self-made algorithm (ineffective) <input type ="text" name = "ProcEnter" > <br> 
<input type = "submit"> 
</form> 

<form action = "Salt_Wrk.php" method = "post"> 
Input password for working salted hash  <input type ="text" name = "ProcEnter" > <br> 
<input type = "submit"> 
</form> 


<form action = "Salt_Brk.php" method = "post"> 
Input password for non-salted hash<input type ="text" name = "ProcEnter" > <br> 
<input type = "submit"> 
</form> 
