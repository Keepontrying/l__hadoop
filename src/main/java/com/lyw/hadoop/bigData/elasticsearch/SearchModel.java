package com.lyw.hadoop.bigData.elasticsearch;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


/**
 * 类描述：es字段
 *
 * @author liangyuwu
 * @Time 2018/9/7 14:12
 */
public class SearchModel implements Serializable {
    private static final long serialVersionUID = -2378937145382567585L;

    private Integer id;
    private String name;
    private String address;
    private List<Node> nods;
    private String indexName;

    class Node{
        private String nodeId;
        private String nodeName;
        private double amount;
        private LocalDate subDate;
        private LocalTime subTime;

        public Node(){
            super();
        }

        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(String nodeId) {
            this.nodeId = nodeId;
        }

        public String getNodeName() {
            return nodeName;
        }

        public void setNodeName(String nodeName) {
            this.nodeName = nodeName;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public LocalDate getSubDate() {
            return subDate;
        }

        public void setSubDate(LocalDate subDate) {
            this.subDate = subDate;
        }

        public LocalTime getSubTime() {
            return subTime;
        }

        public void setSubTime(LocalTime subTime) {
            this.subTime = subTime;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Node> getNods() {
        return nods;
    }

    public void setNods(List<Node> nods) {
        this.nods = nods;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
}
