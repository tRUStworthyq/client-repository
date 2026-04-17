package ru.sber.clientservice.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.clientservice.service.ClientService;
import ru.sber.dto.ClientResponseDto;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping(value = "/{dealId}", version = "1")
    @Timed(value = "client.get.by.dealId", description = "Время, затраченное на получение клиента по dealId")
    public ResponseEntity<ClientResponseDto> getClientByDealId(@PathVariable String dealId) {
        ClientResponseDto response = clientService.getClientByDealId(dealId);
        return ResponseEntity.ok(response);
    }
}
