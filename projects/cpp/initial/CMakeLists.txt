##usage: cmake (CMakeLists.txt) --> make in cli --> builds project
cmake_minimum_required(VERSION 3.10.2) 
project (InitialProject)
set (Boost_USE_STATIC_LIBS   	ON) ##FIND STATIC LIBS
set (Boost_USE_DEBUG_LIBS	OFF) ##ignore debug libs
set (Boost_USE_RELEASE_LIBS	ON)  ##only find release libs
set (Boost_USE_MULTITHREADED	ON)  ##
set (Boost_USE_STATIC_RUNTIME	OFF) ##
find_package(Boost 1.67.0 COMPONENTS thread system)
if (Boost_FOUND)
	include_directories({Boost_INCLUDE_DIRS})
	add_executable(tria main.cpp Triangle.cpp Maths.cpp IShape2D.hpp)
	target_link_libraries(tria ${Boost_LIBRARIES})
endif()
if (NOT Boost_FOUND) 
	set(Boost_USE_STATIC_LIBS NOT)
	find_package(Boost COMPONENTS thread system)
endif()
if (Boost_FOUND) 
	include_directories({Boost_INCLUDE_DIRS})
        add_executable(InitialProject main.cpp Triangle.cpp Maths.cpp IShape2D.hpp)
        target_link_libraries(InitialProject ${Boost_LIBRARIES})
endif()
