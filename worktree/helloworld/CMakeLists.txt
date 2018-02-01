cmake_minimum_required(VERSION 2.8)
project(helloworld)

find_package(qibuild)

# Create a executable named helloworld
# with the source file: main.cpp
qi_create_bin(helloworld "main.cpp")

# Add a simple test:
qi_create_test(test_helloworld "test.cpp")

#test