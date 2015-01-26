package com.njanor.deploypipeline.testdatabuilders.endringsynskje;

import com.njanor.deploypipeline.endringsynskje.EndringsynskjeRegistrertEvent;
import com.njanor.deploypipeline.testdatabuilders.TestDataBuilder;

public class EndringsynskjeRegistrertEventBuilder extends TestDataBuilder<EndringsynskjeRegistrertEvent> {

    @Override
    public EndringsynskjeRegistrertEvent build() {
        return new EndringsynskjeRegistrertEvent("HIB-123");
    }
}
