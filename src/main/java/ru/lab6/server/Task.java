package ru.lab6.server;

import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.command.Command;

import java.util.concurrent.ForkJoinTask;

public class Task extends ForkJoinTask<Response> {
    private Command command;
    private Parameters parameters;

    public Task(Command command, Parameters parameters){
        this.command = command;
        this.parameters = parameters;
    }

    @Override
    public Response getRawResult() {
        return command.execute(parameters);
    }

    @Override
    protected void setRawResult(Response value) {

    }

    @Override
    protected boolean exec() {
        return false;
    }
}