package duoc.cl.ecomarket.examen.usuarios.repository;

import duoc.cl.ecomarket.examen.usuarios.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {
}
