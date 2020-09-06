package com.akondi.GaussTask.dao;

import org.junit.Assert;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class IdGeneratorTest {

    @Autowired
    private IdGenerator generator1;

    @Autowired
    private IdGenerator generator2;

    @Test
    public void testMultipleGeneratorsEnsureGeneratorsDoNotInterfere() throws Exception {
        Assert.assertEquals(1, generator1.getNextId());
        Assert.assertEquals(2, generator1.getNextId());
        Assert.assertEquals(1, generator2.getNextId());
        Assert.assertEquals(2, generator2.getNextId());
    }
}