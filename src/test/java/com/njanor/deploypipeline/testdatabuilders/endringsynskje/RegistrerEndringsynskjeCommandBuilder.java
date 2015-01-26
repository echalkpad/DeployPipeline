package com.njanor.deploypipeline.testdatabuilders.endringsynskje;

import com.njanor.deploypipeline.endringsynskje.RegistrerEndringsynskjeCommand;
import com.njanor.deploypipeline.testdatabuilders.TestDataBuilder;

public class RegistrerEndringsynskjeCommandBuilder extends TestDataBuilder<RegistrerEndringsynskjeCommand> {
    private String namn;

    public RegistrerEndringsynskjeCommandBuilder() {
        this.namn = "HIB-123";
    }

    @Override
    public RegistrerEndringsynskjeCommand build() {
        return new RegistrerEndringsynskjeCommand(namn);
    }
}
