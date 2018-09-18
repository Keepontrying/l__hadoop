# KMP匹配算法
   - kmp算法证明</br>
      主串:S=s<sub>1</sub>s<sub>2</sub>...s<sub>i</sub>...s<sub>n</sub> 主串移动个数K 模式串:P=p<sub>1</sub>p<sub>2</sub>...p<sub>j</sub>...p<sub>n'</sub></br>
   - 条件: 主串与模式串匹配，当 s<sub>i</sub>&ne;p<sub>j</sub>,即存在 p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>s<sub>i-j+2</sub>...s<sub>i-1</sub></br>
   
       - 证明：<font color=red>because</font>: P和S不匹配</br>
            <font color=blue>So</font>: S右移动K=1, s<sub>i-j+2</sub>和P的p<sub>1</sub>开始匹配。</br>
            假设: 匹配成功&rArr; p<sub>1</sub>p<sub>2</sub>...p<sub>j-2</sub> &equiv; s<sub>i-j+2</sub>s<sub>i-j+3</sub>...s<sub>i-1</sub></br>
            <font color=red>because</font>: p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>...s<sub>i-1</sub>
            <font color=blue>&rArr;</font> p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+2</sub>...s<sub>i-1</sub></br>
            <font color=blue>So</font>: 即证明 p<sub>2</sub>...p<sub>j-1</sub> &equiv; p<sub>1</sub>p<sub>2</sub>...p<sub>j-2</sub></br>
            
            <font color=gree>If</font>: S[i-j+2,i-1]&ne;P[1,j-2]不匹配，将S向右移动K=2</br>
            假设: 匹配成功&rArr; p<sub>1</sub>p<sub>2</sub>...p<sub>j-3</sub> &equiv; s<sub>i-j+3</sub>s<sub>i-j+4</sub>...s<sub>i-1</sub></br>
            <font color=red>because</font>: p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>...s<sub>i-1</sub>
            <font color=blue>&rArr;</font> p<sub>3</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+3</sub>...s<sub>i-1</sub></br>
            <font color=blue>So</font>: 即证明 p<sub>3</sub>...p<sub>j-1</sub> &equiv; p<sub>1</sub>p<sub>2</sub>...p<sub>j-3</sub></br>
            ...</br>
            <font color=gree>If</font>: S[i-j+k-1,i-1]&ne;P[1,j-k+1]不匹配，将S向右移动K=k</br>
            假设: 匹配成功&rArr; p<sub>1</sub>p<sub>2</sub>...p<sub>j-k</sub> &equiv; s<sub>i-j+k</sub>...s<sub>i-1</sub></br>
            <font color=red>because</font>: p<sub>1</sub>p<sub>2</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+1</sub>...s<sub>i-1</sub>
            <font color=blue>&rArr;</font> p<sub>k</sub>...p<sub>j-1</sub> &equiv; s<sub>i-j+k</sub>...s<sub>i-1</sub></br>
            <font color=blue>So</font>: 即证明 p<sub>k</sub>...p<sub>j-1</sub> &equiv; p<sub>1</sub>p<sub>2</sub>...p<sub>j-k</sub></br>

            综上所述： p<sub>1</sub>p<sub>2</sub>...p<sub>j-k</sub> &equiv; p<sub>k</sub>...p<sub>j-1</sub></br>
            
            左边是j-k个元素，右边是j-1-k+1=j-k个元素</br>
            令p-1 = j-k个元素</br>
            
            即证明 ： p<sub>1</sub>p<sub>2</sub>...p<sub>p-1</sub> &equiv; p<sub>j-p+1</sub>...p<sub>j-1</sub></br>    
            <font color=blue>&rArr;</font> 可以证明 只要能找到模式串中满足上面条件的p,就可以确定主串需要移动的位数K=j-p+1
            又因为前p-1个 p<sub>1</sub>p<sub>2</sub>...p<sub>p-1</sub> &equiv; s<sub>i-j+k</sub>...s<sub>i-1</sub></br>