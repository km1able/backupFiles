#Comments for make files 
#the variable CC will be the compiler to use
CC = gcc

#both of these work --> make sure -L and -I have spaces for expansion of variables
#VULKAN_SDK_PATH = ~/vulkan/x86_64
VULKAN_SDK_PATH = /home/kelly/vulkan/1.1.92.1/x86_64
#compiler flags
CFLAGS = -std=c++17 -I $(VULKAN_SDK_PATH)/include

#linker flags
LDFLAGS = -L $(VULKAN_SDK_PATH)/lib `pkg-config --static --libs glfw3` -lvulkan
#first flag specifies we want to be able to find libraries like libvulkan.so in x86_64/lib
#the second component invokes pkg-config to automatically retrieve all of the linker flags #necessary to build an application with GLFW
#finally, -lvulkan links with the Vulkan function loader that comes with the Lunar SDK specifying


#specifying rule for VulkanTest compilation is straightforward. 
VulkanTest: main.cpp
	g++ $(CFLAGS) -o VulkanTest main.cpp $(LDFLAGS)

.PHONY: test clean

test: VulkanTest #lines immediately after colon are dependencies, next lines are commands
		LD_LIBRARY_PATH=$(VULKAN_SDK_PATH)/lib VK_LAYER_PATH=$(VULKAN_SDK_PATH)/etc/explicit_layer.d ./VulkanTest
		./VulkanTest

clean: 
	rm -f VulkanTest
