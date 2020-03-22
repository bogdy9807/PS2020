package com.first;

import businesslogic.ClientBLL;
import businesslogic.PrestatorBLL;
import businesslogic.ProgramariServiceBLL;
import businesslogic.ServiceBLL;
import model.Client;
import model.Prestator;
import model.Service;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
class DemoApplication {

    private PrestatorBLL prestatorBLL;
    private ClientBLL clientBLL;
    private ProgramariServiceBLL programariServiceBLL;
    private ServiceBLL serviceBLL;

    public DemoApplication(){
        prestatorBLL = new PrestatorBLL();
        serviceBLL = new ServiceBLL();
        clientBLL = new ClientBLL();
        programariServiceBLL = new ProgramariServiceBLL();
    }


    @GetMapping("/printHello")
    public String hello() {
        return "Hello World INSFARSIT";
    }

    @GetMapping("/afiseazaClienti")
    public String showAllCustomers(){
        ClientBLL x = new ClientBLL();
        //System.out.println("Clientul:" +  x.findById(1).getNume());
        ArrayList<Client> xlist = clientBLL.selectAll();
        String s = new String();
        for(Client y: xlist) {
            s += y.getNume() + " ";
        }
        return s;
    }

    @GetMapping("/afiseazaPrestatori")
    public String showAllProviders(){
        String s = new String();
        for(Prestator x: prestatorBLL.selectAll()){
            s+=x.getNume() + " ";
        }
        return s;
    }

    @GetMapping("/afiseazaService")
    public String showServices(){
        String s = new String();
        s = "ID\t" + "prestator_id\t" + "Informatii\t" + "Nume\n";
        for(Service x: serviceBLL.selectAll()){
            s+= x.getId() + "\t" + x.getPrestator_id() + "\t" + x.getInformatii() + "\t" + x.getNume() + "\n";
        }
        return s;
    }


}