package com.barLoyalty.gateway.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "bars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;

    @OneToOne
    @JoinColumn(name = "admin_user_id")
    private User admin;

    @OneToMany(mappedBy = "bar", cascade = CascadeType.ALL)
    private List<Reward> rewards;
}
