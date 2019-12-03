package zone.refactor.spring.hateoas;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import zone.refactor.spring.hateoas.config.RefactorZoneHateoasAutoConfiguration;
import zone.refactor.spring.hateoas.controller.BlogPostController;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BlogPostController.class)
@Import(RefactorZoneHateoasAutoConfiguration.class)
public class FullStackTest {
    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetPost() throws Exception {
        mvc
            .perform(get("/posts/1"))
            .andExpect(status().isOk())
            .andExpect(
                content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            )
            .andExpect(
                jsonPath("$.title", is("Hello world!"))
            );

        mvc
            .perform(get("/posts/2"))
            .andExpect(status().isOk())
            .andExpect(
                content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            )
            .andExpect(
                jsonPath("$.title", is("My second post"))
            );

        mvc
            .perform(get("/posts/3"))
            .andExpect(status().is4xxClientError())
            .andExpect(
                header().string("X-Response-Type", is("NotFoundException"))
            )
            .andExpect(
                content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            );
    }

    @Test
    public void testListPosts() throws Exception {
        mvc
            .perform(get("/posts"))
            .andExpect(status().isOk())
            .andExpect(
                content()
                    .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)
            )

            .andExpect(
                jsonPath("$._embedded.posts[0].title", is("Hello world!"))
            )
            .andExpect(
                jsonPath("$._embedded.posts[1].title", is("My second post"))
            )
        ;
    }
}
