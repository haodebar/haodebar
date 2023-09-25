package com.chaoyue.haodebar.tool;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: xuzhichao8
 * @date: 2023/3/7 15:45
 * @version: version 1.0
 * @dec: 描述信息
 */
@Component
@Slf4j
public class ElasticSearchUtils {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     *
     * @param index
     * @throws IOException
     */
    public void creatIndex(String index) throws IOException {
        //创建索引请求
        CreateIndexRequest request = new CreateIndexRequest(index);
        //客户端执行请求IndicesClient，请求后获得相应
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

    }

    /**
     * 删除索引
     *
     * @throws IOException
     */
    public void deleteIndex(String index) throws IOException {
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);
        //执行删除索引的方法
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
        //查看是否删除成功
        System.out.println(delete.isAcknowledged());
    }

    /**
     * 增加
     *
     * @param document
     * @throws IOException
     */
    public void addDocument(String index,String document,String docId) throws IOException {
        //创建请求
        IndexRequest request = new IndexRequest(index);
        //设置这条文档的id为1
        request.id(docId);
        //将数据放入请求
        request.source(document, XContentType.JSON);
        //向客户端发送请求，获取相应的结果
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        //输出信息和状态
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());

    }

    public void getDocumentById(String index,String docId) throws IOException {
        //构造条件，这选择的是查询
        GetRequest test_index = new GetRequest(index, docId);
        GetResponse documentFields = restHighLevelClient.get(test_index, RequestOptions.DEFAULT);
        //打印文档的内容
        System.out.println(documentFields.getSourceAsString());
        //返回全部内容
        System.out.println(documentFields);
    }
}
