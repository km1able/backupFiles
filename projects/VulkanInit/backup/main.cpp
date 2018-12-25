/*ENCOUNTERED ERRORS: 
//September, 2018: 
--libvulkan.so.1 not found during code compile/execution
	--solution: install Linux SDK LunarG Vulkan 
	--run 'source setup-env.sh' in SDK root directory
	--run './build_tools.sh"
	--execute 'via' in x86_64/bin to confirm all is well 
	--problem solved....
	
	*/
#define GLFW_INCLUDE_VULKAN
#include <GLFW/glfw3.h>

#include <iostream>
#include <stdexcept>
#include <functional>
#include <cstdlib>
#include <cstring>

//for window sizing
const int WIDTH = 800;
const int HEIGHT = 600;

//configuration variables to specify the layers to enable
//and whether to enable them.
//based on whether the program is in DEBUG mode or
//the NDEBUG macro is part of C++ standard and means "Not Debug"
const std::vector<const char *> validationLayers = {
	"VK_LAYER_LUNARG_standard_validation"};

//check for debug --> don't define NDEBUG -->> yields true
//#define NDEBUG // --> handy thing about Visual Studio Code: will highlight NDEBUG appropriately
#ifdef NDEBUG
const bool enableValidationLayers = false;
#else
const bool enableValidationLayers = true;
#endif

VkResult CreateDebugUtilsMessengerEXT(VkInstance instance,
									  const VkDebugUtilsMessengerCreateInfoEXT *pCreateInfo,
									  const VkAllocationCallbacks *pAllocator,
									  VkDebugUtilsMessengerEXT *pCallback)
{
	auto func = (PFN_vkCreateDebugUtilsMessengerEXT)vkGetInstanceProcAddr(
		instance, "vkCreateDebugUtilsMessengerEXT");
	if (func != nullptr)
	{
		return func(instance, pCreateInfo, pAllocator, pCallback);
	}
	else
	{
		return VK_ERROR_EXTENSION_NOT_PRESENT;
	}
}
/*
VkResult CreateDebugUtilsMessengerEXT(VkInstance instance,
									  const VkDebugUtilsMessengerCreateInfoEXT *pCreateInfo,
									  const VkAllocationCallbacks *pAllocator,
									  VkDebugUtilsMessengerEXT *pCallback)
{
	auto func = (PFN_vkCreateDebugUtilsMessengerEXT)vkGetInstanceProcAddr(
		instance, "vkCreateDebugUtilsMessengerEXT");
	if (func != nullptr)
	{
		return func(instance, pCreateInfo, pAllocator, pCallback);
	} //end if
	else
	{
		return VK_ERROR_EXTENSION_NOT_PRESENT;
	} //end else
} //end VkResult CreateDebugUtilsMessengerExt
*/
class HelloTriangleApplication
{

  public:
	void run()
	{
		initWindow();
		initVulkan();
		mainLoop();
		cleanup();

	} //end fn: run().

  private:
	GLFWwindow *window; //class member for handle to window

	VkInstance instance;			   //class member for handle to Vkinstance
	VkDebugUtilsMessengerEXT callback; //callbacks are called 'messengers' in Vulkan --> interact
	//handled with handler structs

	void initWindow()
	{
		glfwInit();
		//tell GLFW not to create OpenGL context --> GLFW originally
		//desigined for OpenGL
		glfwWindowHint(GLFW_CLIENT_API, GLFW_NO_API);
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE);

