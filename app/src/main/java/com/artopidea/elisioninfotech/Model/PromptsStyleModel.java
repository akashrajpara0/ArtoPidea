package com.artopidea.elisioninfotech.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PromptsStyleModel {

    @SerializedName("status")
    @Expose
    private Integer status;

    @SerializedName("data")
    @Expose
    private List<PromptsStyleData> data = null;

    public PromptsStyleModel() {
    }

    public PromptsStyleModel(Integer status, List<PromptsStyleData> data) {
        this.status = status;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<PromptsStyleData> getData() {
        return data;
    }

    public void setData(List<PromptsStyleData> data) {
        this.data = data;
    }
}
