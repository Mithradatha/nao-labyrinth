cmake_minimum_required(VERSION 2.8)
project(cmdlnopts)

find_package(qibuild)

# Create a executable named cmdlnopts
# with the source file: main.cpp
qi_create_bin(cmdlnopts "main.cpp")

qi_use_lib(cmdlnopts BOOST_PROGRAM_OPTIONS

)
