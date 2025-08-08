package com.example.demo.repository;

import com.example.demo.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByManagerId(Long managerId);
    // 기본적인 CRUD 메서드 자동 제공됨
    // 필요 시 커스텀 쿼리 메서드 추가 가능
}
