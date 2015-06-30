package org.n52.sos.statistics.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.ImmutableSettings.Builder;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.n52.sos.statistics.api.utils.dto.KibanaConfigEntryDto;
import org.n52.sos.statistics.api.utils.dto.KibanaConfigHolderDto;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class KibanaExporter {

    public static void main(String args[]) throws JsonGenerationException, JsonMappingException, FileNotFoundException, IOException {
        if (args.length != 2) {
            System.out.println(String.format("Usage: java KibanaExporter.jar %s %s", "localhost:9300", "my-cluster-name"));
            System.exit(0);
        }
        if (!args[0].contains(":")) {
            throw new IllegalArgumentException(String.format("%s not a valid format. Expected <hostname>:<port>.", args[1]));
        }

        // set ES address
        String split[] = args[0].split(":");
        InetSocketTransportAddress address = new InetSocketTransportAddress(split[0], Integer.valueOf(split[1]));

        // set cluster name
        Builder tcSettings = ImmutableSettings.settingsBuilder();
        tcSettings.put("cluster.name", args[1]);
        System.out.println("Connection to " + args[1]);
        TransportClient client = new TransportClient(tcSettings).addTransportAddress(address);

        KibanaConfigHolderDto holder = new KibanaConfigHolderDto();
        System.out.println("Reading .kibana index");
        SearchResponse resp = client.prepareSearch(".kibana").setSize(1000).get();
        Arrays.asList(resp.getHits().getHits()).stream().forEach(l -> {
            holder.add(new KibanaConfigEntryDto(l.getIndex(), l.getType(), l.getId(), l.getSourceAsString()));
        });
        System.out.println("Reading finished");

        ObjectMapper mapper = new ObjectMapper();
        // we love pretty things
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File f = new File("kibana_config.json");
        if (f.exists()) {
            System.out.println(f.getAbsolutePath() + "exists it will be deleted.");
            f.delete();
        }
        mapper.writeValue(new FileOutputStream(f), holder);
        System.out.println("File outputted to: " + f.getAbsolutePath());
    }
}
