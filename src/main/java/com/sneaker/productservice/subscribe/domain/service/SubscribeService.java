package com.sneaker.productservice.subscribe.domain.service;

import com.sneaker.productservice.config.MailService;
import com.sneaker.productservice.subscribe.domain.entity.SubscribeEntity;
import com.sneaker.productservice.subscribe.domain.entity.repository.SubscribeRepository;
import com.sneaker.productservice.subscribe.rest.dto.SubscribeRequest;
import org.springframework.stereotype.Service;

@Service
public class SubscribeService {
    private final SubscribeRepository repository;
    private final MailService mailService;

    private static final String TEXT_MESSAGE = """
            Dear %s,
                        
            Welcome to the Sneaker Shop community!
                        
            We are thrilled to have you on board!
            """;

    public SubscribeService(SubscribeRepository repository, MailService mailService) {
        this.repository = repository;
        this.mailService = mailService;
    }

    public SubscribeEntity addSubscriber(SubscribeRequest request) {
        SubscribeEntity subscriber = new SubscribeEntity();
        subscriber.setEmail(request.getEmail());
        subscriber.setName(request.getName());
        mailService.send(request.getEmail(), String.format(TEXT_MESSAGE, request.getName()));
        return repository.save(subscriber);
    }
}
