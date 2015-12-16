DOCKER_IP  = $$(docker-machine ip default)
NREPL_PORT = 4001

docker-clean:
	docker ps -a | grep riemannharness | awk '{print $$1}' | xargs docker stop; \
	docker ps -a | grep riemannharness | awk '{print $$1}' | xargs docker rm

run: docker-clean
	docker-compose up

repl:
	lein repl :connect $(DOCKER_IP):$(NREPL_PORT)

dash:
	bin/dash && open http://$(DOCKER_IP):4567

reload:
	docker kill -s SIGHUP $$(docker ps -aq -f name=riemannharness_riemann)
