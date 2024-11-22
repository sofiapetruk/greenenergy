package br.com.fiap.resource;

import br.com.fiap.bo.UsuarioBO;
import br.com.fiap.to.UsuarioTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;

@Path("/register")
public class UsuarioResource {

    private UsuarioBO usuarioBO = new UsuarioBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {

        ArrayList<UsuarioTO> resultado = usuarioBO.findAll();

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
    public Response findById(@PathParam("codigo") Long idCadastro) {

        UsuarioTO resultado = usuarioBO.findById(idCadastro);

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
    public Response save(@Valid UsuarioTO usuario) {

        UsuarioTO resultado = usuarioBO.save(usuario);

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
    public Response update(@Valid UsuarioTO usuario, @PathParam("codigo") Long idCadastro) {

        usuario.setIdCadastro(idCadastro);
        UsuarioTO resultado = usuarioBO.update(usuario);

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
    public Response delete(@PathParam("codigo") Long idCadastro) {

        Response.ResponseBuilder response = null;

        if (usuarioBO.delete(idCadastro)) {
            response = Response.status(204); //204 NO CONTENT
        } else {
            response = Response.status(404); //404 NOT FOUND
        }
        return response.build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UsuarioTO usuario) {

        Response.ResponseBuilder response = null;

        try {
            // Valida o login (verifica o email e senha)
            boolean loginValido = usuarioBO.validarLogin(usuario.getEmail(), usuario.getSenha());

            // Se o login for válido
            if (loginValido) {
                response = Response.ok("Login realizado com sucesso."); // 200 OK
            } else {
                response = Response.status(401).entity("Email ou senha inválidos."); // 401 Unauthorized
            }

        } catch (IllegalArgumentException e) {
            // Se houver erro na validação (ex: email ou senha inválidos)
            response = Response.status(400).entity(e.getMessage()); // 400 Bad Request
        } catch (Exception e) {
            // Em caso de erro genérico
            response = Response.status(500).entity("Erro interno no servidor: " + e.getMessage()); // 500 Internal Server Error
        }

        return response.build();
    }


}
