package es.deusto.ptsi;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.After	;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class CityWeatherWebAppTest {
    private WebTarget target;
 
    @Before
    public void setUp() throws Exception {
        Client c = ClientBuilder.newClient();
        target = c.target("http://localhost:8080/CityWeatherWebApp/webapi");
    }
 
    @After
    public void tearDown() throws Exception {
        //
    }
 
    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        String responseMsg = target.path("/roman/I").request().get(String.class);
        assertEquals("<?xml version=\"1.0\"?><message>1</message>", responseMsg);
    }
}
