FROM ruby:2.2.3

RUN mkdir /code/
RUN mkdir /code/config
WORKDIR   /code/

RUN gem install riemann-dash thin
ADD dev.rb /code/

CMD ["riemann-dash", "/code/dev.rb"]
