package com.fabiomassaretto.order_service.domains.mappers;

import com.fabiomassaretto.order_service.domains.OrderEntity;
import com.fabiomassaretto.order_service.domains.OrderRequest;
import com.fabiomassaretto.order_service.domains.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity mapOrderRequestToEntity(OrderRequest orderRequest);

    OrderResponse mapOrderEntityToResponse(OrderEntity orderEntity);

    List<OrderResponse> mapOrderEntityListToResponse(List<OrderEntity> orderEntities);
}
