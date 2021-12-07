package sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveSpCharTest {

//    @org.junit.jupiter.api.Test
//    void removeSpecialChar() {
//    }

    //unit testing
    @Test
    void test() {
        Main test = new Main();
        String output = test.removeSpecialChar("Hello!@World");
        assertEquals("Hello World", output);

    }
}