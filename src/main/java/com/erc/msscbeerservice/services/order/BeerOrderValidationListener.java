package com.erc.msscbeerservice.services.order;

import com.erc.msscbeerservice.config.JmsConfig;
import com.erc.msscbeerservice.events.ValidateOrderRequest;
import com.erc.msscbeerservice.events.ValidateOrderResult;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class BeerOrderValidationListener {

    private final BeerOrderValidator validator;
    private final JmsTemplate jmsTemplate;

    public BeerOrderValidationListener(BeerOrderValidator validator, JmsTemplate jmsTemplate) {
        this.validator = validator;
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void listen(ValidateOrderRequest validateOrderRequest) {
        Boolean isValid = validator.validateOrder(validateOrderRequest.getBeerOrder());
        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                new ValidateOrderResult(validateOrderRequest.getBeerOrder().getId(), isValid));
    }

}
