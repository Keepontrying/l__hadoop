/*
package com.lyw.hadoop.bigData.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.math.NumberUtils;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsAction;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.Numbers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.common.xcontent.json.JsonXContentParser;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Stream;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

*/
/**
 * 类描述：
 *
 * @author liangyuwu
 * @Time 2018/9/6 17:29
 *//*

public class ElasticSearchTest {

    static final Logger log = LoggerFactory.getLogger(ElasticSearchTest.class);
    static ObjectMapper objectMapper = new ObjectMapper();

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

    public boolean createIndices(String... indexName) throws Exception {
        boolean exist = indicesExist(indexName);
        if (exist) {//index已经存在
            return false;
        }

        Settings settings = setIndexProperties(indexName);

        CreateIndexResponse indexResponse = getClient().admin()
                .indices().prepareCreate(indexName[0])
                .setSettings(Settings.builder()
                    .put("index.number_of_shards",3)
                    .put("index.number_of_replicas", 2)
                )
                .execute().actionGet();
        if (Optional.ofNullable(indexResponse).isPresent()) {//如果有数据，不为空
            log.info("index创建成功：" + objectMapper.writeValueAsString(indexResponse));
            return true;
        }
        return false;
    }

    */
/**
     * 封装index的settings
     * @param indexName
     * @return
     *//*

    private Settings setIndexProperties(String... indexName) throws Exception{
        Settings.Builder builder = Settings.builder()
                .put("index.number_of_shards",3)
                .put("index.number_of_replicas", 2);

        return null;
    }

    */
/**
     * 校验索引是否存在
     * @param indicesName
     * @return boolean
     *//*

    public boolean indicesExist(String... indicesName) throws Exception{
        //#method -1
        IndicesExistsRequest existsRequest = new IndicesExistsRequest(indicesName);
        IndicesExistsResponse existsResponse = getClient().admin().indices()
                .exists(existsRequest).actionGet();
        //#method -2
        IndicesExistsAction existsAction = IndicesExistsAction.INSTANCE;
        IndicesExistsRequestBuilder builder = existsAction.newRequestBuilder(getClient()).setIndices(indicesName);
        existsResponse = builder.get();

        if (Optional.ofNullable(existsResponse).isPresent() && existsResponse.isExists()) {
            log.error("index已经存在="+objectMapper.writeValueAsString(existsResponse));
            return false;
        }
        return existsResponse.isExists();
    }

    public void insertDocument(SearchModel model) throws Exception {
        IndexResponse response = getClient().prepareIndex()
                .setIndex(model.getIndexName())
                .setType(model.getIndexName())
                .setSource(objectMapper.writeValueAsString(model), XContentType.JSON)
                .get();
        log.info("添加公司信息："+response.toString());
    }

    public List<SearchModel> queryData(SearchModel model) throws Exception{
        List<SearchModel> searchModels = new ArrayList<>();

        //查询条件
        QueryBuilder idsQuery = QueryBuilders.idsQuery();
        QueryBuilder termQuery = QueryBuilders.termQuery("address", "new_address");

        SearchResponse response = getClient().prepareSearch().setIndices(model.getIndexName())
                .setTypes(model.getIndexName())
//                .setQuery(idsQuery)
                .setQuery(termQuery)
                .get();
        if (Optional.ofNullable(response).isPresent()) {
            log.info("es查询结果:" + objectMapper.writeValueAsString(response));
            if (response.getHits().getTotalHits() != 0) {
                SearchHit[] hits = response.getHits().getHits();
                log.info("带封装数据："+objectMapper.writeValueAsString(hits));
                for (SearchHit hit : hits) {
                    Map<String,Object> dataStr = hit.getSourceAsMap();
                    SearchModel search = new SearchModel();
                    dataStr.forEach( (k,v) -> {
                        if (k.equals("id")) {
                            search.setId(Integer.valueOf(String.valueOf(v)));
                        } else if (k.equals("name")) {
                            search.setName((String) v);
                        } else if (k.equals("indexName")) {
                            search.setIndexName((String) v);
                        } else if (k.equals("address")) {
                            search.setAddress((String) v);
                        } else if (k.equals("nods")) {
                            List<Map<String,Object>> d = (List<Map<String, Object>>) v;
                            List<SearchModel.Node> nodes = new ArrayList<>();
                            d.forEach(n -> {
                                SearchModel.Node node = search.new Node();
                                nodes.add(node);
                            });
                            search.setNods(nodes);
                        }
                    });
                    searchModels.add(search);
                }
            }
        }
        return searchModels;
    }

    public SearchModel packageData() {
        SearchModel model = new SearchModel();
        model.setId(20);
        model.setName("良乐购公司");
        model.setAddress("河南郑州");
        model.setIndexName("index_test");
        model.setNods(new ArrayList<SearchModel.Node>());

        for (int i = 0; i < 3; i++) {
            SearchModel.Node node = model.new Node();
            node.setAmount(100 + i);
            node.setNodeId(i+"");
            node.setNodeName("lyw_" + i);
            node.setSubDate(LocalDate.now());
            node.setSubTime(LocalTime.now());
            model.getNods().add(node);
        }

        return model;
    }


    public static void main2(String[] args) throws Exception {
        */
/*IndexResponse response = getClient().prepareIndex("customer", "student", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", "liangyuwu")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();
        QueryBuilder query = QueryBuilders.termQuery("name", "liangyuwu");

        SearchResponse searchResponse = getClient().prepareSearch("customer").setQuery(query).get();
        if (Optional.ofNullable(searchResponse.getHits()).isPresent()) {
            SearchHits hits = searchResponse.getHits();
            Stream<SearchHit> stream = Arrays.stream(hits.getHits());
            long count = stream.count();
            System.err.println("count = "+ count);
            List<String> ids = stream.map(x -> x.getId()).collect(Collectors.toList());
            System.err.println("ids = "+ ids);
            SearchHit hit = stream.max( (x,y) -> {
                float b = x.getScore() - y.getScore();
                return (int)b;
            }).get();


            String hitstr= objectMapper.writeValueAsString(hit);
            System.err.println("hit ="+ hitstr);

        }
*//*

        ElasticSearchTest elasticSearchTest = new ElasticSearchTest();
        SearchModel mode = elasticSearchTest.packageData();
        boolean ok = elasticSearchTest.createIndices(mode.getIndexName());
//        elasticSearchTest.insertDocument(mode);
//        elasticSearchTest.queryData(mode);
    }
}
*/
