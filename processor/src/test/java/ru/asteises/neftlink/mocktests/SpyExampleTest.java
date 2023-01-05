package ru.asteises.neftlink.mocktests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

/**
 * Данные тесты отслеживают действия объектов.
 */
@RunWith(MockitoJUnitRunner.class)
public class SpyExampleTest {

    @Spy
    private List<String> strings;

    @Test
    public void spyTest() {
        strings.add("string1");
        strings.isEmpty();
        strings.clear();

        Mockito.verify(strings).add("string1");
        Mockito.verify(strings).add(Mockito.anyString());
        Mockito.verify(strings).add(Mockito.any(String.class));

        Mockito.verify(strings, Mockito.times(1)).add("string1");
        Mockito.verify(strings, Mockito.atLeast(1)).add("string1");
        Mockito.verify(strings, Mockito.atMost(3)).add("string1");
        Mockito.verify(strings, Mockito.never()).size();

        //Проверяем в каком порядке вызывались методы, очередность важна;
        InOrder inOrder = Mockito.inOrder(strings);
        inOrder.verify(strings).add("string1");
        inOrder.verify(strings).isEmpty();
        inOrder.verify(strings).clear();
    }
}
