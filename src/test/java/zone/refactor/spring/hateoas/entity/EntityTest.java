package zone.refactor.spring.hateoas.entity;

import io.swagger.annotations.ApiModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EntityTest {
    @Test
    public void testType() {
        final TestEntity entity = new TestEntity();
        assertEquals("Test", entity.getType());
    }

    @SuppressWarnings({"WeakerAccess"})
    @ApiModel("Test")
    public static class TestEntity extends Entity {

    }
}
