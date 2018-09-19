# KMP匹配算法
   - kmp算法证明(主串移动K值=主串不用回溯指针)</br>
      主串:S=s<sub>1</sub>s<sub>2</sub>...s<sub>i</sub>...s<sub>n</sub> 主串移动个数K 模式串:P=p<sub>1</sub>p<sub>2</sub>...p<sub>j</sub>...p<sub>n'</sub></br>
   - 条件: 主串与模式串匹配，当 s<sub>i</sub>&ne;p<sub>j</sub>,即存在 p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>s<sub>i-j+2</sub>...s<sub>i-1</sub></br>
   
       - 归纳法证明：<font color=red>because</font>: P和S不匹配</br>
            <font color=blue>So</font>: S右移动K=1, s<sub>i-j+2</sub>和P的p<sub>1</sub>开始匹配。</br>
            假设: 匹配成功。 等价&rArr; p<sub>1</sub>p<sub>2</sub>...p<sub>j-2</sub> &equiv; s<sub>i-j+2</sub>s<sub>i-j+3</sub>...s<sub>i-1</sub></br>
            <font color=red>because</font>: p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>...s<sub>i-1</sub>
            <font color=blue>&rArr;</font> p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+2</sub>...s<sub>i-1</sub></br>
            <font color=blue>So</font>: 等价&rArr;证明 p<sub>2</sub>...p<sub>j-1</sub> &equiv; p<sub>1</sub>p<sub>2</sub>...p<sub>j-2</sub></br>
                                        如果不存在模式串不满足上面条件，S与P不匹配。即主串S移动K=1不匹配，需要右移K=2</br>
            
            <font color=gree>If</font>: S[i-j+2,i-1]&ne;P[1,j-2]不匹配，将S向右移动K=2</br>
            假设: 匹配成功&rArr; p<sub>1</sub>p<sub>2</sub>...p<sub>j-3</sub> &equiv; s<sub>i-j+3</sub>s<sub>i-j+4</sub>...s<sub>i-1</sub></br>
            <font color=red>because</font>: p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>...s<sub>i-1</sub>
            <font color=blue>&rArr;</font> p<sub>3</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+3</sub>...s<sub>i-1</sub></br>
            <font color=blue>So</font>: 等价&rArr;证明 p<sub>3</sub>...p<sub>j-1</sub> &equiv; p<sub>1</sub>p<sub>2</sub>...p<sub>j-3</sub></br>
                                        如果模式串不满足上述条件，S与P不匹配。需要将主串向右移动K=3</br>
            ...</br>
            <font color=gree>If</font>: S[i-j+k-1,i-1]&ne;P[1,j-k+1]不匹配，将S向右移动K=k</br>
            假设: 匹配成功。 等价&rArr; p<sub>1</sub>p<sub>2</sub>...p<sub>j-k</sub> &equiv; s<sub>i-j+k</sub>...s<sub>i-1</sub></br>
            <font color=red>because</font>: p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>...s<sub>i-1</sub>
            <font color=blue>&rArr;</font> p<sub>k</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+k</sub>...s<sub>i-1</sub></br>
            <font color=blue>So</font>: 等价&rArr;证明 p<sub>k</sub>...p<sub>j-1</sub> &equiv; p<sub>1</sub>p<sub>2</sub>...p<sub>j-k</sub></br>
                                              如果模式串不满足上述条件，S与P不匹配。需要将主串向右移动K=k+1</br>
            ...</br>
            综上所述： 主串S向右移动K,存在这样K=k,使得S[i-k+1,i-1] &equiv; P[1,k-1] 等价&rArr;<font color=red>命题（2） P[1,k-1] &equiv; P[j-k+1,j-1]</font></br>
                如果命题（2）不成立，则P、S不匹配。所以 找到P中满足命题（2）的k值，可以找到S的K值。又因为命题(2)成立。</br>
                模式串P命题（2）可以确定S的K=j-k值，又因为P满足的对称子串，所以S前k-1个元素已经匹配了。所以从S<sub>i</sub>匹配P<sub>k</sub></br>
            
            
   - 模式串P已匹配的串中找不到k满足命题（2）的话，S在[i-j+1,i-1]区间的回溯: S都不匹配P</br>
     - 证明：假设p不存在k满足命题（2），但S中存在K=k'满足 S匹配P，且k'<j</br>
        因为：存在k' =》 S[i-k'+1,i-1] &equiv; P[1,j-k'+1]  等价&rArr;证明  P[1,j-k'+1] &equiv; P[j-k',j-1]</br>
        所以：假设不成立。
        
# BM(boyer-moore)高效字符串检索算法
   - 坏字符原则：从模式串的最后一位开始匹配，主串和模式串不匹配的字符，称主串中的那个字符为 '<font color=green>坏字符</font>',不匹配时该'<font color=green>坏字符</font>'在模式串中的位置位k。
        1. 当<font color=green>坏字符</font>在模式串中不存在时，<font color=green>坏字符</font>在模式串中的位置位-1，<font color=red>索引从0开始</font></br>
        <font color=red>模式串向右移动距离公式：右移动距离= '<font color=green>坏字符</font>'不匹配时在模式串中的位置 - '<font color=green>坏字符</font>'出现在模式串最右边的位置</font></br>
        eg: 主串 S'=ABCSDBAEXO  模式串P'= ABCE
        ![](boyer-moore_1.png)</br>
        则 S'<sub>3</sub>=S 和P'<sub>3</sub>=E不匹配，称S是'<font color=green>坏字符</font>',S在模式串P'中不存在，所以最右边位置=-1</br>
        则：模式串P向右移动具体：l = 3-(-1)=4 => 从S'<sub>4</sub>=D和P'<sub>1</sub>=A对其，从模式串后面开始匹配</br>
        2. 当'<font color=green>坏字符</font>'在模式串中存在，则'<font color=green>坏字符</font>'出现在模式串最右边的位置位i</br>
        eg: 主串 S'=ABCSDBAEXO  模式串P'= ABCE
        ![](boyer-moore_2.png)</br>
        第二次匹配从S'<sub>7</sub>=E和模式串匹配，然后匹配前一个字符S'<sub>6</sub>=A,发现不匹配是'<font color=green>坏字符</font>'</br>
        A在模式串k=2，i=0; 所以模式串右移距离l=2-0=2 =>主串S'<sub>6</sub>=A和模式串P'<sub>0</sub>=A对其
   
   - 最好后缀原则：如上图（2）, 'E'即是最好后缀。最好后缀E在模式串中的位置是 3，E在模式串中上一次出现的位置是-1。所以模式串向右移动距离 l= 3-(-1)=4
     S'<sub>8</sub>和P'<sub>0</sub> 对齐。
     最好后缀移动距离公式 l= 最好后缀在模式串中的位置（后缀最后字符所在位置） - 最好后缀上传出现的位置（最右边第二次出现的位置）