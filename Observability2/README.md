# Observability

1) First start the vault with below command:

COMPOSE_PROFILES=vault docker compose -f grafana-prometheus-loki-springBoot.yaml up

2) To start other services (except spring/eliteclub):
   COMPOSE_PROFILES=prod2 docker compose -f grafana-prometheus-loki-springBoot.yaml up
   
3) For running the spring application independently: 
under Spring/eliteclub run below:
  VAULT_HOST=192.168.64.7 VAULT_PORT=8200 OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4318 OTEL_EXPORTER_OTLP_INSECURE=true OTEL_EXPORTER_OTLP_PROTOCOL=http/protobuf OTEL_TRACES_EXPORTER=otlp OTEL_RESOURCE_ATTRIBUTES=SERVICE.NAME=ELITECLUB OTEL_METRICS_EXPORTER=none OTEL_LOGGING_EXPORTER=none OTEL_JAVAAGENT_DEBUG=true java -javaagent:./opentelemetry-javaagent.jar -jar target/eliteclub-0.0.1-SNAPSHOT.jar
  
  
  
4) To start all services (excluding vault) along with spring/eliteclub:
   COMPOSE_PROFILES=prod docker compose -f grafana-prometheus-loki-springBoot.yaml up
   


5) To create vault secret: Login to the vault container and run below: 
a) vault kv put -mount=cubbyhole my-secret password=password2
b) check vault status with: vault status
c) vault ui is accessible from http://host:8200


UIs are accessible at :
a) jaegar-ui (or tempo-query) : http://host:16686
b) prometheus: http://host:9090
c) loki: http://host:3100
d) grafana: http://host:3000
e) eliteclub: http://host:8080
