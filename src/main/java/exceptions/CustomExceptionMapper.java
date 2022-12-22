package exceptions;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(Exception exception) {

        MessageError messageError = new MessageError();
        if (exception instanceof BusinessException){
            messageError.setMessageReturn(exception.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(messageError).build();
        }

        messageError.setMessageReturn(exception.getMessage());
        return Response.status(Response.Status.NOT_FOUND).entity(messageError).build();
    }
}
