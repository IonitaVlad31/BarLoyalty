package com.barLoyalty.gateway.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rewards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Column(nullable = false)
    private Integer costPoints;

    // Relatie: Multe recompense pot apartine unui singur bar
    @ManyToOne
    @JoinColumn(name = "bar_id", nullable = false)
    private Bar bar;
}
