package resource;

import dto.BookDTO;
import enums.BookStatus;
import model.Book;
import service.BookService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    BookService service;

    @POST
    @Transactional
    public Response save(Book book){
        return Response
                .status(Response.Status.CREATED)
                .entity(service.save(book))
                .build();
    }

    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, BookDTO dto){
        return Response
                .status(Response.Status.CREATED)
                .entity(service.updateBook(id, dto))
                .build();
    }
    @GET
    public Response listAll(){
        return Response
                .status(Response.Status.OK)
                .entity(service.listAll())
                .build();
    }

    @GET
    @Path("/status")
    public Response findBookStatus(@QueryParam("statusBook") String statusBook){
        return Response
                .status(Response.Status.OK)
                .entity(service.findBookStatus(statusBook))
                .build();
    }

    @GET
    @Path("/{id}")
    public Response fetchBookByID(@PathParam("id") Long id){
        return Response
                .status(Response.Status.OK)
                .entity(service.fetchBookById(id))
                .build();
    }

    @GET
    @Path("/qty")
    public Response quantityOfBooks(){
        return Response
                .status(Response.Status.OK)
                .entity(service.quantityOfBooks())
                .build();
    }

    @GET
    @Path("/isbn")
    public Response fetchIsbn(@QueryParam("isbn") String isbn){
        return Response
                .status(Response.Status.OK)
                .entity(service.fetchBookByIsbn(isbn))
                .build();
    }

    @PUT
    @Path("/status/{id}/{status}")
    @Transactional
    public Response fetchBookStatus(@PathParam("id") Long id, @PathParam("status") String status){
        return Response
                .status(Response.Status.OK)
                .entity(service.changeStatus(id, status))
                .build();
    }

}