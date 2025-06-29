package alvarompdev.newprojectapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Clase de configuración de Spring
 * RestTemplate ese necesario si queremos realizar peticiones HTTP a APIs externas (por ejemplo, aquí consumimos la API REST de Open Food Facts)
 *
 * @author Álvaro Muñoz Panadero - alvarompdev on GitHub - alvaromp.dev@gmail.com
 */
@Configuration
public class RestConfig {

    /**
     * Se crea e inyecta un bean RestTemplate para poder consumir APIs externas
     *
     * @return Instancia de RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Cliente HTTP para consumir APIs externas
    }

}