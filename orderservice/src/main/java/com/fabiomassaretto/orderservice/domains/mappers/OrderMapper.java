package com.fabiomassaretto.orderservice.domains.mappers;

import com.fabiomassaretto.orderservice.domains.OrderEntity;
import com.fabiomassaretto.orderservice.domains.OrderRequest;
import com.fabiomassaretto.orderservice.domains.OrderResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderEntity mapOrderRequestToEntity(OrderRequest orderRequest);

    OrderResponse mapOrderEntityToResponse(OrderEntity orderEntity);

    List<OrderResponse> mapOrderEntityListToResponse(List<OrderEntity> orderEntities);
}
