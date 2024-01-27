package com.objects.marketbridge.order.payment.service;

import com.objects.marketbridge.order.domain.Order;
import com.objects.marketbridge.order.payment.controller.request.KakaoPayApproveRequest;
import com.objects.marketbridge.order.payment.controller.response.KakaoPayApproveResponse;
import com.objects.marketbridge.order.service.port.OrderRepository;
import com.objects.marketbridge.order.payment.config.KakaoPayConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

import static com.objects.marketbridge.order.payment.config.KakaoPayConfig.KAKAO_BASE_URL;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
@RequiredArgsConstructor
public class KakaoPaymentApproveService {

    private final KakaoPayConfig kakaoPayConfig;
    private final OrderRepository orderRepository;
    public KakaoPayApproveResponse execute(String pgToken, String orderNo, String cid) {

        Order order = orderRepository.findByOrderNo(orderNo);
        MultiValueMap<String, String> requestMap = createRequest(order, pgToken, cid).toMultiValueMap();

        RestClient restClient = RestClient.builder()
                .baseUrl(KAKAO_BASE_URL)
                .messageConverters((converters) ->
                        converters.add(new FormHttpMessageConverter()))
                .defaultHeaders((httpHeaders -> {
                    httpHeaders.add("Authorization", "KakaoAK "+kakaoPayConfig.getAdminKey());
                    httpHeaders.add("Accept", APPLICATION_JSON.toString());
                    httpHeaders.add("Content-Type", APPLICATION_FORM_URLENCODED.toString()+";charset=UTF-8");
                }))
                .build();

        return restClient.post()
                .uri("/approve")
                .body(requestMap)
                .retrieve()
                .body(KakaoPayApproveResponse.class);
    }

    private KakaoPayApproveRequest createRequest(Order order, String pgToken, String cid) {

        return KakaoPayApproveRequest.builder()
                .tid(order.getTid())
                .cid(cid)
                .partnerOrderId(order.getOrderNo())
                .partnerUserId(order.getMember().getId().toString())
                .pgToken(pgToken)
                .build();
    }
}
