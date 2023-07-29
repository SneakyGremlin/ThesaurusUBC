package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestGlobalHelpers {

    GlobalHelpers globalHelpers;

    @BeforeEach
    public void initialise() {
        globalHelpers = new GlobalHelpers();
    }

    @Test
    public void testConstructor() {
        assertNotNull(globalHelpers);
    }
}
