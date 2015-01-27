package com.njanor.deploypipeline.testdatabuilders.endringsynskje;

import com.njanor.deploypipeline.endringsynskje.EndringsynskjeRegistrertEvent;
import com.njanor.deploypipeline.testdatabuilders.TestDataBuilder;

public class EndringsynskjeRegistrertEventBuilder extends TestDataBuilder<EndringsynskjeRegistrertEvent> {

    private String namn;

    public EndringsynskjeRegistrertEventBuilder() {
        namn = "HIB-152";
    }

    @Override
    public EndringsynskjeRegistrertEvent build() {
        return new EndringsynskjeRegistrertEvent(namn);
    }

    public EndringsynskjeRegistrertEventBuilder medNamn(String namn) {
        this.namn = namn;
        return this;
    }
}
