# social-rating-calculator-ms
Social Rating Calculator

## Technical Stack
1. Java 8
2. Spring Boot 2.2.4
3. Maven
4. Apache Kafka
5. Redis/Jedis


### Description
* read message from the kafka
* calculate social rating score (score = base seed * user’s age)
* print similar to the following message to the application console "<firstName> <lastName> has <socialRatingScore> score"
* save to Jedis ( User : score )
