package ${package}.annotation;

import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * Configuracion usada por los clientes Feign.
 * No se declara como @Configuration ya que se asigna especificamente a los clientes que lo requieran.
 *
 * @version 1.0.0
 */
@RequiredArgsConstructor
public class FeignConfig {

// -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Obtiene Interceptor para Clientes Feign.
     *
     * @return {@link RequestInterceptor}
     */
    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Content-Type", "application/json");

    }

}
