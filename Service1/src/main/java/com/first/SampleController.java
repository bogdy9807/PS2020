package com.first;

import businesslogic.ClientBLL;
import model.Client;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
class DemoApplication {

    private ArrayList<Integer> testList;

    @GetMapping("/printList")
    public String printList(){
        testList = new ArrayList<Integer>();
        testList.add(1);
        testList.add(2);
        testList.add(3);
        return testList.toString();
    }

    @GetMapping("/printHello")
    public String hello() {
        return "Hello World INSFARSIT";
    }

    @GetMapping("/showClienti")
    public String showAllCustomers(){
        ClientBLL x = new ClientBLL();
        System.out.println("Clientul:" +  x.findById(1).getNume());
        ArrayList<Client> xlist = x.selectAll();
        String s = new String();
        for(Client y: xlist) {
            s += y.getNume() + " ";
        }
        return s;
    }
}