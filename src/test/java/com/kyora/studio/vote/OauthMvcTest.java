package com.kyora.studio.vote;

import com.kyora.studio.vote.web.dto.ApplicationVersionDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OauthMvcTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void noAuth() {
        ResponseEntity<ApplicationVersionDTO> response = testRestTemplate.getForEntity("/", ApplicationVersionDTO.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
