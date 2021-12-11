package com.itmo.mustread.users.service

import com.itmo.mustread.users.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class SubscriptionService(val userRepository: UserRepository) {
    fun subscriberUserTo(usernameOfSubscriber: String, subscriptionUserId: Int) {

        val subscriber = userRepository.findUserByUsername(usernameOfSubscriber)
        val subscription = userRepository.findById(subscriptionUserId)

        if (subscription.isEmpty) {
            return
        }

        subscriber.subscriptions.add(subscription.get())
        userRepository.save(subscriber)
    }

    fun unsubscribeUserFrom(usernameOfSubscriber: String, subscriptionUserId: Int) {
        val subscriber = userRepository.findUserByUsername(usernameOfSubscriber)
        val subscriptionOptional = userRepository.findById(subscriptionUserId)
        if (subscriptionOptional.isEmpty) {
            return
        }
        val subscription = subscriptionOptional.get()

        if (!subscriber.subscriptions.contains(subscription)) return

        subscriber.subscriptions.remove(subscription)
        userRepository.save(subscriber)
    }
}