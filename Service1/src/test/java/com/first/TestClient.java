package com.first;

import businesslogic.ClientBLL;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

public class TestClient {
    @Mock
    ClientBLL clientBLL = new ClientBLL();
    public MockitoRule rule = MockitoJUnit.rule();
    @Rule
}
