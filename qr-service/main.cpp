#include "crow.h"
#include <qrencode.h>
#include <iostream>
#include <sstream>
#include <string>

// Functie auxiliara care transforma structura QR in XML (SVG)
std::string toSvg(QRcode* qr, int margin) {
    if (!qr) return "";

    int size = qr->width;
    int realSize = size + margin * 2;
    std::ostringstream out;

    // Header SVG
    out << "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    out << "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" "
        << "viewBox=\"0 0 " << realSize << " " << realSize << "\" stroke=\"none\">\n";

    // Background Alb
    out << "<rect width=\"100%\" height=\"100%\" fill=\"#FFFFFF\"/>\n";
    out << "<path d=\"";

    // Desenam patratelele negre
    for (int y = 0; y < size; y++) {
        for (int x = 0; x < size; x++) {
            // Verificam daca bitul e setat (1 = negru)
            unsigned char b = qr->data[y * size + x];
            if (b & 1) {
                out << "M" << (x + margin) << "," << (y + margin) << "h1v1h-1z ";
            }
        }
    }

    out << "\" fill=\"#000000\"/>\n";
    out << "</svg>";

    return out.str();
}

int main() {
    crow::SimpleApp app;

    // 1. Endpoint simplu de test
    CROW_ROUTE(app, "/")([](){
        return "QR Service (C++) is Ready!";
    });

    // 2. Endpoint Generare QR
    // Asteapta un JSON: { "data": "textul_pentru_qr" }
    CROW_ROUTE(app, "/generate").methods(crow::HTTPMethod::POST)([](const crow::request& req){
        auto x = crow::json::load(req.body);
        if (!x) return crow::response(400, "Invalid JSON");

        std::string qrText = x["data"].s();
        std::cout << "Generare QR pentru: " << qrText << std::endl;

        // Folosim biblioteca qrencode
        QRcode* qr = QRcode_encodeString(qrText.c_str(), 0, QR_ECLEVEL_M, QR_MODE_8, 1);

        if (qr) {
            std::string svg = toSvg(qr, 4); // Margin 4
            QRcode_free(qr);

            crow::response res(svg);
            res.set_header("Content-Type", "image/svg+xml; charset=utf-8");
            return res;
        } else {
            return crow::response(500, "Error generating QR");
        }
    });

    app.port(8081).multithreaded().run();
}