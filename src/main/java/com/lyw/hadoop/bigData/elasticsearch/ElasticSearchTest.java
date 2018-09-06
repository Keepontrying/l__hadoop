package com.lyw.hadoop.bigData.elasticsearch;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Date;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/9/6 17:29
 */
public class ElasticSearchTest {

    public static TransportClient getClient() throws Exception {
        Settings settings = Settings.builder()
                .put("client.transport.sniff", false)
                .put("client.transport.ignore_cluster_name",false)
                .put("client.transport.nodes_sampler_interval", "10s")
                .put("cluster.name", "es-5.0-test").build();
        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

        return client;
    }


    public static void main(String[] args) throws Exception {
        IndexResponse response = getClient().prepareIndex("customer", "student", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "liangyuwu")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
        QueryBuilder query = QueryBuilders.termQuery("address", "new");

        SearchResponse searchResponse = getClient().prepareSearch("customer").setQuery(query).get();


        System.err.println(""+response.toString());
    }
}
