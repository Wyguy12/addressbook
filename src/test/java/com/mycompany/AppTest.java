package com.mycompany;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * 
     */
    @Test
    public void testContactToString() {
        Contacts contact = new Contacts("John", "Doe", "123 Elm St", "Springfield", "IL", "123-456-7890", "john.doe@example.com", "Friend from college"); assertEquals("Doe, John", contact.toString());
        // Assert that the 'toString' method does return the expected string
        assertEquals("Doe, John",  contact.toString());
    }
}