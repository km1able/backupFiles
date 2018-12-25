<html> 
<body> 

<?php 
echo "<br>"; 
 $input = $_POST["ProcEnter"]; 
 $result = shell_exec("sudo -S java Salt_Wrk $input "); 
 echo $result; 

?>


</body> 
</html> 