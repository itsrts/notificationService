# Notification Service

The service exposes API to publish notifications and check status of existing notifications via unique refId.

All of the data is saved in memory but can be easily be moved to an external service. e,g, Redis and Rabbitmq.

Clients are created and saved with a subscription in the SubscriptionServiceImpl. In live scenarios the clients and the subscription will be saved by another service exposed to the admin.

The notifications are published to a queue and other consumers (separate consumers for EMAIL, SMS, PUSH) shall be responsible for actual delivery of the notifications.

The ClientAuthenticatorInterceptor checks for the client token and validity of the subscription.