cmake_minimum_required(VERSION 2.8)
project(sayremotemodule)

find_package(qibuild)

# Create a list of source files
set(_srcs
    mymodule.cpp
    mymodule.h
    main.cpp)

# Create a executable named sayremotemodule
# with the source file: main.cpp
qi_create_bin(sayremotemodule ${_srcs})

qi_use_lib(sayremotemodule BOOST_PROGRAM_OPTIONS ALCOMMON ALPROXIES)
