package ru.sber.clientservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import ru.sber.clientservice.entity.Client;
import ru.sber.clientservice.repository.ClientRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(TestcontainersConfiguration.class)
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;


    @Test
    void findByDealId_shouldSaveAndFindByDealId() {
        Client client = Client.builder()
                .dealId("CRD-111")
                .fullName("Иванов Иван Иванович")
                .inn("123456789012")
                .build();

        clientRepository.save(client);

        Optional<Client> found = clientRepository.findByDealId("CRD-111");

        assertThat(found).isPresent();
        assertThat(found.get().getFullName()).isEqualTo("Иванов Иван Иванович");
    }
}
