package ru.sber.clientservice.service;

import ru.sber.dto.ClientResponseDto;

public interface ClientService {
    ClientResponseDto getClientByDealId(String dealId);
}
