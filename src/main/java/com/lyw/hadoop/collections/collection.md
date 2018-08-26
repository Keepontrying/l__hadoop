# 集合 Collection、Map
## Collection是set、list、queue的父接口
  - set：是不含重复元素的集合，可以包含null
      - HashSet：无序的、不含重复元素的集合，是通过HashMap实现去重
        ```
            public boolean add(E e) {
                return map.put(e, PRESENT)==null;
            }
        ```
      - LinkedHashSet
        
      - EnumSet



## Map是键值对集合
![Map类图](map类图.png)
  - HashMap:单向链表数组实现的key不重复的键值对集合。key哈希值是数组索引，然后单向链表存储对象。
  如果hash冲突的话，找到索引出的链表，然后放入链表中。根据链表中key值操作链表元素。
  非基本类型类，需要重写key对象的equals和hashCode方法
    - 泛型，传入的是参数化类型（编译器泛型有效，运行时是同一个类型）
        - 泛型类运行原理：编译器强制校验类型，编译为class文件的时候，会擦出类型，替换为Object
        并强制转换类型。所以运行时，并不知道泛型类的实际类型参数。
        - 方法返回类型前加<T> 表示该方法是接受的参数是泛型，而不是类传入的类型参数。
        - FunctionalInterface：函数接口。只有一个抽象方法。（继承Object公开的方法【native实现】，后者default方法都是有默认实现的，不算抽象方法）
        
    - HashMap动态扩展数组长度 ：判断数组和原始数组容量大小，threshold << 1 计算新的数组容量newThr并创建new Node[newThr]   
  - ConcurrentHashMap :   线程安全的HashMap。通过同步代码块 put，remove操作保证线程安全。
  - LinkedHashMap : 有插入顺序的HashMap。将书友Entity节点链入一个双向链表的HashMap。
        增加了head、tail Entity节点。
  - TreeMap :红黑树实现的有序HashMap。