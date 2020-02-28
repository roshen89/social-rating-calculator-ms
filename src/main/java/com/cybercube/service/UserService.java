package com.cybercube.service;


import com.cybercube.model.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface UserService {


  /**
   * This method is for get date from kafka and calculate score
   *
   * @param cr ConsumerRecord<Integer, User>
   * @param userJSON String
   */
  void calculateSocialRatingScore(ConsumerRecord<Integer, User> cr, String userJSON);
}
