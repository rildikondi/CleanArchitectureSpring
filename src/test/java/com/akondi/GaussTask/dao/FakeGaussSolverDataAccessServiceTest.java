package com.akondi.GaussTask.dao;

import com.akondi.GaussTask.model.EquationSystem;
import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FakeGaussSolverDataAccessServiceTest {
    private double[][] a = {{2.0, 4.0}, {5.0, -6.0}};
    private double[] b = {8.0, 4.0};
    private double[] result = {2.0, 1.0};

    @Autowired
    FakeGaussSolverDataAccessService dataAccessService;

    @Before
    public void setUp() {
        dataAccessService.selectAllGaussSolverRequests().clear();
    }

    @Test
    public void testRightResultCalculated() {
        double[] soulution = dataAccessService.solve(new EquationSystem(a, b));
        Assert.assertEquals(Arrays.deepToString(new double[][]{result}), Arrays.deepToString(new double[][]{soulution}));
        Assert.assertEquals(1, dataAccessService.selectAllGaussSolverRequests().size());
    }
}