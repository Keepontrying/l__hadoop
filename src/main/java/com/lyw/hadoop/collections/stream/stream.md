# Stream 流式数据集合处理工具
    steam中间操作是layze，只有执行终态动作才会执行中间操作。
   ## stream构造
   - Collection、数组
        1. Collection.stream() List、Set、Queue
        2. Stream.of()  <===>  Arrays.stream()
        3. Collection.parallelStream()
        4. java.io.BufferedReader.lines()
   - 静态方法
        1. java.util.stream.IntStream.range()
        2. java.nio.file.Files.walk()
        3. java.util.Spliterator
   - 其他
        1. Random.ints()
        2. BitSet.stream()
        3. Pattern.splitAsStream(java.lang.CharSequence)
        4. JarFile.stream()
   ## steam的操作
   - 中间操作 intermediate
        1. map、flatMap、mapToInt</br>
            将stream转为其他形式的stream,flatMap是将子stream扁平化到一个大的stream
        2. filter</br>
           元素过滤，通过过滤的保留下来生成新stream
        3. distinct、sorted、peek、limit、skip、parallel、unordered</br>
           skip 则是扔掉前 n 个元素</br>
           peek 生成一个包含原Stream的所有元素的新Stream，新Stream每个元素被消费之前都会执行peek给定的消费函数
   - terminal 终点操作
        1. forEach、min、max、count、toArray、findFirst、iterator、forEachOrdered</br>
           forEachOrdered输出和输入的顺序保持一致
        2. collect、reduce</br>
           collect 将stream转为集合，combiner只有并行是在有效。get、accept方法</br>
           reduce 减少stream元素个数，通过BinaryOperator中的apply方法
        3. anyMatch、allMatch、noneMatch</br>
            anyMatch任何一个match，就OK</br>
            allMatch所有的</br>
            noneMatch 所有的都不符合
            