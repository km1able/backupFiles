


sudo add-apt-repository ppa:ubuntu-desktop/ubuntu-make
sudo apt-get update
sudo apt-get install ubuntu-make

##for VSCODE --> .net c#
sudo add-apt-repository -y "deb [arch=amd64] https://packages.microsoft.com/repos/vscode stable main"
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys EB3E94ADBE1229CF

sudo add-apt-repository universe
sudo add-get install apt-transport-https
sudo apt-get update
sudo apt-get install dotnet-sdk-2.2

##java
sudo apt install default-jre
sudo apt install default-jdk
java --version

##boost

sudo apt-get install build-essential g++ python-dev autotools-dev libicu-dev build-essential libbz2-dev libboost-all-dev
sudo apt-get install boost
sudo apt-get install libboost-all-dev

sudo apt update
sudo apt -y install code

##setup apache web server + mysql 
sudo apt install apache2
sudo ufw app list
sudo ufw allow 'Apache'
##sudo ufw status
##sudo systemctl status apache2
sudo mkdir -p /var/www/example.com/html
sudo chown -R $USER:$USER /var/www/example.com/html
sudo chmod -R 755 /var/www/example.com

sudo apt-get install mysql-server
sudo ufw allow mysql 
##systemctl start mysql 
##systemctl enable mysql 
sudo apt-get install curl
sudo apt-get install php libapache2-mod-php php-mcrypt php-mysql
##apt-cache-search php- | less  
##displays all php modules 


touch /var/www/example.com/html/index.html


sudo apt install snapd-xdg-open
##sudo snap install vscode --classic

##umake ide visual-studio-code
##setup subversion source control 
sudo apt install subversion db5.3-util libapache2-mod-svn subversion-tools
