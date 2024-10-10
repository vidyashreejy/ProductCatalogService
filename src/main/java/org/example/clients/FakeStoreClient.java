package org.example.clients;

import org.example.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getProduct(Long productId) {
        FakeStoreProductDto fakeStoreProductDto = RequestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{id}",
                null, FakeStoreProductDto.class, productId).getBody();
        return fakeStoreProductDto;
    }

    public FakeStoreProductDto replaceProductById(Long id, FakeStoreProductDto fakeStoreProductDto) {
        return RequestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}", fakeStoreProductDto, FakeStoreProductDto.class, id).getBody();
    }

    public <T> ResponseEntity<T> RequestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }
}
