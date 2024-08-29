package com.accenture.coffeemaker;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.Assert.assertEquals;

public class DisplayTest {

    private static final String MESSAGE_PREFIX = "LCD display: ";
    private static final String TEST_MESSAGE = "Test";
    private static final String EMPTY_MESSAGE = "";

    private final PrintStream standardOut = System.out;

    private ByteArrayOutputStream outputStreamCaptor;

    private Display underTest = new Display();

    @BeforeMethod
    public void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testDisplayMessageShouldDisplayAMessageWhenMessageIsProvided() {
        underTest.displayMessage(TEST_MESSAGE);

        assertEquals(outputStreamCaptor.toString().trim(), MESSAGE_PREFIX + TEST_MESSAGE);
    }

    @Test
    public void testDisplayMessageShouldNotDisplayAnyMessageWhenMessageIsNotProvided() {
        underTest.displayMessage(EMPTY_MESSAGE);

        assertEquals(outputStreamCaptor.toString().trim(), EMPTY_MESSAGE);
    }
}