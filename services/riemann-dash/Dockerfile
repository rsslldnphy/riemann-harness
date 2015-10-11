FROM ruby:2.2.3

RUN mkdir /app/
RUN mkdir /app/config
WORKDIR   /app/

RUN gem install riemann-dash thin
ADD dev.rb /app/

CMD ["riemann-dash", "/app/dev.rb"]
