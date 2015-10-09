DOCKER_IP  = $$(docker-machine ip dev)
NREPL_PORT = 4001

docker-clean:
	docker ps -a | grep riemannharness | awk '{print $$1}' | xargs docker rm

_run_trap:
	trap 'kill $$(jobs -p)' EXIT; sleep 60 && echo done || echo failed

_run:
	source .env && docker-compose up

run: docker-clean _run _run-trap

repl:
	lein repl :connect $(DOCKER_IP):$(NREPL_PORT)
