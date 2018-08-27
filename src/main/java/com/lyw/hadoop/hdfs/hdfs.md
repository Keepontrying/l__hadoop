# HDFS 分布式文件系统
    HDFS集群有2类节点管理节点（namenode）、工作者节点（datanode)
- namenode 管理文件系统的命名空间，维护这整个文件系统树及整个树内的所有文件和目录。是hadoop的管理者
是hdfs正常工作的入口。namenode主要的文件是fsimage文件系统映像（元数据信息）和编辑日志edit。会在namenode节点
内存中保留所有块位置的映射。
    - 工作原理：
- datanode 是工作节点。实际存储hdfs数据块的数据。维护这数据块的位置信息。
   - datanode中的文件类型：HDFS块文件（只包含原始数据），
                    数据块的元数据文件，包含版本、类型信息和一系列的校验和
   - datanode数据块数据可以通过设置块数据副本数量，在其他的datanode上面维护着该数块的原始数据副本，提供系统容灾
   
   
   
   