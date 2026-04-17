package ru.sber.clientservice.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clients")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "deal_id", nullable = false, unique = true, length = 50)
    @NotBlank(message = "Full name must not be blank")
    private String dealId;

    @Column(name = "full_name", nullable = false, length = 255)
    @NotBlank(message = "Full name must not be blank")
    private String fullName;

    @Column(name = "inn", nullable = false, unique = true, length = 12)
    @NotBlank(message = "INN must not be blank")
    @Pattern(regexp = "\\d{10}|\\d{12}", message = "INN must be 10 or 12 digits")
    private String inn;
}
