package com.geekstylecn.amqp.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Validated
public class MessageController {
	
	private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

	@PostMapping("/{message}")
	public ResponseEntity<?> sendMessage(@PathVariable String message) {
		rabbitTemplate.convertAndSend("spring-boot-exchange", "foo.bar.baz", message);
		System.out.println("message sent: " + message);
		return ResponseEntity.status(HttpStatus.OK).body(null);
	}
	
}