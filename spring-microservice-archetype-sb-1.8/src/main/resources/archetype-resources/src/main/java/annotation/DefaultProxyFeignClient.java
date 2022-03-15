package ${package}.annotation;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.http.HttpStatus;

import ${package}.enums.Errors;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotacion simil a JsonProxyFeignClient pero sin la clase de configuration, esta es usada para los clientes feign que
 * en su service son usados por metodos con la anotacion @Async.
 *
 * @version 1.0.0
 */
@FeignClient
@Target({ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultProxyFeignClient {

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Alias de la anotacion FeignClient para el nombre del servicio.
     *
     * @return el nombre del servicio
     */
    @AliasFor(annotation = FeignClient.class)
    String name();

    /**
     * Alias para la url del servicio.
     *
     * @return la url del servicio
     */
    @AliasFor(annotation = FeignClient.class)
    String url();

    /**
     * Codigo de error que retornará el servicio al ocurrir una excepcion no controlada al llamar al motor interno.
     * Se usa por defecto el codigo de error generico {@link Errors#DEFAULT}
     *
     * @return el codigo de error a retornar.
     */
    Errors error() default Errors.DEFAULT;

    /**
     * Codigo http que retornara el servicio al ocurrir una excepcion no controlada al llamar al servicio Feign.
     *
     * @return el codigo status http.
     */
    HttpStatus status() default HttpStatus.INTERNAL_SERVER_ERROR;

}
