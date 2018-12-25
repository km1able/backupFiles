wget -qO - http://packages.lunarg.com/lunarg-signing-key-pub.asc | sudo apt-key add -
sudo wget -qO /etc/apt/sources.list.d/lunarg-vulkan-1.1.92-bionic.list http://packages.lunarg.com/vulkan/1.1.92/lunarg-vulkan-1.1.92-bionic.list
sudo apt update
sudo apt install lunarg-vulkan-sdk

sduo apt-get install libglfw3 libglfw3-dev
##download/install sdk
##run both setup_tools.sh and source env_setup.sh
