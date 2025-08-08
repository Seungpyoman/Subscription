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
        // active 기본값 처리
        if (subscription.getActive() == null) {
            subscription.setActive(true); // 기본값 true
        }
        return subscriptionService.create(subscription);
    }

    // 2. 전체 조회 (GET)
    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionService.getAll();
    }

    // 3. 단일 조회 (GET)
    @GetMapping("/{id}")
    public Subscription getOne(@PathVariable Long id) {
        return subscriptionService.getById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found (ID: " + id + ")"));
    }

    // 4. 수정 (PUT)
    @PutMapping("/{id}")
    public Subscription update(@PathVariable Long id, @RequestBody Subscription updated) {
        return subscriptionService.update(id, updated);
    }

    // 5. 삭제 (DELETE)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        subscriptionService.delete(id);
        return "Subscription deleted (ID: " + id + ")";
    }
}
