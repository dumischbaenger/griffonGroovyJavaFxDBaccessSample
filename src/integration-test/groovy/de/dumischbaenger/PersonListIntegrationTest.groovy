package de.dumischbaenger

import griffon.javafx.test.GriffonTestFXRule
import org.junit.Rule
import org.junit.Test

import static org.junit.Assert.fail

class PersonListIntegrationTest {
    @Rule
    public GriffonTestFXRule testfx = new GriffonTestFXRule('mainWindow')

    @Test
    void smokeTest() {
        fail('Not implemented yet!')
    }
}
