mvn spring-boot:build-image



java -javaagent:./opentelemetry-javaagent.jar -Dotel.traces.exporter=otlp -Dotel.exporter.otlp.endpoint=http://localhost:4318  -Dotel.resource.attributes=service.name=eliteclub -Dotel.javaagent.debug=true -Dotel.metrics.exporter=none -jar target/eliteclub-0.0.1-SNAPSHOT.jar


OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4318 OTEL_EXPORTER_OTLP_INSECURE=true OTEL_EXPORTER_OTLP_PROTOCOL=http/protobuf OTEL_TRACES_EXPORTER=otlp OTEL_RESOURCE_ATTRIBUTES=SERVICE.NAME=ELITECLUB OTEL_METRICS_EXPORTER=none OTEL_LOGGING_EXPORTER=none OTEL_JAVAAGENT_DEBUG=true java -javaagent:./opentelemetry-javaagent.jar -jar target/eliteclub-0.0.1-SNAPSHOT.jar 



OTEL_EXPORTER_OTLP_TRACES_ENDPOINT=http://localhost:4318 OTEL_EXPORTER_OTLP_INSECURE=true OTEL_EXPORTER_OTLP_PROTOCOL=http/protobuf OTEL_TRACES_EXPORTER=otlp OTEL_RESOURCE_ATTRIBUTES=SERVICE.NAME=ELITECLUB OTEL_METRICS_EXPORTER=none OTEL_LOGGING_EXPORTER=none OTEL_JAVAAGENT_DEBUG=true java -javaagent:./opentelemetry-javaagent.jar -jar target/eliteclub-0.0.1-SNAPSHOT.jar 
	
