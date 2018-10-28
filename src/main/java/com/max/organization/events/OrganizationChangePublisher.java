package com.max.organization.events;

import com.max.correlation.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class OrganizationChangePublisher {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationChangePublisher.class);

    private final Source source;

    @Autowired
    public OrganizationChangePublisher(Source source) {
        this.source = source;
    }

    public void publishOrgChange(String action, String orgId) {
        LOG.info("Sending Kafka message {} for Organization Id: {}", action, orgId);

        OrganizationChangeEvent change = new OrganizationChangeEvent(
                OrganizationChangeEvent.class.getTypeName(),
                action,
                orgId,
                UserContextHolder.getUserContext().getCorrelationId());

        source.output().send(MessageBuilder.withPayload(change).build());
    }

}
