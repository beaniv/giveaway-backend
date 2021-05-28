package com.beaniv.giveaway.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class AddressPropertiesTest {

    @Autowired
    private AddressProperties addressProperties;

    @Value("${server.domainName}")
    private String domainName;

    @Value("${server.port}")
    private int port;

    @Test
    void getDomainName() {
        String domainNameFromGetter = addressProperties.getDomainName();
        assertThat(domainNameFromGetter, is(equalTo(domainName)));
    }

    @Test
    void getPort() {
        int portFromGetter = addressProperties.getPort();
        assertThat(portFromGetter, is(equalTo(port)));
    }
}
