package com.example.demo.helper;


import java.util.List;

public class Response {
    private Boolean isSuccessful;
    private String description;
    private Object object;
    private List<String> roleList;

    public Response() {
    }

    public Response(Boolean isSuccessful, String description, Object object) {
        this.isSuccessful = isSuccessful;
        this.description = description;
        this.object = object;
    }

    public Response(Boolean isSuccessful, String description, Object object, List<String> roleList) {
        this.isSuccessful = isSuccessful;
        this.description = description;
        this.object = object;
        this.roleList = roleList;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setIsSuccessful(Boolean isSuccessful) {
        this.isSuccessful = isSuccessful;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
}
