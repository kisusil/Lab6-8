package ru.lab6.server.model.command;

import ru.lab6.common.parameters.IdParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.RepositoryException;

import java.awt.image.RescaleOp;

public class RemoveByIdCommand implements Command {
    private final ApplicationContext applicationContext;

    public RemoveByIdCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Response execute(Parameters parameters) {
        if (!(parameters instanceof IdParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        IdParameters idParameters = (IdParameters) parameters;
        try {
            applicationContext.getRepository().delete(idParameters.id);
            return new Response().setEmptyResult();
        }
        catch (RepositoryException e){
            return new Response().setErrorResponse("человека с таким id не существует", "");
        }
    }
}
