package unit;


import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

@Test
public class SimpleTest {

    @BeforeClass
    public void setUp() {
    }

    @Test(groups = { "fast" })
    public void aFastTest() {
        assertThat(1).isEqualTo(1);
    }

    @Test(groups = { "slow" })
    public void aSlowTest() {
        assertThat("Breakfast").isNotEqualTo("Cookies");
    }

}
