package com.artopidea.elisioninfotech.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromptsData {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("prompt_text")
    @Expose
    String prompt_text;

    @SerializedName("created_at")
    @Expose
    String created_at;

    @SerializedName("updated_at")
    @Expose
    String updated_at;

    public PromptsData() {
    }

    public PromptsData(String id, String prompt_text, String created_at, String updated_at) {
        this.id = id;
        this.prompt_text = prompt_text;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrompt_text() {
        return prompt_text;
    }

    public void setPrompt_text(String prompt_text) {
        this.prompt_text = prompt_text;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
