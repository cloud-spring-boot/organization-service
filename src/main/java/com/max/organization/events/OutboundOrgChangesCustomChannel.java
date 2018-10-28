package com.max.organization.events;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface OutboundOrgChangesCustomChannel {

    @Output("outputOrgChanges")
    MessageChannel orgChannel();
}
