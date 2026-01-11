cmake_minimum_required(VERSION 3.10)
project(QrService)

set(CMAKE_CXX_STANDARD 17)

find_package(PNG REQUIRED)

add_executable(QrService main.cpp)

target_include_directories(QrService PRIVATE ${PNG_INCLUDE_DIRS})
target_link_libraries(QrService PRIVATE qrencode ${PNG_LIBRARIES})

