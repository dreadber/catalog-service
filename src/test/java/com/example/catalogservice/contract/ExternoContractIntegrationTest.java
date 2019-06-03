package com.example.catalogservice.contract;

import com.example.catalogservice.client.ExternoClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureStubRunner(
        stubsMode = StubRunnerProperties.StubsMode.LOCAL,
        ids = {"com.example:externo-service:+:stubs:6565"})
@DirtiesContext
public class ExternoContractIntegrationTest {

    @Autowired
    private ExternoClient externoClient;

    @Test
    public void testInvocacionServicioExterno() throws Exception {
        String externo = externoClient.obtenerExterno();
        assertThat(externo, equalToIgnoringCase("Mensaje Externo"));
    }

}
