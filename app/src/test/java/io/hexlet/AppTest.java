/*
 * This source file was generated by the Gradle 'init' task
 */
package io.hexlet;

import io.hexlet.spring.Application;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        Application classUnderTest = new Application();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}