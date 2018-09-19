package com.lyw.hadoop.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * 类描述：2-3树
 *
 * @author liangyuwu
 * @Time 2018/9/19 15:23
 */
public class Two_3Tree<K,V> {

    static Logger logger = LoggerFactory.getLogger(Two_3Tree.class);

    private Node<K,V> root;

    public Two_3Tree(){
        root = new ThreeNode<>();
    }

    public Node<K, V> put(K key, V value) {
        Entry<K, V> entry = new Entry<K, V>(key, value);
        for(Node<K,V> head = root;;){
            if (head.size() < 2) {
                head.get3Item().add(entry);
            } else if (head.size() == 2) {
                Set<Entry<K,V>> items = head.get3Item();
                Node<K, V> node = new TwoNode<K, V>(key, value);
//                items.stream().
            }
            break;
        }

        return null;
    }



    static class ThreeNode<K,V> implements Node{
        public ThreeNode<K,V> parent,left,right,middle;
        public Set<Entry<K, V>> item = new HashSet();

        public ThreeNode(){
            super();
        }

        public ThreeNode(K key, V value) {

        }

        public ThreeNode(ThreeNode<K,V> parent, ThreeNode<K,V> left,
                         ThreeNode<K,V> right, ThreeNode<K,V> middle,
                         K key, V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.middle = middle;
            if (item.size() < 3) {
                this.item.add(new Entry<K, V>(key, value));
            }else{
                logger.error("已经是3-node,需要分裂平衡");
                //Todo
            }
        }

        public ThreeNode<K, V> getParent() {
            return parent;
        }

        public void setParent(ThreeNode<K, V> parent) {
            this.parent = parent;
        }

        public ThreeNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(ThreeNode<K, V> left) {
            this.left = left;
        }

        public ThreeNode<K, V> getRight() {
            return right;
        }

        public void setRight(ThreeNode<K, V> right) {
            this.right = right;
        }

        public ThreeNode<K, V> getMiddle() {
            return middle;
        }

        public void setMiddle(ThreeNode<K, V> middle) {
            this.middle = middle;
        }

        public Set<Entry<K, V>> getItem() {
            return item;
        }

        @Override
        public Set<Entry<K,V>> get3Item() {
            return this.item;
        }

        @Override
        public long size() {
            return item.size();
        }

        public void setItem(Set<Entry<K, V>> item) {
            this.item = item;
        }
    }

    static class TwoNode<K,V> implements Node{
        private TwoNode<K,V> parent,left,right;
        private Entry<K,V> item;
        private NodeTypeEnum nodeType;

        public TwoNode(){
            super();
        }

        public TwoNode(K key, V value) {
            this.item = new Entry<K, V>(key, value);
        }

        public TwoNode(TwoNode<K,V> parent, TwoNode<K,V> left,
                       TwoNode<K,V> right, K key,V value) {
            this.parent = parent;
            this.left = left;
            this.right = right;
            this.item = new Entry<K, V>(key, value);
        }

        public TwoNode<K, V> getParent() {
            return parent;
        }

        public void setParent(TwoNode<K, V> parent) {
            this.parent = parent;
        }

        public TwoNode<K, V> getLeft() {
            return left;
        }

        public void setLeft(TwoNode<K, V> left) {
            this.left = left;
        }

        public TwoNode<K, V> getRight() {
            return right;
        }

        public void setRight(TwoNode<K, V> right) {
            this.right = right;
        }

        public Entry<K, V> getItem() {
            return item;
        }

        public void setItem(Entry<K, V> item) {
            this.item = item;
        }

        public NodeTypeEnum getNodeType() {
            return nodeType;
        }

        public void setNodeType(NodeTypeEnum nodeType) {
            this.nodeType = nodeType;
        }

        @Override
        public Set<Entry> get3Item() {
            return null;
        }

        @Override
        public long size() {
            return 0;
        }
    }

    static class Entry<K,V>{
        private K key;
        private V value;

        private Entry(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    public interface Node<K,V>{
        public Set<Entry<K, V>> get3Item();

        public long size();
    }

    public enum  NodeTypeEnum{
        Three("3-node"),Two("2-node");
        private String desc;
        private NodeTypeEnum(String desc) {
            this.desc = desc;
        }
    }
}
