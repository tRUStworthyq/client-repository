package ru.sber.clientservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.sber.clientservice.entity.Client;
import ru.sber.clientservice.entity.ClientDeals;
import ru.sber.clientservice.repository.ClientRepository;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestcontainersConfiguration.class)
public class ClientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        clientRepository.deleteAll();
        Client client = Client.builder()
                .fullName("Иванов Иван Иванович")
                .inn("123456789012")
                .build();
        client.setDeals(List.of(new ClientDeals("CRD-111", client)));
        clientRepository.save(client);
    }

    @Test
    void getClientByDealId_ShouldReturnClient() throws Exception {
        mvc.perform(get("/api/clients/CRD-111")
                .header("X-API-Version", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.dealId").value("CRD-111"))
                .andExpect(jsonPath("$.fullName").value("Иванов Иван Иванович"))
                .andExpect(jsonPath("$.inn").value("123456789012"));
    }

    @Test
    void getClientByDealId_ShouldReturn404_WhenNotFound() throws Exception {
        mvc.perform(get("/api/clients/TEST")
                .header("X-API-Version", "1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Client not found for dealId: TEST"));
    }

}
