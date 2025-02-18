package br.com.fiap.resource;

import br.com.fiap.bo.ComercioEnergiaBO;
import br.com.fiap.to.ComercioEnergiaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/distribuicao")
public class ComercioEnergiaResource {

    private ComercioEnergiaBO comercioEnergiaBO = new ComercioEnergiaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<ComercioEnergiaTO> resultado = comercioEnergiaBO.findAll();

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
    public Response findById(@PathParam("codigo") Long idComercio) {

        ComercioEnergiaTO resultado = comercioEnergiaBO.findById(idComercio);

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
    public Response save(@Valid ComercioEnergiaTO comercio) {

        ComercioEnergiaTO resultado = comercioEnergiaBO.save(comercio);

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
    public Response update(@Valid ComercioEnergiaTO comercio, @PathParam("codigo") Long idComercio) {

        comercio.setIdComercio(idComercio);
        ComercioEnergiaTO resultado = comercioEnergiaBO.update(comercio);

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
    public Response delete(@PathParam("codigo") Long idComercio) {

        Response.ResponseBuilder response = null;

        if (comercioEnergiaBO.delete(idComercio)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();

    }


}
