package br.com.fiap.resource;

import br.com.fiap.bo.UnidadeComunidaeBO;
import br.com.fiap.to.UnidadeComunidadeTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/unidade")
public class UnidadeComunidadeResource {

    private UnidadeComunidaeBO unidadeComunidaeBO = new UnidadeComunidaeBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<UnidadeComunidadeTO> resultado = unidadeComunidaeBO.findAll();

        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); // 200 (ok)
        } else {
            response = Response.status(404); // 404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("codigo") Long idUnidade)   {

        UnidadeComunidadeTO resultado = unidadeComunidaeBO.findById(idUnidade);

        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.ok(); //200 (ok)
        } else {
            response = Response.status(404); //404 (NOT FOUND)
        }
        response.entity(resultado);
        return response.build();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(UnidadeComunidadeTO unidade) {
        UnidadeComunidadeTO resultado = unidadeComunidaeBO.save(unidade);

        Response.ResponseBuilder response = null;

        if (resultado != null) {
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(400); //400 - BAD REQUEST
        }
        response.entity(resultado);
        return  response.build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(UnidadeComunidadeTO unidade, @PathParam("codigo") Long idUnidade) {

        unidade.setIdUnidade(idUnidade);
        UnidadeComunidadeTO resultado = unidadeComunidaeBO.update(unidade);

        Response.ResponseBuilder response = null;

        if  (resultado != null) {
            response = Response.created(null); //201 - CREATED
        } else {
            response = Response.status(400); // 400 - BAD REQUEST
        }
        response.entity(resultado);
        return  response.build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long idUnidade) {
        Response.ResponseBuilder response = null;

        if (unidadeComunidaeBO.delete(idUnidade)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();
    }
}
