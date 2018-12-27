<?php 
function writeLine () {
 echo "Hello World <br> "; 
 
}

function varLine ($var) {
 echo "This line prints an input variable $var <br> "; 
 
}

function doSomething ($x, $y) {
 $thisSum = $x + $y; 
 echo "Doing some math... <br>"; 
 return $thisSum; 
}
writeline(); 
echo "Hello Aws <br>"; 
varLine ("Patricia"); 
$temp = doSomething(4, 60); 
echo "Sum of math is: $temp <br> "; 
echo "Today is: " . date("Y/m/d") . "<br>"; 
?>