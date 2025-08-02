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

    // 등록 (POST)
    @PostMapping
    public Subscription create(@RequestBody Subscription subscription) {
        return subscriptionService.createSubscription(
                subscription.getCctvGroupId(),
                subscription.getUserId()
        );
    }

    // 전체 조회 (GET)
    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionService.getAllSubscriptions();
    }

    // 단일 조회 (GET)
    @GetMapping("/{id}")
    public Subscription getOne(@PathVariable Long id) {
        return subscriptionService.getSubscription(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
    }

    // 수정 (PUT)
    @PutMapping("/{id}")
    public Subscription update(@PathVariable Long id,
                               @RequestParam Long cctvGroupId,
                               @RequestParam Long userId,
                               @RequestParam boolean active) {
        return subscriptionService.updateSubscription(id, cctvGroupId, userId, active);
    }

    // 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return "Subscription logically deleted (ID reset to 0)";
    }
}
