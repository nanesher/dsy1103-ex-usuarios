package duoc.cl.ecomarket.examen.usuarios;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsuariosApplication.class, args);
	}
	@Value("${productos-api-url}")
	private String productosApiUrl;
	@Value("${envios-api-url}")
	private String enviosApiUrl;
	@Value("${ventas-api-url}")
	private String ventasApiUrl;
	@Value("${pagos-api-url}")
	private String pagossApiUrl;

}
