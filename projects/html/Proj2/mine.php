<?php

    $result = shell_exec("sudo -S ./cmdInjection passwd");  
    echo $result; 
    $result = shell_exec ("sudo php -v"); 
    echo $result; 

    $result = shell_exec("sudo -S java CmdInjection"); 
    echo $result; 
    echo shell_exec ("sudo cat /usr/local/nginx/config/nginx.config"); 
?> 
