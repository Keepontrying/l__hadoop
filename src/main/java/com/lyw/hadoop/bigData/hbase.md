# hbase
   - 实时、分布式存储面向列的数据存储服务器。
        - 实时：hbase所有的查询都是用rowkey检索，通过LRU(Least recently used)算法找到要检索分区
        先从