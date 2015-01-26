package com.njanor.deploypipeline.endringsynskje;

import no.ks.eventstore2.command.Command;

public class RegistrerEndringsynskjeCommand extends Command {

    private final String namn;

    public RegistrerEndringsynskjeCommand(String namn) {
        this.namn = namn;
    }

    public String getNamn() {
        return namn;
    }
}
