#include <iostream>
#include <thread>
#include <chrono>

int main() {
    std::cout << "QR Service (C++) is starting..." << std::endl;

    // Simulam un server care ruleaza infinit
    while (true) {
        std::this_thread::sleep_for(std::chrono::seconds(5));
        std::cout << "QR Service is alive..." << std::endl;
    }
    return 0;
}