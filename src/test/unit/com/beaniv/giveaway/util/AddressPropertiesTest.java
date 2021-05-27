package com.beaniv.giveaway.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class AddressPropertiesTest {
    private static final String DOMAIN_NAME_INIT = "example.com";
    private static final int PORT_INIT = 1234;

    private AddressProperties addressProperties;

    @BeforeEach
    void create() {
        addressProperties = new AddressProperties(DOMAIN_NAME_INIT, PORT_INIT);
    }

    @Test
    void getDomainName() {
        var domainName = addressProperties.getDomainName();
        assertThat(domainName, is(equalTo(DOMAIN_NAME_INIT)));
    }

    @Test
    void getPort() {
        var port = addressProperties.getPort();
        assertThat(port, is(equalTo(PORT_INIT)));
    }
}
