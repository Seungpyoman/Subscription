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
    public Subscription create(Subscription subscription) {
        // null 방지: active가 null이면 true로 설정
        if (subscription.getActive() == null) {
            subscription.setActive(true);
        }
        return subscriptionRepository.save(subscription);
    }

    // 2. 전체 조회
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    // 3. 단일 조회
    public Optional<Subscription> getById(Long id) {
        return subscriptionRepository.findById(id);
    }

    // 4. 수정
    public Subscription update(Long id, Subscription updated) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found"));

        subscription.setName(updated.getName());
        subscription.setDescription(updated.getDescription());
        subscription.setCctvIds(updated.getCctvIds());
        subscription.setManagerId(updated.getManagerId());
        subscription.setActive(updated.getActive());

        return subscriptionRepository.save(subscription);
    }

    // 5. 삭제
    public void delete(Long id) {
        if (!subscriptionRepository.existsById(id)) {
            throw new IllegalArgumentException("Subscription not found");
        }
        subscriptionRepository.deleteById(id);
    }
}
