<html> 
<body> 

<?php 
echo "This is working..."; 
echo "<br>"; 
 $input = $_POST["ProcEnter"]; 
 $result = shell_exec("sudo -S ./IntOverflow"); 
 echo $result; 

?>


</body> 
</html> 