package duoc.cl.ecomarket.examen.usuarios.repository;

import duoc.cl.ecomarket.examen.usuarios.model.Usuario;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
@Profile("!test")
public class DataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.commerce().productName());
            usuario.setIsValid(faker.bool().bool());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setDireccion(faker.address().streetAddress());
            usuario.setRut(faker.numerify("##.###.###-#"));
            usuarioRepository.save(usuario);
        }
    }
}
