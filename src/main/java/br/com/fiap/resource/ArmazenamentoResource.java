package br.com.fiap.resource;

import br.com.fiap.bo.ArmazenamentoBO;
import br.com.fiap.to.ArmazenamentoTO;
import br.com.fiap.to.CadastroComunidadeTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/armazenamento")
public class ArmazenamentoResource {

    private ArmazenamentoBO armazenamentoBO = new ArmazenamentoBO();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<ArmazenamentoTO> resultado = armazenamentoBO.findAll();

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
    public Response findById(@PathParam("codigo") Long idArmazenamento) {

        ArmazenamentoTO resultado = armazenamentoBO.findById(idArmazenamento);

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
    public Response save(@Valid ArmazenamentoTO armazenamento) {
        ArmazenamentoTO resultado = armazenamentoBO.save(armazenamento);

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
    public Response update(@Valid ArmazenamentoTO armazenamento, @PathParam("codigo") Long idArmazenamento) {

        armazenamento.setIdArmazenamento(idArmazenamento);
        ArmazenamentoTO resultado = armazenamentoBO.update(armazenamento);

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
    public Response delete(@PathParam("codigo") Long idArmazenamento) {

        Response.ResponseBuilder response = null;

        if (armazenamentoBO.delete(idArmazenamento)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();

    }

}
