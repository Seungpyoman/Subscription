package com.example.demo.controller;

import com.example.demo.model.Subscription;
import com.example.demo.service.SubscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")  // ✅ React 개발 서버 허용
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


    // 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        subscriptionService.deleteSubscription(id);
        return "Subscription logically deleted (ID reset to 0)";
    }
}
