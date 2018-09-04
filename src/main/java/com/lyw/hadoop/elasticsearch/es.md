# elasticsearch(es) 操作命令

- es集群命令
    ```html
      GET /_cat/health?v   //查看集群的健康情况
    
      GET /_cat/nodes?v    //查看集群所有的节点
    
      GET /_cat/indices?v  //查看集群所有的index
    ```
- 操作index
    ```html
      //创建一个indexName=customer的index，pretty是尽可能直观的展示返回的json数据
     
      PUT /customer?pretty
    
      DELETE /customer?pretty   //删除index
       
    ```

- 添加、查询、更新、删除document
    - 添加<font color=red> PUT需要指定_id,POST不需要指定索引，会生成随机的串当索引_id</font>
        ```
        //向customer这个index里插入type=student、filed=name、value=liangyuwu、_id为1的一条document
        
        PUT /customer/student/1?pretty
        {
           "name" : "liangyuwu_put"
        }
        
        
        POST /customer/student?pretty
        {
            "name" : "liangyuwu_post"
        }
        ```
        and the response
        ```html
            {
              "_index" : "customer",
              "_type" : "student",
              "_id" : "1",
              "_version" : 1,
              "result" : "created",
              "_shards" : {
                "total" : 2,
                "successful" : 1,
                "failed" : 0
              },
              "_seq_no" : 0,
              "_primary_term" : 1
            } 
        ```
    - 查询GET
        ```html
          GET /customer/student/1?pretty
        ```
        and the response
        ```html
           {
             "_index" : "customer",
             "_type" : "student",
             "_id" : "1",
             "_version" : 1,
             "found" : true,
             "_source" : { "name": "liangyuwu_put" }
           }    
        ```
    - 删除
       ```html
          DELETE /customer/student/1?pretty
        ```
    - 更新数据document
        - 简单修改
            ```html
              //put命令替换
              PUT /customer/student/1?pretty
              {
                  "name" : "liangyuwu_put_replace"
              }
        
              GET /customer/student/1?pretty
            ```
            and the response
            ```html
               {
                 "_index" : "customer",
                 "_type" : "student",
                 "_id" : "1", //id不变
                 "_version" : 2, //版本增加
                 "found" : true,
                 "_source" : { "name": "liangyuwu_put_replace" }
               }    
            ```
        - 增加字段<font color=red> doc是关键字，表示document</font>
            ```html
               POST /customer/student/1/_update?pretty
               {
                  "doc" :{"age" : 18}   
               }
              GET /customer/student/1?pretty
            ```
            and the response
            ```html
               {
                 "_index" : "customer",
                 "_type" : "student",
                 "_id" : "1", //id不变
                 "_version" : 3, //版本增加
                 "found" : true,
                 "_source" : { "name": "liangyuwu_put_replace" ,"age":18}
               }    
            ```
        - <font color=red>POST与PUT区别： 如果指定document的ID，没有什么区别。如果Post没有指定id，会重新生成_id,插入一条新数据</font>
        - script 更新指定的document数据
        ```html
          POST /customer/student/1/_update?pretty
          {
              "script" : "ctx._source.age += 10"
          }
    
          //ctx._source 指当前要更新的document（即：_id=1）的数据
        ```
        - <font color=blue>批量更新</font>

- 批量操作（_bulk Api块操作）
    ```html
      action_and_meta_data\n
      optional_source\n
      action_and_meta_data\n
      optional_source\n
      ...
      action_and_meta_data\n
      optional_source\n
    ```
    eg :
    ```html
      //批量插入
      POST /customer/student/_bulk?pretty
      {"index" : {"_id":"3"}}
      {"name" : "lyw"}
      {"index" : {"_id":"4"}}
      {"age" : "29"}
    
      //批量更新
      POST /customer/student/_bulk?pretty
      {"update" : {"_id" : "1"}}
      {"doc":{"name" : "lyw","age":"25"}}
      {"delete" : {"_id" : "2"}}
    ```

- es 搜索api(以_search结尾restful)
    - restful url后面拼接search参数
    ```html
      GET /customer/_search?q=*&sort=age:asc&pretty
      
      // q=* customer index里面所有的documents
      // sort=age:asc  以字段age升序排序
    ```
    - <font color=blue>rest 请求主体(效果等同上面)</font>
    ```html
      GET /customer/_search
      {
          "query" :{"match_all":{}}
          "sort" :[{"age":"asc"}]
      }
    ```