package week4.project1.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MyPojo {

    @JsonProperty("k1")
    private String key1;
    @JsonProperty("k2")
    private List<String> key2;

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public List<String> getKey2() {
        return key2;
    }

    public void setKey2(List<String> key2) {
        this.key2 = key2;
    }
}
