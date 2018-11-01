package com.max.organization.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(OutboundOrgChangesCustomChannel.class)
public class OrganizationChangePublisher {

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationChangePublisher.class);

    private final OutboundOrgChangesCustomChannel source;

    @Autowired
    public OrganizationChangePublisher(OutboundOrgChangesCustomChannel source) {
        this.source = source;
    }

    public void publishOrgChange(String action, String orgId) {
        LOG.info("Sending Kafka message {} for Organization Id: {}", action, orgId);

        OrganizationChangeEvent change = new OrganizationChangeEvent(
                OrganizationChangeEvent.class.getTypeName(),
                action,
                orgId,
                "<undefined inside OrganizationChangePublisher>");

        source.orgChannel().send(MessageBuilder.withPayload(change).build());
    }

}
