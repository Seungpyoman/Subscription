package com.example.demo.service;

import com.example.demo.model.Subscription;
import com.example.demo.repository.SubscriptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    // 1. 등록
    public Subscription createSubscription(Long cctvGroupId, Long userId) {
        Subscription subscription = Subscription.builder()
                .cctvGroupId(cctvGroupId)
                .userId(userId)
                .active(true)
                .build();
        return subscriptionRepository.save(subscription);
    }

    // 2. 전체 조회
    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    // 3. 단일 조회
    public Optional<Subscription> getSubscription(Long id) {
        return subscriptionRepository.findById(id);
    }

    // 4. 수정
    public Subscription updateSubscription(Long id, Long cctvGroupId, Long userId, boolean active) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));
        subscription.setCctvGroupId(cctvGroupId);
        subscription.setUserId(userId);
        subscription.setActive(active);
        return subscriptionRepository.save(subscription);
    }

    // 5. 삭제 (NPE 방지: cctvGroupId와 userId를 0으로 초기화)
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        subscription.setCctvGroupId(0L);
        subscription.setUserId(0L);
        subscription.setActive(false);
        subscriptionRepository.save(subscription);
    }
}
