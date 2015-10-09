FROM clojure

RUN mkdir       /code/
WORKDIR         /code/

ADD project.clj /code/
RUN lein deps

ADD src         /code/src
ADD test        /code/test

CMD lein repl :headless :host 0.0.0.0 :port 4001
