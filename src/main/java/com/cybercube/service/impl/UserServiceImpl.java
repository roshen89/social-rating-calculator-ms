package com.cybercube.service.impl;

import com.cybercube.model.User;
import com.cybercube.repository.UserRepository;
import com.cybercube.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final ObjectMapper objectMapper;


  @Override
  @KafkaListener(topics = "users", groupId = "group_user")
  public void calculateSocialRatingScore(String userJSON) {
    log.info("Consumed message of user: " + userJSON);
    try {
      if (!userJSON.isEmpty()) {
        User user = objectMapper.readValue(userJSON, User.class);
        int score = user.getAge();
        System.out.println(user.getFirstName() + " " + user.getLastName() + " has " + score + " score.");
        userRepository.save(user);
      }
    } catch (JsonProcessingException e) {
      String errorMsg = "Failed to parse User JSON!";
      log.error(errorMsg, e);
      throw new UnsupportedOperationException(e.getMessage());
    }
  }
}