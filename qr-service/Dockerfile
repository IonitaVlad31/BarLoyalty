FROM gcc:latest
WORKDIR /app

RUN apt-get update && apt-get install -y \
    cmake \
    libqrencode-dev \
    libpng-dev \
    libboost-all-dev \
    git

COPY . .

RUN mkdir -p build && cd build && cmake .. && make

CMD ["./build/QrService"]