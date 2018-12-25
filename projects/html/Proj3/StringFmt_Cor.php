<html> 
<body> 

<?php 
echo "This is working..."; 
echo "<br>"; 
 $input = $_POST["ProcEnter"]; 
 $result = shell_exec("sudo -S ./StringFmt_Cor"); 
 echo $result; 

?>


</body> 
</html> 