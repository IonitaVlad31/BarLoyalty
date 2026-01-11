# Etapa 1: Build environment

FROM gcc:latest AS build
WORKDIR /app


# Instalam biblioteci externe (build-time)

RUN apt-get update && apt-get install -y --no-install-recommends \
    cmake \
    libqrencode-dev \
    libpng-dev \
 && rm -rf /var/lib/apt/lists/*

COPY . .

RUN mkdir build && cd build && cmake .. && make

# Etapa 2: Runtime environment

FROM ubuntu:latest
WORKDIR /app

# Runtime libs (ca sa nu lipsesca .so la rulare)

RUN apt-get update && apt-get install -y --no-install-recommends \
    libstdc++6 \
    libqrencode4 \
    libpng16-16 \
 && rm -rf /var/lib/apt/lists/*

COPY --from=build /app/build/QrService .

CMD ["./QrService"]
