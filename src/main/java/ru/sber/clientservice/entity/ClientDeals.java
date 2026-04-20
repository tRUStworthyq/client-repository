package ru.sber.clientservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "client_deals")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientDeals {

    @Id
    @Column(name = "deal_id", length = 50)
    private String dealId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;
}
