#include <iostream>
#include <thread>
#include <chrono>
#include <qrencode.h>

int main() {
    std::cout << "QR Service: Library check..." << std::endl;

    QRcode *qr = QRcode_encodeString("Test", 0, QR_ECLEVEL_L, QR_MODE_8, 1);

    if (qr != nullptr) {
        std::cout << "SUCCESS: QR Code library is working!" << std::endl;
        QRcode_free(qr);
    } else {
        std::cout << "ERROR: Could not generate QR." << std::endl;
    }

    while (true) {
        std::this_thread::sleep_for(std::chrono::seconds(10));
    }
    return 0;
}