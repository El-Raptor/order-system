package com.raptor.ordersystem.controller;

import com.raptor.ordersystem.dto.OrderDTO;
import com.raptor.ordersystem.entity.Order;
import com.raptor.ordersystem.entity.User;
import com.raptor.ordersystem.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// 1. Removido @SpringBootTest - não é necessário para um teste unitário puro
@ExtendWith(MockitoExtension.class) // Apenas a extensão Mockito
public class OrderControllerUnitTest {

    // 2. Mockar apenas o que o controller realmente usa
    @Mock
    private OrderService orderService;
    // Removido o mock do OrderItemService (não utilizado)

    // 3. Usar @InjectMocks para criar o controller e injetar os mocks
    @InjectMocks
    private OrderController orderController;
    // Removido o @Autowired

    private List<Order> ordersFromService;
    private List<OrderDTO> expectedOrderDTOs;

    @BeforeEach
    public void setUp() {
        // 4. Simplificar o setup para conter APENAS o necessário para este teste
        User user1 = User.builder().id(1).build();
        LocalDateTime testDate = LocalDateTime.now();

        // Dados que o serviço retornará
        ordersFromService = new ArrayList<>(List.of(
                Order.builder()
                        .orderId(1)
                        .user(user1)
                        .orderDate(testDate)
                        .build(),
                Order.builder()
                        .orderId(2)
                        .user(user1)
                        .orderDate(testDate.plusHours(1))
                        .build()
        ));

        // 5. Definir o resultado ESPERADO (DTOs)
        // (Assumindo que OrderDTO tenha um builder e os campos orderId e orderDate)
        expectedOrderDTOs = new ArrayList<>(List.of(
                OrderDTO.builder().orderId(1).orderDate(testDate).build(),
                OrderDTO.builder().orderId(2).orderDate(testDate.plusHours(1)).build()
        ));
    }

    @Test
    @DisplayName("Should find all orders by user Id and return as DTOs")
    public void shouldFindAllOrdersByUserId() {
        // --- Arrange ---
        int userId = 1;
        // 6. Ser específico no mock
        Mockito.when(orderService.findByUserId(userId)).thenReturn(ordersFromService);

        // --- Act ---
        ResponseEntity<List<OrderDTO>> result = orderController.findOrdersByUserId(userId);

        // --- Assert ---
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());

        // 7. Esta é a asserção correta: comparar DTOs esperados com DTOs reais
        List<OrderDTO> actualDTOs = result.getBody();
        Assertions.assertNotNull(actualDTOs);
        Assertions.assertEquals(expectedOrderDTOs.size(), actualDTOs.size());

        // Para uma comparação mais robusta, compare os objetos (requer .equals() no DTO)
        Assertions.assertEquals(expectedOrderDTOs, actualDTOs);
        // Ou compare campo a campo
        // Assertions.assertEquals(expectedOrderDTOs.get(0).getOrderId(), actualDTOs.get(0).getOrderId());

        // 8. (Opcional, mas recomendado) Verificar se o serviço foi chamado como esperado
        Mockito.verify(orderService, Mockito.times(1)).findByUserId(userId);
    }
}