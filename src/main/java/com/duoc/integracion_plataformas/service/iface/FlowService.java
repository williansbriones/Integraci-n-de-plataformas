package com.duoc.integracion_plataformas.service.iface;

import com.duoc.integracion_plataformas.dto.FlowResponseDto;
import org.springframework.util.LinkedMultiValueMap;

public interface FlowService {

    FlowResponseDto CreateOrder(int amount, String email);


    LinkedMultiValueMap<String, String> param();

}
