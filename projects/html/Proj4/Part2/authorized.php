<html> 
<body> 

<?php 
echo "<br>"; 
 $firstname = $_POST['FirstName']; 
 $lastname  = $_POST['LastName']; 
 $dataval   = $_POST["DataVal"]; 
 $result = shell_exec("sudo -S java Authorized $firstname $lastname $dataval"); 
 echo $result; 

?>


</body> 
</html> 