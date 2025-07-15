package duoc.cl.ecomarket.examen.usuarios.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {

    @Schema(description = "Identificador unico del usuario", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Schema(description = "Nombre del usuario", example = "Cristiano Ronaldo")
    @Column(name="nombre",nullable=false)
    private String nombre;

    @Schema(description = "Rol Único Tributario (RUT) chileno del usuario", example = "12345678-9")
    @Column(name="rut", nullable = false)
    private String rut;

    @Schema(description = "Direccion de email del usuario", example = "CrisCR7@gmail.com")
    @Column(name="email", nullable = false)
    private String email;

    @Schema(description = "Direccion del hogar del usuario", example = "Calle wallaby 42")
    @Column(name="direccion", nullable = false)
    private String direccion;

    @Schema(description = "Indica si el usuario esta habilitado en el sistema", example = "True")
    @Column(name="is_valid",nullable = false)
    private Boolean isValid=true;




}
