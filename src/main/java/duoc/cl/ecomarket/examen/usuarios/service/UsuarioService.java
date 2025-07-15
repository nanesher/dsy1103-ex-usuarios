package duoc.cl.ecomarket.examen.usuarios.service;

import duoc.cl.ecomarket.examen.usuarios.model.Usuario;
import duoc.cl.ecomarket.examen.usuarios.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {this.usuarioRepository = usuarioRepository;}

    public List<Usuario> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }
    public Usuario findByIdUsuario(Integer idUsuario){
        return usuarioRepository.findById(idUsuario).get();
    }
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    public void delete(Integer idUsuario){
        usuarioRepository.deleteById(idUsuario);
    }
    public Usuario update(Integer idUsuario, Usuario usuario2){
        Usuario usuario1= findByIdUsuario(idUsuario);
        usuario1.setNombre(usuario2.getNombre());
        usuario1.setEmail(usuario2.getEmail());
        usuario1.setRut(usuario2.getRut());
        usuario1.setDireccion(usuario2.getDireccion());
        usuario1.setIsValid(usuario2.getIsValid());
        return usuarioRepository.save(usuario1);

    }
}
