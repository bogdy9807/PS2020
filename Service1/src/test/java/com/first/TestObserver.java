package com.first;

import businesslogic.PrestatorBLL;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestObserver {

    @Test
    public void testObs(){
        DemoApplication demoApplication = new DemoApplication();
        PrestatorBLL prestatorBLL = new PrestatorBLL();
        demoApplication.addObserver(prestatorBLL);
        demoApplication.emiteCerereProgramare(2,"Maine","Azi");
        assertEquals(prestatorBLL.getProgramariNeconfirmateByPrestatorId(2).get(0).getData_programare(), "Maine");
    }

}
