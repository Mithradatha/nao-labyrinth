cmake_minimum_required(VERSION 2.8)
project(sayhelloworld)

find_package(qibuild)

# Create a executable named sayhelloworld
# with the source file: main.cpp
qi_create_bin(sayhelloworld "main.cpp")

qi_use_lib(sayhelloworld BOOST_PROGRAM_OPTIONS ALCOMMON ALPROXIES)