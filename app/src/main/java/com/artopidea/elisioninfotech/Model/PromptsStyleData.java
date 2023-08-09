package com.artopidea.elisioninfotech.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PromptsStyleData {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("prompt_text")
    @Expose
    String prompt_text;

    @SerializedName("style_img")
    @Expose
    String style_img;

    @SerializedName("created_at")
    @Expose
    String created_at;

    @SerializedName("updated_at")
    @Expose
    String updated_at;

    public PromptsStyleData() {
    }

    public PromptsStyleData(String id, String name, String prompt_text, String style_img, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.prompt_text = prompt_text;
        this.style_img = style_img;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrompt_text() {
        return prompt_text;
    }

    public void setPrompt_text(String prompt_text) {
        this.prompt_text = prompt_text;
    }

    public String getStyle_img() {
        return style_img;
    }

    public void setStyle_img(String style_img) {
        this.style_img = style_img;
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
