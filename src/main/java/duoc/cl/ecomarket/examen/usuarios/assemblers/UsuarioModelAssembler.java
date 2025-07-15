package duoc.cl.ecomarket.examen.usuarios.assemblers;


import duoc.cl.ecomarket.examen.usuarios.controller.UsuarioController;
import duoc.cl.ecomarket.examen.usuarios.model.Usuario;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, EntityModel<Usuario>> {

    @Override
    public EntityModel<Usuario> toModel(Usuario usuario) {
        return EntityModel.of(usuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getIdUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("Lista de todos los usuario (con metodo get)"),
                linkTo(methodOn(UsuarioController.class).deleteUsuario(usuario.getIdUsuario())).withRel("Borrar un usuario (con metodo delete)"),
                linkTo(methodOn(UsuarioController.class).updateUsuario(usuario.getIdUsuario(),null)).withRel("Actualizar un usuario (con metodo put)"));
    }

}