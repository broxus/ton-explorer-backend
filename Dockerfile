FROM openjdk:8-jre-slim

RUN addgroup jvm && \
    adduser --system --disabled-login --shell /bin/false --home=/app jvm && \
    adduser jvm jvm && chown jvm:jvm -R /app

WORKDIR /app

COPY ./target/universal/stage ./

RUN chmod 755 -R /app/bin

USER jvm

EXPOSE 9000

ENTRYPOINT [ "bin/blockchainexplorer", "-Dpidfile.path=/dev/null", "-Dfile.encoding=UTF8" ]
