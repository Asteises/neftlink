package ru.asteises.neftlink.mocktests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * Отлавливает переданные в метод параметры.
 */
@RunWith(MockitoJUnitRunner.class)
public class CaptorExampleTest {

    @Mock
    private List<String> strings;

    @Captor
    private ArgumentCaptor argumentCaptor;

    @Test
    public void captorTest() {
        strings.add("string1");

        Mockito.verify(strings).add(String.valueOf(argumentCaptor.capture()));

        Assert.assertEquals("string1", argumentCaptor.getValue());
    }
}
