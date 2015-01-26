package com.njanor.deploypipeline.endringsynskje;

public class Endringsynskje {
    private String id;
    private String namn;

    public Endringsynskje(EndringsynskjeRegistrertEvent endringsynskjeRegistrert) {
        this.id = endringsynskjeRegistrert.getAggregateRootId();
        this.namn = endringsynskjeRegistrert.getNamn();
    }

    public String getId() {
        return id;
    }

    public String getNamn() {
        return namn;
    }
}
