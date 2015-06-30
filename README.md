# Kibana-exporter
Kibana-esporter is for extracting the kibana specific settings from elasticsearch into a json file.

#Usage
Run `mvn clean package`.
In the target directory run the kibana-exporter-jar-with-dependencies.jar with
`java -jar kibana-exporter-jar-with-dependencies.jar <param1> <param2>`

The input parameters
- param1: is the address of your Elasticsearch cluster e.g: localhost:9300
- param2: is the cluster name of your Elasticsearch cluster.

The output will be generated in the calling directory `kibana_config.json` which contains the .kibana index sources.
