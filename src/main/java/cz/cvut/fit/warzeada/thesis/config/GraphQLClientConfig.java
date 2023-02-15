package cz.cvut.fit.warzeada.thesis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;

@Configuration
public class GraphQLClientConfig {

    @Value("${graphql.apiUrl}")
    private String apiUrl;

    @Value("${graphql.bearerAuth}")
    private String bearerAuth;

    @Bean
    public HttpGraphQlClient httpGraphQlClient() {
        return HttpGraphQlClient.builder()
            .url(apiUrl)
            .headers(headers -> headers.setBearerAuth(bearerAuth))
            .build();
    }

}
