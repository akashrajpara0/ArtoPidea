package com.artopidea.elisioninfotech.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromptsModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("data")
    @Expose
    private List<PromptsData> data = null;

    public PromptsModel() {
    }

    public PromptsModel(Integer status, List<PromptsData> data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PromptsData> getData() {
        return data;
    }

    public void setData(List<PromptsData> data) {
        this.data = data;
    }
}
