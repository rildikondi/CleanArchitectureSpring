package com.akondi.GaussTask.api;


import com.akondi.GaussTask.interfaceadapters.controller.GaussSolverController;
import com.akondi.GaussTask.enterprisebusinessrules.entity.EquationSystem;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GaussSolverControllerTest {
    private double[][] a = {{2.0, 4.0}, {5.0, -6.0}};
    private double[][] wrongA = {{2.0, 4.0}};
    private double[] b = {8.0, 4.0};
    private double[] result = {2.0, 1.0};

    @Autowired
    private GaussSolverController gaussSolverController;

    private static Validator validator;

    @BeforeClass
    public static void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void testNullSolveEquationSystemParamException() {
        try {
            gaussSolverController.solveEquationSystem(null);
            fail("Wrong parameter was not captured from constraints");
        } catch (NullPointerException e) {

        }
    }

    @Test
    public void testNullParamSolveEquationSystemException() {
        try {
            double[] response = gaussSolverController.solveEquationSystem(new EquationSystem(null, null));
            fail("Wrong parameter was not captured from constraints");
        } catch (NullPointerException e) {

        }
    }

    @Test
    public void testWrongParamSolveEquationSystemException() {
        EquationSystem equationSystem = new EquationSystem(wrongA, b);

        Set<ConstraintViolation<EquationSystem>> constraintViolations = validator.validate(equationSystem);

        Assert.assertEquals("size must be between 2 and 2", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testEmptyArraysParamSolveEquationSystemException() {
        EquationSystem equationSystem = new EquationSystem(new double[][]{}, new double[]{});

        Set<ConstraintViolation<EquationSystem>> constraintViolations = validator.validate(equationSystem);

        Assert.assertTrue( constraintViolations.size() > 0);
    }

    @Test
    public void validParamsForSolveEquationSystem() {
        EquationSystem equationSystem = new EquationSystem(a, b);

        Set<ConstraintViolation<EquationSystem>> constraintViolations = validator.validate(equationSystem);

        Assert.assertEquals(0, constraintViolations.size());
    }


    @Test
    public void testReturnsRightResultForSolveEquationSystem() {
        EquationSystem equationSystem = new EquationSystem(a, b);

        double[] response = gaussSolverController.solveEquationSystem(equationSystem);

        Assert.assertEquals(Arrays.toString(result), Arrays.toString(response));
    }
}