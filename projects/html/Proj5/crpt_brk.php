<html> 
<body> 

<?php 

echo "<br>"; 
 $input = $_POST["ProcEnter"]; 
 $result = shell_exec("sudo -S java CryptoBroken $input"); 
 echo $result; 

?>


</body> 
</html> 