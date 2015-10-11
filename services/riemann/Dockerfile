FROM java

ENV RIEMANN_VERSION 0.2.10
ADD riemann-${RIEMANN_VERSION}.tar.bz2 /
RUN mv riemann-${RIEMANN_VERSION} /app

WORKDIR /app

EXPOSE 5555/tcp 5555/udp 5556

CMD [ "bin/riemann", "etc/riemann.config" ]
