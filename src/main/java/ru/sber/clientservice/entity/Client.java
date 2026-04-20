package ru.sber.clientservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clients")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @Column(name = "id", length = 50)
    private String id;

    @Column(name = "full_name", nullable = false, length = 255)
    @NotBlank(message = "Full name must not be blank")
    private String fullName;

    @Column(name = "inn", nullable = false, unique = true, length = 12)
    @NotBlank(message = "INN must not be blank")
    @Pattern(regexp = "\\d{10}|\\d{12}", message = "INN must be 10 or 12 digits")
    private String inn;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientDeals> deals = new ArrayList<>();
}
