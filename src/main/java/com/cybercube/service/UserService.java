package com.cybercube.service;


public interface UserService {

  /**
   * This method is for calculate user's social rating score
   * @param userJSON String
   */
  void calculateSocialRatingScore(String userJSON);
}
