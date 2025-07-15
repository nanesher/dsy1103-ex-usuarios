package duoc.cl.ecomarket.examen.usuarios.controller;


import duoc.cl.ecomarket.examen.usuarios.assemblers.UsuarioModelAssembler;
import duoc.cl.ecomarket.examen.usuarios.model.Usuario;
import duoc.cl.ecomarket.examen.usuarios.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
@Tag(name="Usuarios", description = "Operaciones relacionadas con el registro de usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioModelAssembler  assembler;

    @Operation(summary="Obtener todos los usuarios",description = "Obtiene una lista de todos los usuarios con sus atributos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Todos los usuarios listados correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Usuario>> getAllUsuarios() {
        List<EntityModel<Usuario>> usuarios = usuarioService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(usuarios,
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).createUsuario(null)).withRel("Crear un usuario (con metodo post)"));
    }
    @Operation(summary="Crear un usuario",description = "Se crea un nuevo usuario con los parametros enviados asignadole una ID automaticamente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario creado con éxito",
                    content = @Content(mediaType = "application/hal+json"))
    })
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Usuario>> createUsuario(@RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.save(usuario);
        return ResponseEntity
                .created(linkTo(methodOn(UsuarioController.class).getUsuarioById(newUsuario.getIdUsuario())).toUri())
                .body(assembler.toModel(newUsuario));
    }

    @Operation(summary="Obtener un usuario",description = "Obtiene un usuario con sus atributos por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario listado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })

    @GetMapping (value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Usuario> getUsuarioById(@Parameter(
            description = "ID del usuario a obtener",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id) {
        Usuario usuario = usuarioService.findByIdUsuario(id);
        return assembler.toModel(usuario);
    }

    @Operation(summary="Eliminar un usuario",description = "Se elimina un usuario por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario eliminado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteUsuario(@Parameter(
            description = "ID del usuario a eliminar",
            name = "id",
            required = true,
            in = ParameterIn.PATH,
            example = "5"
    )@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary="Actualizar un usuario",description = "Se actualizan los datos de un usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario actualizado correctamente",
                    content = @Content(mediaType = "application/hal+json"))
    })

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> updateUsuario(
            @Parameter(
                    description = "ID del usuario a actualizar",
                    name = "id",
                    required = true,
                    in = ParameterIn.PATH,
                    example = "5"
            )@PathVariable Integer id, @RequestBody Usuario usuario) {
        try {
            Usuario updatedUsuario = usuarioService.update(id, usuario);
            return ResponseEntity.ok(assembler.toModel(updatedUsuario));
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}



