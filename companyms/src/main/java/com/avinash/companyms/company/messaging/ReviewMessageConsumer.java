package com.avinash.companyms.company.messaging;

import com.avinash.companyms.company.CompanyService;
import com.avinash.companyms.company.dto.ReviewMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    CompanyService companyService;

   /* or
    private final RabbitTemplate rabbitTemplate;
    public ReviewMessageProducer(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate=rabbitTemplate;
    }
    */

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewMessage){
        companyService.updateCompanyRating(reviewMessage);
    }
}
