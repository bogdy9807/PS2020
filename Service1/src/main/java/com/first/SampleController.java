package com.first;

import businesslogic.*;
import model.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@SpringBootApplication
@RestController
class DemoApplication {

    private PrestatorBLL prestatorBLL;
    private ClientBLL clientBLL;
    private ProgramariServiceBLL programariServiceBLL;
    private ServiceBLL serviceBLL;
    private LoginBLL loginBLL;
    private Login login;
    private Client client;

    private ArrayList<ObserverProgramari> observers;

    public DemoApplication(){
        observers = new ArrayList<ObserverProgramari>();
        prestatorBLL = new PrestatorBLL();
        addObserver(prestatorBLL);
        serviceBLL = new ServiceBLL();
        clientBLL = new ClientBLL();
        programariServiceBLL = new ProgramariServiceBLL();
        loginBLL = new LoginBLL();
        client = clientBLL.findById(1);

    }

    public void addObserver(ObserverProgramari p){
        observers.add(p);
    }

    public void removeObserver(ObserverProgramari p){
        observers.remove(p);
    }

    public void notifyAll(ProgramariService p, int id){
        for(ObserverProgramari obs: observers){
            obs.update(p,id);
        }
    }

    @GetMapping("/printHello")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/afiseazaClienti")
    public ArrayList<Client> showAllCustomers(){
        return clientBLL.selectAll();
    }

    @GetMapping("/afiseazaPrestatori")
    public ArrayList<Prestator> showAllProviders(){
        return prestatorBLL.selectAll();
    }

    @GetMapping("/afiseazaService")
    public ArrayList<Service> showServices(){
        return serviceBLL.selectAll();
    }

    @GetMapping("/afiseazaProgramari")
    public ArrayList<ProgramariService> showAllBooks(){
        return programariServiceBLL.selectAll();
    }

    @PostMapping("/emiteCerereProgramare")
    public void emiteCerereProgramare(@RequestBody int serviceId, String data_programare, String data_creare){
        ProgramariService programareNoua = new ProgramariService(-1,serviceId,client.getId(),data_programare,data_creare,0.0f,"");
        Prestator pr = prestatorBLL.findById(serviceBLL.findById(serviceId).getPrestator_id());
        notifyAll(programareNoua, pr.getId());
    }

    @PostMapping("/confirmaProgramare")
    public void confirmaProgramare(ProgramariService p, float cost, String data){
        ArrayList<ProgramariService> programari = programariServiceBLL.selectAll();
        int maxId = -1;
        for(ProgramariService p1: programari){
            if(p1.getId()>maxId){
                maxId = p1.getId();
            }
        }
        p.setId(maxId+1);
        p.setCost_estimat(cost);
        p.setData_estimata_fin(data);
        programariServiceBLL.insert(p);
    }

    @GetMapping("/clientFindById")
    public Client clientFindById(@RequestBody int id){
        return clientBLL.findById(id);
    }

    @GetMapping("/prestatorFindById")
    public Prestator prestatorFBId(@RequestBody int id){
        return prestatorBLL.findById(id);
    }

    @GetMapping("/serviceFindById")
    public Service serviceFBId(@RequestBody int id){
        return serviceBLL.findById(id);
    }

    @GetMapping("/programariFindById")
    public ProgramariService programariFBId(@RequestBody int id){
        return programariServiceBLL.findById(id);
    }

    @GetMapping("/afiseazaLogin")
    public ArrayList<Login> lognSelectAll(){
        return loginBLL.selectAll();
    }

    @PostMapping("/insertClient")
    public void insertClient(@RequestBody Client x){
        clientBLL.insert(x);
    }

    @PostMapping("/insertPrestator")
    public void insertPrestator(@RequestBody Prestator x){
        prestatorBLL.insert(x);
    }

    @PostMapping("/insertService")
    public void insertService(@RequestBody Service x){
        serviceBLL.insert(x);
    }

    @PostMapping("/insertProgramare")
    public void insertProgramare(@RequestBody ProgramariService x){
        programariServiceBLL.insert(x);
    }

    @PostMapping("/insertLogin")
    public void insertLogin(@RequestBody Login x){
        loginBLL.insert(x);
    }

    @PostMapping("/updateClient")
    public void updateClient(@RequestBody Client x){
        if(clientBLL.findById(x.getId()) == null){
            return;
        }
        clientBLL.update(x);
    }

    @PostMapping("/updateLogin")
    public void updateLogin(@RequestBody Login x){
        if(loginBLL.findById(x.getId()) == null){
            return;
        }
        loginBLL.update(x);
    }

    @PostMapping("/updatePrestator")
    public void updatePrestator(@RequestBody Prestator x){
        if(prestatorBLL.findById(x.getId()) == null){
            return;
        }
        prestatorBLL.update(x);
    }

    @PostMapping("/updateService")
    public void updateService(@RequestBody Service x){
        if(serviceBLL.findById(x.getId()) == null){
            return;
        }
        serviceBLL.update(x);
    }

    @PostMapping("/updateProgramare")
    public void updateProgramare(@RequestBody ProgramariService x){
        if(programariServiceBLL.findById(x.getId()) == null){
            return;
        }
        programariServiceBLL.update(x);
    }

    @PostMapping("/deleteClient")
    public void deleteClient(@RequestBody int id){
        if(clientBLL.findById(id) == null){
            return;
        }
        clientBLL.deleteById(id);
    }

    @PostMapping("/deleteService")
    public void deleteService(@RequestBody int id){
        if(serviceBLL.findById(id) == null){
            return;
        }
        serviceBLL.deleteById(id);
    }

    @PostMapping("/deletePrestator")
    public void deletePrestator(@RequestBody int id){
        if(prestatorBLL.findById(id) == null){
            return;
        }
        prestatorBLL.deleteById(id);
    }

    @PostMapping("/deleteProgramare")
    public void deleteProgramare(@RequestBody int id){
        if(programariServiceBLL.findById(id) == null){
            return;
        }
        programariServiceBLL.deleteById(id);
    }


}