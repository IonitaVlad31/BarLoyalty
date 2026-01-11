# Etapa 1: Build environment
FROM gcc:latest AS build
WORKDIR /app

RUN apt-get update \
	&& apt-get install -y --no-install-recommends cmake \
	&& rm -rf /var/lib/apt/lists/*

COPY . .

RUN mkdir build && cd build && cmake .. && make

# Etapa 2: Runtime environment
FROM ubuntu:latest
WORKDIR /app

RUN apt-get update \
	&& apt-get install -y --no-install-recommends libstdc++6 \
	&& rm -rf /var/lib/apt/lists/*

COPY --from=build /app/build/QrService .

CMD ["./QrService"]
