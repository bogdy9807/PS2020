package com.first;

import businesslogic.ProgramariServiceBLL;
import businesslogic.ServiceBLL;
import model.ProgramariService;
import model.Service;

import java.util.ArrayList;

public class ShowPrestatorBooks implements ShowBooksStrategy{

    @Override
    public ArrayList<ProgramariService> showBooks(ProgramariServiceBLL programariServiceBLL, int id) {
        if(programariServiceBLL == null){
            return null;
        }
        ServiceBLL serviceBLL = new ServiceBLL();
        ArrayList<ProgramariService> list = new ArrayList<ProgramariService>();
        for(Service x: serviceBLL.selectByIdPrestator(id)){
            list.addAll(programariServiceBLL.selectByIdService(x.getId()));
        }
        return list;
    }
}
