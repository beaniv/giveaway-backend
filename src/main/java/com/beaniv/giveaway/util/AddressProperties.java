package com.beaniv.giveaway.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AddressProperties {
    private final String domainName;
    private final int port;

    public AddressProperties(@Value("${server.domainName}") String domainName,
                             @Value("${server.port}") int port) {
        this.domainName = domainName;
        this.port = port;
    }
}