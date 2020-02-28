package com.cybercube.service.impl;

import com.cybercube.model.User;
import com.cybercube.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final ObjectMapper objectMapper;
  private Jedis jedis = new Jedis();


  @Override
  @KafkaListener(topics = "users", clientIdPrefix = "json", containerFactory = "kafkaListenerContainerFactory")
  public void calculateSocialRatingScore(ConsumerRecord<Integer, User> cr, @Payload String userJSON) {
    try {
      User user = objectMapper.readValue(userJSON, User.class);
      int score = user.getAge() * cr.key();
      System.out.println(user.getFirstName() + " " + user.getLastName() + " has " + score + " score.");
      jedis.set(userJSON, String.valueOf(score));
    } catch (JsonProcessingException e) {
      String errorMsg = "Failed to parse User JSON!";
      log.error(errorMsg, e);
    }
  }
}