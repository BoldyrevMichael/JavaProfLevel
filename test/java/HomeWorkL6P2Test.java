import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.javaproflevel.lesson6.HomeWorkL6P2;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class HomeWorkL6P2Test {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{

                        {new boolean[]{true}, new int[]{1, 1, 1, 1, 1, 1, 1}},
                        {new boolean[]{true}, new int[]{4, 4, 4, 4, 4, 4, 4}},
                        {new boolean[]{true}, new int[]{1, 4, 1, 4, 1, 4, 1}},
                        {new boolean[]{false}, new int[]{0, 2, 3, 5, 6, 7, 8}},
                        {new boolean[]{false}, new int[]{4, 1, 4, 1, 4, 1, 0}}
                }
        );
    }

    private int[] arrayIn;
    private boolean[] arrayExp;

    public HomeWorkL6P2Test(boolean[] arrayExp, int[] arrayIn) {
        this.arrayIn = arrayIn;
        this.arrayExp = arrayExp;
    }

    HomeWorkL6P2 homeWorkL6P2;

    @Before
    public void init() {
        homeWorkL6P2 = new HomeWorkL6P2();
    }

    @Test
    public void massTestCheckArray() {
        Assert.assertArrayEquals(arrayExp, new boolean[]{homeWorkL6P2.checkArrayFor1and4(arrayIn)});
    }
}
