package com.nisum.hack.ms.ledger.base;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Optional;

@ContextConfiguration
public class AcceptanceBaseStep {

    private static final String HOST = existEnv("HOST")? getEnvValue("HOST"): "localhost";
    protected static final String CONTEXT_PATH = existEnv("CTX_PATH")? getEnvValue("CTX_PATH"): "/";
    protected static final String PORT = existEnv("HOST")? getEnvPort(): "8080";
    protected static final String PROTOCOL = existEnv("PROTOCOL")? getEnvValue("PROTOCOL"): "http";
    private static final String COLON = ":";

    protected RestTemplate restTemplate;
    protected HttpHeaders headers;

    public static final String getEnvValue(String variable) {
        return Optional.ofNullable(System.getenv(variable)).orElse("");
    }

    public static final String getEnvPort() {
        return getEnvValue("PORT");
    }

    public static final boolean existEnv(String variable) {
        return Objects.nonNull(System.getenv(variable));
    }

    private void loadHeaders() {
        headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
    }

    public <U,T> ResponseEntity<T> call(U request, HttpMethod method, Class<T> tClass) throws URISyntaxException {

        String separator = "://";
        String port = !PORT.equalsIgnoreCase("") ? COLON + PORT : PORT;
        restTemplate = new RestTemplate();
        loadHeaders();

        if(!method.equals(HttpMethod.GET)) {
            headers.setContentType(MediaType.APPLICATION_JSON);
        }

        HttpEntity<U> entity = new HttpEntity<>(request, headers);

        URI endpoint = new URI(PROTOCOL + separator + HOST + port + CONTEXT_PATH);

        return restTemplate.exchange(endpoint.toString(), method, entity, tClass);
    }

}