		//specifiy Width, Height, and Title of window
		//fourth param: specify a monitor to open window on
		//last param only relevant to OpenGL
		window = glfwCreateWindow(WIDTH, HEIGHT, "Vulkan", nullptr, nullptr);

	} //end fn: initWindow().

	void initVulkan()
	{

		createInstance();
		setupDebugCallback();

	} //end fn: initVulkan ().

	void mainLoop()
	{

		//keep window open until message sent for exit
		while (!glfwWindowShouldClose(window))
		{
			glfwPollEvents();
		} //end while: (!glfwWindowShouldClose(window) )

	} //end fn: mainLoop ().

	void cleanup()
	{

		vkDestroyInstance(instance, nullptr);
		glfwDestroyWindow(window);

		glfwTerminate();
	} //end fn: cleanup().

	void createInstance()
	{
		//check for validation Layer support using -checkValidationLayerSupport fn () -
		if (enableValidationLayers && !checkValidationLayerSupport())
		{
			throw std::runtime_error("validation layers requested, but not available");
		} //end if: ..validation layers

		//to create an instance we first need information struct
		//data is technically optional, however, may provide useful information to driver
		//to optimize for our specific application
		//i.e. uses well-known graphics engine with certain behaviors --> struct called:
		//VkApplicationInfo
		VkApplicationInfo appInfo = {};
		appInfo.sType = VK_STRUCTURE_TYPE_APPLICATION_INFO;
		appInfo.pApplicationName = "Hello Triangle";
		appInfo.applicationVersion = VK_MAKE_VERSION(1, 0, 0);
		appInfo.pEngineName = "No Engine";
		appInfo.engineVersion = VK_MAKE_VERSION(1, 0, 0);
		appInfo.apiVersion = VK_API_VERSION_1_0;

		//this struct is NOT OPTIONAL
		//tells Vulkan driver which gloabl extensions and validation layers we want to use
		//global here means they apply to the entire program and not a specific device
		VkInstanceCreateInfo createInfo = {};
		createInfo.sType = VK_STRUCTURE_TYPE_INSTANCE_CREATE_INFO;
		createInfo.pApplicationInfo = &appInfo;

		/*use -getRequiredExtensions fn to getReq'd extensions */
		auto extensions = getRequiredExtensions();
		createInfo.enabledExtensionCount = static_cast<uint32_t>(extensions.size());
		createInfo.ppEnabledExtensionNames = extensions.data();
		/////////////////////////////////////////////////////////////////////////////////
		//determine supported extensions
		//optional: helpful to determine properties of graphics card/system and Vulkan interop
		/* Redundant code after -auto extensions = getRequiredExtensions(); 
		uint32_t extensionCount = 0;
		vkEnumerateInstanceExtensionProperties(nullptr, &extensionCount, nullptr);
		std::vector<VkExtensionProperties> extensions(extensionCount);
		vkEnumerateInstanceExtensionProperties(nullptr, &extensionCount, extensions.data());
		//each VkExtensionProperties struct contains name and version of an extension
		//list each with a loop
		std::cout << "available extensions:" << std::endl;
		for (const auto &extension : extensions)
		{
			std::cout << "\t" << extension.extensionName << std::endl;
		} //end for: extension.
		//challenge: determine if extensions are all supported in glfwGetRequiredInstanceExtensions
		*/
		/////////////////////////////////////////////////////////////////////////////////

		uint32_t glfwExtensionCount = 0;
		const char **glfwExtensions;
		//get required extension for platform specific window system
		//GLFW has built-in function that returns the extension(s) it needs
		glfwExtensions = glfwGetRequiredInstanceExtensions(&glfwExtensionCount);

		createInfo.enabledExtensionCount = glfwExtensionCount;
		createInfo.ppEnabledExtensionNames = glfwExtensions;

		createInfo.enabledLayerCount = 0;

		//include validation layer names if they are enabled
		if (enableValidationLayers)
		{
			createInfo.enabledLayerCount = static_cast<uint32_t>(validationLayers.size());
			createInfo.ppEnabledLayerNames = validationLayers.data();
		} // end if
		else
		{
			createInfo.enabledLayerCount = 0;
		} //end else

		//create Vulkan instance
		//general pattern for object creation:
		//--> pointer to struct with creation info
		//--> pointer to custom allocator callbacks (nullptr in tutorial...)
		//--> pointer to the variable that stores the handle to the new object
		// returns type VkResult (either VK_SUCCESS or error code)
		if (vkCreateInstance(&createInfo, nullptr, &instance) != VK_SUCCESS)
		{
			throw std::runtime_error("failed to create instance!");
		} //end if
	}	 //end fn: createInstance().

	void setupDebugCallback()
	{
		if (!enableValidationLayers)
			return;

		VkDebugUtilsMessengerCreateInfoEXT createInfo = {};
		createInfo.sType = VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT;
		createInfo.messageSeverity = VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT | VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT | VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT;
		createInfo.messageType = VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT | VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT | VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT;
		createInfo.pfnUserCallback = debugCallback;

		if (CreateDebugUtilsMessengerEXT(instance, &createInfo, nullptr, &callback) != VK_SUCCESS)
		{
			throw std::runtime_error("failed to set up debug callback!");
		}
	}
	/*
	void setupDebugCallback()
	{
		if (!enableValidationLayers)
			return;

		VkDebugUtilsMessengerCreateInfoEXT createInfo = {};
		createInfo.sType = VK_STRUCTURE_TYPE_DEBUG_UTILS_MESSENGER_CREATE_INFO_EXT;
		createInfo.messageSeverity = VK_DEBUG_UTILS_MESSAGE_SEVERITY_VERBOSE_BIT_EXT |
									 VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT |
									 VK_DEBUG_UTILS_MESSAGE_SEVERITY_ERROR_BIT_EXT;
		createInfo.messageType - VK_DEBUG_UTILS_MESSAGE_TYPE_GENERAL_BIT_EXT |
			VK_DEBUG_UTILS_MESSAGE_TYPE_VALIDATION_BIT_EXT |
			VK_DEBUG_UTILS_MESSAGE_TYPE_PERFORMANCE_BIT_EXT;
		createInfo.pfnUserCallback = debugCallback;

		if (CreateDebugUtilsMessengerEXT(instance, &createInfo, nullptr, &callback) != VK_SUCCESS)
		{
			throw std::runtime_error("failed to set up debug callback!");
		}
	} //end setupDebugCallback
	*/

	//Message Callback
	//just enabling layers doesn't help --> they have no way to relay the debug messages back to our
	//program. To receive those messages we have to set up a callback, which requires the
	//VK_EXT_debug_utils extension
	std::vector<const char *> getRequiredExtensions()
	//returns required list of extensions based on whether validation layers are enabled or not
	{
		uint32_t glfwExtensionCount = 0;
		const char **glfwExtensions;
		glfwExtensions = glfwGetRequiredInstanceExtensions(&glfwExtensionCount);

		std::vector<const char *> extensions(glfwExtensions, glfwExtensions + glfwExtensionCount);

		if (enableValidationLayers)
		{
			extensions.push_back(VK_EXT_DEBUG_UTILS_EXTENSION_NAME);
		} //end: enableValidationLayers

		return extensions;
	}
	//add a new function -checkValidationLayerSupport- to check if requested layers are available
	//first list all available layers using vkEnumerateInstanceLayerProperties fn (usage identical to
	//vkEnumerateInstanceExtensionProperties discussed in the instance creation chapter)
	bool checkValidationLayerSupport()
	{
		uint32_t layerCount;
		vkEnumerateInstanceLayerProperties(&layerCount, nullptr);

		std::vector<VkLayerProperties> availableLayers(layerCount);
		vkEnumerateInstanceLayerProperties(&layerCount, availableLayers.data());

		for (const char *layerName : validationLayers)
		{
			bool layerFound = false;

			for (const auto &layerProperties : availableLayers)
			{
				if (strcmp(layerName, layerProperties.layerName) == 0)
				{
					layerFound = true;
					break;
				} //end if (strcmp)
			}	 //end for: layerProperties: availableLayers

			if (!layerFound)
			{
				return false;
			} //end: !layerFound
		}	 //end for: layerName: validationLayers
		return true;
	}

	static VKAPI_ATTR VkBool32 VKAPI_CALL debugCallback(VkDebugUtilsMessageSeverityFlagBitsEXT messageSeverity, VkDebugUtilsMessageTypeFlagsEXT messageType, const VkDebugUtilsMessengerCallbackDataEXT *pCallbackData, void *pUserData)
	{
		std::cerr << "validation layer: " << pCallbackData->pMessage << std::endl;

		return VK_FALSE;
	}

	/*
	static VKAPI_ATTR VkBool32 VKAPI_CALL
	debugCallback(VkDebugUtilsMessageSeverityFlagBitsEXT messageSeverity,
				  VkDebugUtilsMessageTypeFlagsEXT messageType,
				  const VkDebugUtilsMessengerCallbackDataEXT *pCallbackData,
				  void *pUserData)
	{
	//	if (messageSeverity >= VK_DEBUG_UTILS_MESSAGE_SEVERITY_WARNING_BIT_EXT)
	//	{
			//Message is important enough to show
	//	}
	//	
	std::cerr << "validation layer: " << pCallbackData->pMessage << std::endl;
	return VK_FALSE;
} //end: debugCallback
*/

}; //end class: HelloTriangleApplication

int main()
{
	HelloTriangleApplication app;
	std::cout << "Created app" << std::endl;
	try
	{
		std::cout << "Running app...";
		app.run();
	}
	catch (const std::exception &e)
	{
		std::cout << "Catching error..." << std::endl;
		//std::cerr << e.what() << std::endl;
		std::cout << e.what() << std::endl;

		return EXIT_FAILURE;
	} //end catch

	std::cout << "Ending after try/catch..." << std::endl;
	return EXIT_SUCCESS;
} //end fn: main ()