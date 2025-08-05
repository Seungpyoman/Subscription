package com.example.demo.controller;

import com.example.demo.model.Subscription;
import com.example.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/subscriptions")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    // 1. 등록 (POST)
    @PostMapping
    public Subscription create(@RequestBody Subscription subscription) {
        // active 값 없으면 true로 기본 설정
        boolean active = subscription.isActive();
        return subscriptionService.createSubscription(
                subscription.getCctvGroupId(),
                subscription.getUserId(),
                active
        );
    }

    // 2. 전체 조회 (GET)
    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionService.getAllSubscriptions();
    }

    // 3. 단일 조회 (GET)
    @GetMapping("/{id}")
    public Subscription getOne(@PathVariable Long id) {
        return subscriptionService.getSubscription(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    // 4. 수정 (PUT)
    @PutMapping("/{id}")
    public Subscription update(@PathVariable Long id,
                               @RequestBody Subscription updated) {
        return subscriptionService.updateSubscription(
                id,
                updated.getCctvGroupId(),
                updated.getUserId(),
                updated.isActive()
        );
    }

    // 5. 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return "Subscription deleted (ID: " + id + ")";
    }
}
