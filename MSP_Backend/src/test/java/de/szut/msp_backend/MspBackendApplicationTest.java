package de.szut.msp_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MspBackendApplicationTest
{
    @Test
    void testContextLoads()
    {
    }

    @Test
    void testMain()
    {
        MspBackendApplication.main(new String[0]);
    }
}