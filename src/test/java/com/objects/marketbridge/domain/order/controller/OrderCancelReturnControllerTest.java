//package com.objects.marketbridge.domain.order.controller;
//
//import com.objects.marketbridge.domain.member.repository.MemberRepository;
//import com.objects.marketbridge.domain.order.RestDocsSupport;
//import com.objects.marketbridge.domain.order.controller.request.OrderCancelRequest;
//import com.objects.marketbridge.domain.order.controller.response.*;
//import com.objects.marketbridge.domain.order.dto.OrderCancelServiceDto;
//import com.objects.marketbridge.domain.order.dto.OrderReturnResponse;
//import com.objects.marketbridge.domain.order.dto.ReturnRefundInfoResponse;
//import com.objects.marketbridge.domain.order.entity.Order;
//import com.objects.marketbridge.domain.order.entity.OrderDetail;
//import com.objects.marketbridge.domain.order.service.OrderCancelReturnService;
//import com.objects.marketbridge.domain.order.service.port.OrderDtoRepository;
//import com.objects.marketbridge.domain.order.service.port.OrderRepository;
//import com.objects.marketbridge.domain.payment.dto.RefundDto;
//import com.objects.marketbridge.domain.product.repository.ProductRepository;
//import com.objects.marketbridge.common.infra.entity.Member;
//import com.objects.marketbridge.common.infra.entity.Product;
//import jakarta.persistence.EntityManager;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
//import org.springframework.restdocs.payload.JsonFieldType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static com.objects.marketbridge.domain.order.entity.StatusCodeType.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.mock;
//import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
//import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
//import static org.springframework.restdocs.payload.PayloadDocumentation.*;
//import static org.springframework.restdocs.request.RequestDocumentation.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Transactional
//@SpringBootTest
//@ActiveProfiles("test")
//public class OrderCancelReturnControllerTest extends RestDocsSupport {
//
//    private final OrderCancelReturnService orderCancelReturnService = mock(OrderCancelReturnService.class);
//
//    @Override
//    protected Object initController() {
//        return new OrderCancelReturnController(orderCancelReturnService);
//    }
//
//    @Autowired
//    OrderRepository orderRepository;
//    @Autowired
//    ProductRepository productRepository;
//    @Autowired
//    MemberRepository memberRepository;
//    @Autowired
//    OrderDtoRepository orderDtoRepository;
//    @Autowired
//    EntityManager entityManager;
//
//    @Test
//    @DisplayName("주문 취소 확정 API")
//    public void cancelOrder() throws Exception {
//        // given
//        OrderCancelServiceDto request = OrderCancelRequest.builder()
//                .orderId(1L)
//                .cancelReason("빵빵아! 옥지얌!")
//                .build()
//                .toServiceRequest();
//
//        given(orderCancelReturnService.cancelReturnOrder(any(OrderCancelServiceDto.class), any(LocalDateTime.class)))
//                .willReturn(OrderCancelReturnResponse.builder()
//                        .orderId(1L)
//                        .orderNumber("ORD001")
//                        .totalPrice(300L)
//                        .cancellationDate(LocalDateTime.of(2024, 1, 18, 12, 26))
//                        .refundInfo(RefundInfo.of(
//                                RefundDto.builder()
//                                        .totalRefundAmount(10000L)
//                                        .refundMethod("card")
//                                        .refundProcessedAt(LocalDateTime.of(2024, 1, 18, 12, 26))
//                                        .build())
//                        )
//                        .cancelledItems(List.of(
//                                        ProductResponse.builder()
//                                                .productId(1L)
//                                                .name("빵빵이 키링")
//                                                .productNo("P123456")
//                                                .price(10000L)
//                                                .quantity(2L)
//                                                .build(),
//                                        ProductResponse.builder()
//                                                .productId(2L)
//                                                .name("옥지얌 키링")
//                                                .productNo("P2345667")
//                                                .price(20000L)
//                                                .quantity(3L)
//                                                .build()
//                                )
//                        )
//                        .build()
//                );
//
//        // when // then
//        mockMvc.perform(
//                        post("/orders/cancel-return-flow/thank-you")
//                                .content(objectMapper.writeValueAsString(request))
//                                .contentType(MediaType.APPLICATION_JSON)
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("order-cancel-return",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        requestFields(
//                                fieldWithPath("orderId").type(JsonFieldType.NUMBER)
//                                        .description("상품 타입"),
//                                fieldWithPath("cancelReason").type(JsonFieldType.STRING)
//                                        .description("상품 판매상태")
//                        ),
//                        responseFields(
//                                fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                        .description("코드"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("상태"),
//                                fieldWithPath("message").type(JsonFieldType.STRING)
//                                        .description("메시지"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                        .description("응답 데이터"),
//                                fieldWithPath("data.orderId").type(JsonFieldType.NUMBER)
//                                        .description("주문 ID"),
//                                fieldWithPath("data.orderNumber").type(JsonFieldType.STRING)
//                                        .description("주문 번호"),
//                                fieldWithPath("data.totalPrice").type(JsonFieldType.NUMBER)
//                                        .description("주문 총 가격"),
//                                fieldWithPath("data.cancellationDate").type(JsonFieldType.ARRAY)
//                                        .description("주문 취소 시간"),
//                                fieldWithPath("data.cancelledItems").type(JsonFieldType.ARRAY)
//                                        .description("상품 이름"),
//                                fieldWithPath("data.cancelledItems[].productId").type(JsonFieldType.NUMBER)
//                                        .description("취소된 상품의 제품 ID"),
//                                fieldWithPath("data.cancelledItems[].productNo").type(JsonFieldType.STRING)
//                                        .description("취소된 상품의 제품 번호"),
//                                fieldWithPath("data.cancelledItems[].name").type(JsonFieldType.STRING)
//                                        .description("취소된 상품의 이름"),
//                                fieldWithPath("data.cancelledItems[].price").type(JsonFieldType.NUMBER)
//                                        .description("취소된 상품의 가격"),
//                                fieldWithPath("data.cancelledItems[].quantity").type(JsonFieldType.NUMBER)
//                                        .description("취소된 주문 수량"),
//                                fieldWithPath("data.refundInfo").type(JsonFieldType.OBJECT)
//                                        .description("환불 정보"),
//                                fieldWithPath("data.refundInfo.totalRefundAmount").type(JsonFieldType.NUMBER)
//                                        .description("환불 총 금액"),
//                                fieldWithPath("data.refundInfo.refundMethod").type(JsonFieldType.STRING)
//                                        .description("환불 방법"),
//                                fieldWithPath("data.refundInfo.refundProcessedAt").type(JsonFieldType.ARRAY)
//                                        .description("환불 시간")
//                        )
//                ));
//    }
//
//    @Test
//    @DisplayName("주문 취소 요청 API")
//    public void requestCancelOrder() throws Exception {
//        // given
//
//        Product product1 = Product.builder()
//                .price(1000L)
//                .thumbImg("썸네일1")
//                .name("옷")
//                .build();
//        Product product2 = Product.builder()
//                .name("바지")
//                .price(2000L)
//                .thumbImg("썸네일2")
//                .build();
//        Product product3 = Product.builder()
//                .name("신발")
//                .price(3000L)
//                .thumbImg("썸네일3")
//                .build();
//
//        OrderDetail orderDetail1 = OrderDetail.builder()
//                .product(product1)
//                .quantity(2L)
//                .price(product1.getPrice() * 2L)
//                .build();
//        OrderDetail orderDetail2 = OrderDetail.builder()
//                .product(product2)
//                .quantity(3L)
//                .price(product2.getPrice() * 3L)
//                .build();
//        OrderDetail orderDetail3 = OrderDetail.builder()
//                .product(product3)
//                .quantity(4L)
//                .price(product3.getPrice() * 4L)
//                .build();
//
//        Order order = Order.builder().build();
//
//        given(orderCancelReturnService.requestCancel(any(Long.class), any(List.class)))
//                .willReturn(OrderCancelResponse.builder()
//                        .cancelRefundInfoResponse(CancelRefundInfoResponse.builder()
//                                .deliveryFee(0L)
//                                .refundFee(0L)
//                                .totalPrice(20000L)
//                                .discountPrice(0L)
//                                .build()
//                        )
//                        .productResponses(List.of(
//                                        ProductInfoResponse.of(orderDetail1),
//                                        ProductInfoResponse.of(orderDetail2),
//                                        ProductInfoResponse.of(orderDetail3)
//                                )
//                        )
//                        .build()
//
//                );
//
//        // when // then
//        mockMvc.perform(
//                        get("/orders/cancel-flow")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .param("orderId", "1")
//                                .param("productIds", "1", "2", "3")
//
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("order-cancel-request",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        queryParameters(
//                                parameterWithName("orderId")
//                                        .description("주문 ID"),
//                                parameterWithName("productIds")
//                                        .description("취소 상품ID 리스트")
//                        ),
//                        responseFields(
//                                fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                        .description("코드"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("상태"),
//                                fieldWithPath("message").type(JsonFieldType.STRING)
//                                        .description("메시지"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                        .description("응답 데이터"),
//                                fieldWithPath("data.productResponses").type(JsonFieldType.ARRAY)
//                                        .description("취소 상품 리스트"),
//                                fieldWithPath("data.productResponses[].quantity").type(JsonFieldType.NUMBER)
//                                        .description("취소 상품 수량"),
//                                fieldWithPath("data.productResponses[].name").type(JsonFieldType.STRING)
//                                        .description("취소 상품 이름"),
//                                fieldWithPath("data.productResponses[].price").type(JsonFieldType.NUMBER)
//                                        .description("취소 상품 가격"),
//                                fieldWithPath("data.productResponses[].image").type(JsonFieldType.STRING)
//                                        .description("취소 상품 썸네일"),
//                                fieldWithPath("data.cancelRefundInfoResponse").type(JsonFieldType.OBJECT)
//                                        .description("취소 상품 썸네일"),
//                                fieldWithPath("data.cancelRefundInfoResponse.deliveryFee").type(JsonFieldType.NUMBER)
//                                        .description("환불 배송비"),
//                                fieldWithPath("data.cancelRefundInfoResponse.refundFee").type(JsonFieldType.NUMBER)
//                                        .description("환불 금액"),
//                                fieldWithPath("data.cancelRefundInfoResponse.discountPrice").type(JsonFieldType.NUMBER)
//                                        .description("할인 금액"),
//                                fieldWithPath("data.cancelRefundInfoResponse.totalPrice").type(JsonFieldType.NUMBER)
//                                        .description("상품 전체 금액")
//                        )));
//    }
//
//    @Test
//    @DisplayName("주문 반품 요청 API")
//    public void requestReturnOrder() throws Exception {
//        // given
//        Long orderId = 1L;
//        List<Long> productIds = List.of(1L, 2L, 3L);
//
//        Product product1 = Product.builder()
//                .price(1000L)
//                .thumbImg("썸네일1")
//                .name("옷")
//                .build();
//        Product product2 = Product.builder()
//                .name("바지")
//                .price(2000L)
//                .thumbImg("썸네일2")
//                .build();
//        Product product3 = Product.builder()
//                .name("신발")
//                .price(3000L)
//                .thumbImg("썸네일3")
//                .build();
//
//        OrderDetail orderDetail1 = OrderDetail.builder()
//                .product(product1)
//                .quantity(2L)
//                .price(product1.getPrice() * 2L)
//                .build();
//        OrderDetail orderDetail2 = OrderDetail.builder()
//                .product(product2)
//                .quantity(3L)
//                .price(product2.getPrice() * 3L)
//                .build();
//        OrderDetail orderDetail3 = OrderDetail.builder()
//                .product(product3)
//                .quantity(4L)
//                .price(product3.getPrice() * 4L)
//                .build();
//
//        Order order = Order.builder().build();
//
//        given(orderCancelReturnService.requestReturn(any(Long.class), any(List.class)))
//                .willReturn(OrderReturnResponse.builder()
//                        .returnRefundInfoResponse(
//                                ReturnRefundInfoResponse.builder()
//                                        .returnFee(0L)
//                                        .deliveryFee(0L)
//                                        .productPrice(20000L)
//                                        .build()
//                        )
//                        .productResponses(
//                                List.of(
//                                        ProductInfoResponse.of(orderDetail1),
//                                        ProductInfoResponse.of(orderDetail2),
//                                        ProductInfoResponse.of(orderDetail3)
//                                )
//                        )
//                        .build()
//
//                );
//
//        // when // then
//        mockMvc.perform(
//                        get("/orders/return-flow")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .param("orderId", "1")
//                                .param("productIds", "1", "2", "3")
//
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("order-return-request",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        queryParameters(
//                                parameterWithName("orderId")
//                                        .description("주문 ID"),
//                                parameterWithName("productIds")
//                                        .description("취소 상품ID 리스트")
//                        ),
//                        responseFields(
//                                fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                        .description("코드"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("상태"),
//                                fieldWithPath("message").type(JsonFieldType.STRING)
//                                        .description("메시지"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                        .description("응답 데이터"),
//                                fieldWithPath("data.productResponses").type(JsonFieldType.ARRAY)
//                                        .description("반품 상품 리스트"),
//                                fieldWithPath("data.productResponses[].quantity").type(JsonFieldType.NUMBER)
//                                        .description("반품 상품 수량"),
//                                fieldWithPath("data.productResponses[].name").type(JsonFieldType.STRING)
//                                        .description("반품 상품 이름"),
//                                fieldWithPath("data.productResponses[].price").type(JsonFieldType.NUMBER)
//                                        .description("반품 상품 가격"),
//                                fieldWithPath("data.productResponses[].image").type(JsonFieldType.STRING)
//                                        .description("반품 상품 썸네일"),
//                                fieldWithPath("data.returnRefundInfoResponse").type(JsonFieldType.OBJECT)
//                                        .description("반품 환불 정보"),
//                                fieldWithPath("data.returnRefundInfoResponse.deliveryFee").type(JsonFieldType.NUMBER)
//                                        .description("환불 배송비"),
//                                fieldWithPath("data.returnRefundInfoResponse.returnFee").type(JsonFieldType.NUMBER)
//                                        .description("반품 배송비"),
//                                fieldWithPath("data.returnRefundInfoResponse.productPrice").type(JsonFieldType.NUMBER)
//                                        .description("환불(상품) 금액")
//                        )));
//    }
//
//    @Test
//    @DisplayName("주문 취소/반품 리스트 반환 API")
//    public void getCancelReturnList() throws Exception {
//        // given
//        Member member = Member.builder().build();
//
//        Order order1 = Order.builder()
//                .member(member)
//                .orderNo("123")
//                .build();
//
//        Order order2 = Order.builder()
//                .member(member)
//                .orderNo("456")
//                .build();
//
//        Product product1 = Product.builder()
//                .productNo("1")
//                .name("옷")
//                .price(1000L)
//                .build();
//        Product product2 = Product.builder()
//                .productNo("2")
//                .name("신발")
//                .price(2000L)
//                .build();
//        Product product3 = Product.builder()
//                .productNo("3")
//                .name("바지")
//                .price(3000L)
//                .build();
//
//        OrderDetail orderDetail1 = OrderDetail.builder()
//                .order(order1)
//                .product(product1)
//                .quantity(1L)
//                .orderNo(order1.getOrderNo())
//                .statusCode(RETURN_COMPLETED.getCode())
//                .build();
//        OrderDetail orderDetail2 = OrderDetail.builder()
//                .order(order1)
//                .product(product2)
//                .quantity(2L)
//                .orderNo(order1.getOrderNo())
//                .statusCode(ORDER_CANCEL.getCode())
//                .build();
//        OrderDetail orderDetail3 = OrderDetail.builder()
//                .order(order2)
//                .product(product3)
//                .quantity(3L)
//                .orderNo(order2.getOrderNo())
//                .statusCode(ORDER_CANCEL.getCode())
//                .build();
//        OrderDetail orderDetail4 = OrderDetail.builder()
//                .order(order2)
//                .product(product2)
//                .quantity(4L)
//                .orderNo(order2.getOrderNo())
//                .statusCode(DELIVERY_ING.getCode())
//                .build();
//
//        order1.addOrderDetail(orderDetail1);
//        order1.addOrderDetail(orderDetail2);
//        order2.addOrderDetail(orderDetail3);
//        order2.addOrderDetail(orderDetail4);
//
//        productRepository.saveAll(List.of(product1, product2, product3));
//        memberRepository.save(member);
//        orderRepository.save(order1);
//        orderRepository.save(order2);
//
//        Page<OrderCancelReturnListResponse> orderCancelReturnListResponsePage = orderDtoRepository.findOrdersByMemberId(member.getId(), PageRequest.of(0, 3));
//
//        given(orderCancelReturnService.findCancelReturnList(any(Long.class), any(Pageable.class)))
//                .willReturn(orderCancelReturnListResponsePage);
//
//        // when // then
//        mockMvc.perform(
//                        get("/orders/cancel-return/list")
//                                .accept(MediaType.APPLICATION_JSON)
//                                .param("memberId", "1")
//                                .param("page", "0")
//                                .param("size", "10")
//
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("order-cancel-return-list",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        queryParameters(
//                                parameterWithName("memberId")
//                                        .description("유저 ID"),
//                                parameterWithName("page")
//                                        .description("페이지"),
//                                parameterWithName("size")
//                                        .description("사이즈")
//                        ),
//                        responseFields(
//                                fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                        .description("코드"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("상태"),
//                                fieldWithPath("message").type(JsonFieldType.STRING)
//                                        .description("메시지"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                        .description("응답 데이터"),
//                                fieldWithPath("data.content[].cancelReceiptDate")
//                                        .description("주문 취소 날짜"),
//                                fieldWithPath("data.content[].orderDate")
//                                        .description("주문 날짜"),
//                                fieldWithPath("data.content[].orderNo").type(JsonFieldType.STRING)
//                                        .description("주문 번호"),
//                                fieldWithPath("data.content[].orderDetailResponses[].orderNo").type(JsonFieldType.STRING)
//                                        .description("주문 번호"),
//                                fieldWithPath("data.content[].orderDetailResponses[].productId").type(JsonFieldType.NUMBER)
//                                        .description("상품 Id"),
//                                fieldWithPath("data.content[].orderDetailResponses[].productNo").type(JsonFieldType.STRING)
//                                        .description("상품 번호"),
//                                fieldWithPath("data.content[].orderDetailResponses[].name").type(JsonFieldType.STRING)
//                                        .description("상품 이름"),
//                                fieldWithPath("data.content[].orderDetailResponses[].price").type(JsonFieldType.NUMBER)
//                                        .description("상품 가격"),
//                                fieldWithPath("data.content[].orderDetailResponses[].quantity").type(JsonFieldType.NUMBER)
//                                        .description("상품 주문 수량"),
//                                fieldWithPath("data.content[].orderDetailResponses[].orderStatus").type(JsonFieldType.STRING)
//                                        .description("주문 상태"),
//                                fieldWithPath("data.pageable.pageNumber").type(JsonFieldType.NUMBER)
//                                        .description("페이지 번호"),
//                                fieldWithPath("data.pageable.pageSize").type(JsonFieldType.NUMBER)
//                                        .description("페이지당 요소 수"),
//                                fieldWithPath("data.pageable.sort.empty").type(JsonFieldType.BOOLEAN)
//                                        .description("정렬이 비어 있는지 여부"),
//                                fieldWithPath("data.pageable.sort.sorted").type(JsonFieldType.BOOLEAN)
//                                        .description("정렬이 되어 있는지 여부"),
//                                fieldWithPath("data.pageable.sort.unsorted").type(JsonFieldType.BOOLEAN)
//                                        .description("정렬이 되어 있지 않은지 여부"),
//                                fieldWithPath("data.pageable.offset").type(JsonFieldType.NUMBER)
//                                        .description("페이지 오프셋"),
//                                fieldWithPath("data.pageable.paged").type(JsonFieldType.BOOLEAN)
//                                        .description("페이지가 있는지 여부"),
//                                fieldWithPath("data.pageable.unpaged").type(JsonFieldType.BOOLEAN)
//                                        .description("페이지가 없는지 여부"),
//                                fieldWithPath("data.last").type(JsonFieldType.BOOLEAN)
//                                        .description("마지막 페이지 여부"),
//                                fieldWithPath("data.totalPages").type(JsonFieldType.NUMBER)
//                                        .description("전체 페이지 수"),
//                                fieldWithPath("data.totalElements").type(JsonFieldType.NUMBER)
//                                        .description("전체 요소 수"),
//                                fieldWithPath("data.size").type(JsonFieldType.NUMBER)
//                                        .description("페이지 크기"),
//                                fieldWithPath("data.number").type(JsonFieldType.NUMBER)
//                                        .description("페이지 번호"),
//                                fieldWithPath("data.sort.empty").type(JsonFieldType.BOOLEAN)
//                                        .description("정렬이 비어 있는지 여부"),
//                                fieldWithPath("data.sort.sorted").type(JsonFieldType.BOOLEAN)
//                                        .description("정렬이 되어 있는지 여부"),
//                                fieldWithPath("data.sort.unsorted").type(JsonFieldType.BOOLEAN)
//                                        .description("정렬이 되어 있지 않은지 여부"),
//                                fieldWithPath("data.first").type(JsonFieldType.BOOLEAN)
//                                        .description("첫 페이지 여부"),
//                                fieldWithPath("data.numberOfElements").type(JsonFieldType.NUMBER)
//                                        .description("현재 페이지의 요소 수"),
//                                fieldWithPath("data.empty").type(JsonFieldType.BOOLEAN)
//                                        .description("컨텐츠가 비어 있는지 여부")
//
//
//                        )));
//    }
//
//    @Test
//    @DisplayName("")
//    public void getCancelReturnDetail() throws Exception {
//        // given
//        given(orderCancelReturnService.findCancelReturnDetail(any(String.class), any(Long.class), any(List.class)))
//                .willReturn(OrderCancelReturnDetailResponse.builder()
//                        .orderDate(LocalDateTime.now())
//                        .cancelDate(LocalDateTime.now())
//                        .orderNo("123")
//                        .cancelReason("빵빵이 기여워")
//                        .productResponseList(
//                                List.of(
//                                        ProductResponse.builder()
//                                                .productId(1L)
//                                                .productNo("1")
//                                                .name("빵빵이 키링")
//                                                .price(10000L)
//                                                .quantity(2L)
//                                                .build(),
//                                        ProductResponse.builder()
//                                                .productId(2L)
//                                                .productNo("2")
//                                                .name("옥지얌 키링")
//                                                .price(20000L)
//                                                .quantity(4L)
//                                                .build()
//                                )
//                        )
//                        .cancelRefundInfoResponse(
//                                CancelRefundInfoResponse.builder()
//                                        .deliveryFee(0L)
//                                        .refundFee(0L)
//                                        .discountPrice(5000L)
//                                        .totalPrice(100000L)
//                                        .build()
//                        )
//                        .build()
//                );
//
//        // when // then
//        mockMvc.perform(
//                        RestDocumentationRequestBuilders.get("/orders/cancel-return/{orderNo}", "123")
//                                .param("paymentId", "1")
//                                .param("receiptType", "card")
//                                .param("productIds", "1", "2", "3")
//                                .accept(MediaType.APPLICATION_JSON)
//
//                )
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andDo(document("order-cancel-return-detail",
//                        preprocessRequest(prettyPrint()),
//                        preprocessResponse(prettyPrint()),
//                        pathParameters(
//                                parameterWithName("orderNo")
//                                .description("주문 번호")
//                        ),
//                        queryParameters(
//                                parameterWithName("paymentId")
//                                        .description("유저 ID"),
//                                parameterWithName("receiptType")
//                                        .description("페이지"),
//                                parameterWithName("productIds")
//                                        .description("사이즈")
//                        ),
//                        responseFields(
//                                fieldWithPath("code").type(JsonFieldType.NUMBER)
//                                        .description("코드"),
//                                fieldWithPath("status").type(JsonFieldType.STRING)
//                                        .description("상태"),
//                                fieldWithPath("message").type(JsonFieldType.STRING)
//                                        .description("메시지"),
//                                fieldWithPath("data").type(JsonFieldType.OBJECT)
//                                        .description("응답 데이터"),
//                                fieldWithPath("data.orderDate").type(JsonFieldType.ARRAY)
//                                        .description("주문 날짜"),
//                                fieldWithPath("data.cancelDate").type(JsonFieldType.ARRAY)
//                                        .description("주문 취소 날짜"),
//                                fieldWithPath("data.orderNo").type(JsonFieldType.STRING)
//                                        .description("주문 번호"),
//                                fieldWithPath("data.cancelReason").type(JsonFieldType.STRING)
//                                        .description("주문 취소 이유"),
//                                fieldWithPath("data.productResponseList").type(JsonFieldType.ARRAY)
//                                        .description("상품 정보 리스트"),
//                                fieldWithPath("data.productResponseList[].productId").type(JsonFieldType.NUMBER)
//                                        .description("상품 ID"),
//                                fieldWithPath("data.productResponseList[].productNo").type(JsonFieldType.STRING)
//                                        .description("상품 번호"),
//                                fieldWithPath("data.productResponseList[].name").type(JsonFieldType.STRING)
//                                        .description("상품 이름"),
//                                fieldWithPath("data.productResponseList[].price").type(JsonFieldType.NUMBER)
//                                        .description("주문 가격"),
//                                fieldWithPath("data.productResponseList[].quantity").type(JsonFieldType.NUMBER)
//                                        .description("상품 주문 수량"),
//                                fieldWithPath("data.cancelRefundInfoResponse").type(JsonFieldType.OBJECT)
//                                        .description("취소/반품 정보"),
//                                fieldWithPath("data.cancelRefundInfoResponse.deliveryFee").type(JsonFieldType.NUMBER)
//                                        .description("배송 비용"),
//                                fieldWithPath("data.cancelRefundInfoResponse.refundFee").type(JsonFieldType.NUMBER)
//                                        .description("반품 비용"),
//                                fieldWithPath("data.cancelRefundInfoResponse.discountPrice").type(JsonFieldType.NUMBER)
//                                        .description("할인 금액"),
//                                fieldWithPath("data.cancelRefundInfoResponse.totalPrice").type(JsonFieldType.NUMBER)
//                                        .description("상품 할인 전 금액 합계")
//                        )));
//    }
//}
