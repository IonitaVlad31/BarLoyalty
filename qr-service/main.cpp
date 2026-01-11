#include "crow.h"
#include <qrencode.h>

int main() {
    crow::SimpleApp app;

    CROW_ROUTE(app, "/")([](){
        return "Salut! Serverul QR C++ ruleaza!";
    });

    CROW_ROUTE(app, "/generate").methods(crow::HTTPMethod::POST)
    ([](const crow::request& req){
        (void)req;
        return crow::response(200, "Aici va fi QR-ul");
    });

    app.port(8081).multithreaded().run();
}