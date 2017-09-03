import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.javaproflevel.lesson6.HomeWorkL6P1;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)

public class HomeWorkL6P1Test {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[][]{

                        {new int[]{0, 0, 0, 0, 0, 0}, new int[]{4, 0, 0, 0, 0, 0, 0}},
                        {new int[]{}, new int[]{0, 0, 0, 0, 0, 0, 4}},
                        {new int[]{0, 0, 0, 0}, new int[]{0, 0, 4, 0, 0, 0, 0}},
                        {new int[]{0, 0}, new int[]{0, 0, 0, 0, 4, 0, 0}}
                }
        );
    }

    private int[] arrayIn;
    private int[] arrayExp;

    public HomeWorkL6P1Test(int[] arrayExp, int[] arrayIn) {
        this.arrayIn = arrayIn;
        this.arrayExp = arrayExp;
    }

    HomeWorkL6P1 homeWorkL6P1;

    @Before
    public void init() {
        homeWorkL6P1 = new HomeWorkL6P1();
    }


//    как сделать так, чтобы тест на появление RuntimeException выполнялся один раз?
    @Test(expected = RuntimeException.class)
    public void testBadArray() {
        Assert.assertArrayEquals(new int[]{0, 0}, homeWorkL6P1.copyAfter4(new int[]{0, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void massTestCopyAfter4() {
        Assert.assertArrayEquals(arrayExp, homeWorkL6P1.copyAfter4(arrayIn));
    }
}


