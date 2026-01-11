FROM gcc:latest
WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends \
    cmake \
    git \
    libasio-dev \
    libqrencode-dev \
    libpng-dev \
    libboost-system-dev \
    libboost-thread-dev \
    libssl-dev \
 && rm -rf /var/lib/apt/lists/*

COPY . .

RUN mkdir -p build && cd build && cmake .. && make

CMD ["./build/QrService"]