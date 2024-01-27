package com.objects.marketbridge.order.service;

import com.objects.marketbridge.order.controller.response.KakaoPayReadyResponse;
import com.objects.marketbridge.order.dto.KakaoPayReadyRequest;
import com.objects.marketbridge.order.payment.config.KakaoPayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class KakaoPayReadyService {

    private final KakaoPayConfig kakaoPayConfig;
    public KakaoPayReadyResponse execute(KakaoPayReadyRequest request) {

        MultiValueMap<String, String> requestMap = request.toMultiValueMap();

        RestClient restClient = RestClient.builder()
                .baseUrl(KakaoPayConfig.KAKAO_BASE_URL)
                .messageConverters((converters) ->
                    converters.add(new FormHttpMessageConverter()))
                .defaultHeaders((httpHeaders -> {
                    httpHeaders.add("Authorization", "KakaoAK "+kakaoPayConfig.getAdminKey());
                    httpHeaders.add("Accept", APPLICATION_JSON.toString());
                    httpHeaders.add("Content-Type", APPLICATION_FORM_URLENCODED.toString()+";charset=UTF-8");
                }))
                .build();

        return restClient.post()
                .uri("/ready")
                .body(requestMap)
                .retrieve()
                .body(KakaoPayReadyResponse.class);
    }
}
