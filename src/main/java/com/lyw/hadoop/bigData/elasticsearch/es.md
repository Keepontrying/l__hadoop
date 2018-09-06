# elasticsearch(es) 操作命令

- PUT命令 ： 可以创建索引，可以index数据（必须有_id）
- POST命令： 
    1. 无法直接创建index,但是可以通过添加document，创建type类型 来创建index
    2. 添加document可以不指定_id，系统自动生成字符串作为_id的值
    3. 以_update结尾，配合doc或者script关键字，可以实现修改指定_id的数据结构、数据值
- GET命令：  
    1. 可以查看index的信息，可以查看集群、节点、分配的信息  
    2. 如何查看document数据信息，后面必须跟_id信息，
    3. 以_search结尾，可以查出所有数据 eg: GET /index/_search 或 /index/type/_search
    4. 不支持 GET /index/type/  查询数据
- DELETE命令： 
    1. DELETE /index/  删除是整个index
    2. DELETE /index/type/_id  删除的是指定id的document
    3. 不能删除 /index/type/


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
              //put命令替换,返回的是更新操作
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
        - 增加字段<font color=red> doc是关键字，表示document。是可以指定更新字段值或者新增字段。如果不用doc关键字
            无法新增字段</font>
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
    
      GET /customer/_search
      {
         "query" :{"match"://空格默认是或的关系，匹配其中之一，如果匹配的是整体match_phrase
                      {"name":"lyw liangyuwu"}
                  }
         "sort" :[{"age":"asc"}]
      }
    
      GET /customer/_search
      {
          "query" :{
              "bool" :{//查询池
                  "must" :[// &的关系  同时匹配
                      {"match" :{"name" : "lyw"}},
                      {"match" :{"name" : "liangyuwu"}}
                  ],
                  "should":[// |的关系 匹配其中一个
                      {"match" :{"name" : "lyw"}},
                      {"match" :{"name" : "liangyuwu"}}
                  ],
                  "must_not":[// ^的关系 都不匹配
                  ]
              }        
          }
      }
    ```
    - es Filters过滤器
    ```html
      GET /customer/_search
      {
          "query" : {
              "bool":{
                  "must":{"match_all":{}},
                  "filter":{
                      "range":{
                          "age":{
                              "gte": 25,
                              "lte": 30
                          }
                      }
                  }
              }
          }
      }
    ```
    - es 聚合查询