package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "subscriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ElementCollection
    @CollectionTable(name = "cctv_group_cctv_ids", joinColumns = @JoinColumn(name = "group_id"))
    @Column(name = "cctv_id")
    private List<Long> cctvIds; // CCTV ID 목록 (숫자 리스트)

    private Long managerId;  // 담당자 ID (유저 ID)

    private Boolean active;  // true = 구독중, false = 해제됨 그리고 push 테스트임
}

