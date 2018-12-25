
<html> 
<body> 

<?php 
echo "This is working..."; 
echo "<br>"; 
 $input = $_POST["ProcEnter"]; 
 $result = shell_exec("sudo -S ./crpt_wrk $input"); 
 echo $result; 

?>


</body> 
</html> 