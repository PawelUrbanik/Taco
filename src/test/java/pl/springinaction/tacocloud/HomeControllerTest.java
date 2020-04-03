package pl.springinaction.tacocloud;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.springinaction.tacocloud.controller.DesignTacoController;

@RunWith(SpringRunner.class)
@WebMvcTest(DesignTacoController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHomepage() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/design"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("create_own"))
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("sałata")));
    }
}
