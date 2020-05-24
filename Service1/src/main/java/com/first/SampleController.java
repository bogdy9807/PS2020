package com.first;

import businesslogic.*;
import model.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
    private Prestator prestator;
    private int userID;
    private Client registerClient;
    private Prestator registerPrestator;
    private String currentUserName;
    private ArrayList<ProgramariService> programariInAsteptare;
    private ArrayList<String> informatiiProgramari;

    public ShowBooksStrategy getShowBooksStrategy() {
        return showBooksStrategy;
    }

    public void setShowBooksStrategy(ShowBooksStrategy showBooksStrategy) {
        this.showBooksStrategy = showBooksStrategy;
    }

    private ShowBooksStrategy showBooksStrategy;

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
        programariInAsteptare = new ArrayList<ProgramariService>();
        informatiiProgramari = new ArrayList<String>();
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

    @CrossOrigin(origins="*")
    @GetMapping("/printHello")
    public String hello() {
        return "Hello World";
    }

    @CrossOrigin(origins="*")
    @GetMapping("/afiseazaClienti")
    public ArrayList<Client> showAllCustomers(){
        return clientBLL.selectAll();
    }

    @CrossOrigin(origins="*")
    @GetMapping("/afiseazaPrestatori")
    public ArrayList<Prestator> showAllProviders(){
        return prestatorBLL.selectAll();
    }

    @CrossOrigin(origins="*")
    @GetMapping("/afiseazaService")
    public ArrayList<Service> showServices(){
        return serviceBLL.selectAll();
    }

    @CrossOrigin(origins="*")
    @GetMapping("/afiseazaProgramari")
    public ArrayList<ProgramariService> showAllBooks(){
        return programariServiceBLL.selectAll();
    }

    @CrossOrigin(origins="*")
    @PostMapping("/emiteCerereProgramare")
    public int emiteCerereProgramare(@RequestParam(value="serviceId") int serviceId,@RequestParam(value="data_prog")  String data_programare, @RequestParam(value="info") String informatii){
        if(serviceBLL.findById(serviceId) == null){
            return -1;
        }
        informatiiProgramari.add(informatii);
        String data_creare;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        data_creare = formatter.format(date);
        int maxId = -1;
        for(ProgramariService p1: programariInAsteptare){
            if(p1.getId()>maxId){
                maxId = p1.getId();
            }
        }
        ProgramariService programareNoua = new ProgramariService(maxId+1,serviceId,client.getId(),data_programare,data_creare,0.0f,"");
        programariInAsteptare.add(programareNoua);
        Prestator pr = prestatorBLL.findById(serviceBLL.findById(serviceId).getPrestator_id());
        notifyAll(programareNoua, pr.getId());
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/confirmaProgramare")
    public int confirmaProgramare(@RequestParam(value = "id") int id,@RequestParam(value="cost") float cost,@RequestParam(value="data_estim_fin") String data_estim_fin){
        ProgramariService p = new ProgramariService();
        int ok = 0;
        for(Iterator<ProgramariService> i = programariInAsteptare.iterator();i.hasNext();){
            ProgramariService x = i.next();
            if(x.getId() == id){
                p = x;
                ok = 1;
            }
        }
        if(ok == 0){
            return -1;
        }
        programariInAsteptare.remove(p);
        ArrayList<ProgramariService> programari = programariServiceBLL.selectAll();
        int maxId = -1;
        for(ProgramariService p1: programari){
            if(p1.getId()>maxId){
                maxId = p1.getId();
            }
        }
        informatiiProgramari.remove(informatiiProgramari.get(p.getId()));
        p.setId(maxId+1);
        p.setCost_estimat(cost);
        p.setData_estimata_fin(data_estim_fin);
        programariServiceBLL.insert(p);
        return 0;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/clientFindById")
    public Client clientFindById(@RequestBody int id){
        return clientBLL.findById(id);
    }

    @CrossOrigin(origins="*")
    @GetMapping("/prestatorFindById")
    public Prestator prestatorFBId(@RequestBody int id){
        return prestatorBLL.findById(id);
    }

    @CrossOrigin(origins="*")
    @GetMapping("/serviceFindById")
    public Service serviceFBId(@RequestBody int id){
        return serviceBLL.findById(id);
    }

    @CrossOrigin(origins="*")
    @GetMapping("/programariFindById")
    public ProgramariService programariFBId(@RequestBody int id){
        return programariServiceBLL.findById(id);
    }

    @CrossOrigin(origins="*")
    @GetMapping("/afiseazaLogin")
    public ArrayList<Login> lognSelectAll(){
        return loginBLL.selectAll();
    }

    @CrossOrigin(origins="*")
    @PostMapping("/insertClient")
    public void insertClient(@RequestBody Client x){
        clientBLL.insert(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/insertPrestator")
    public void insertPrestator(@RequestBody Prestator x){
        prestatorBLL.insert(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/insertProgramare")
    public void insertProgramare(@RequestBody ProgramariService x){
        programariServiceBLL.insert(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/insertService")
    public void insertService(@RequestParam(value = "nume") String nume, @RequestParam(value = "info") String infoServ){
        ArrayList<Service> services = serviceBLL.selectAll();
        int maxId = 0;
        for(Service serv: services) {
            if(serv.getId() > maxId) {
                maxId = serv.getId();
            }
        }
        Service newServ = new Service(maxId + 1, prestator.getId(), infoServ, nume);
        serviceBLL.insert(newServ);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/insertLogin")
    public void insertLogin(@RequestBody Login x){
        loginBLL.insert(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/updateClient")
    public void updateClient(@RequestBody Client x){
        if(clientBLL.findById(x.getId()) == null){
            return;
        }
        clientBLL.update(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/updateLogin")
    public void updateLogin(@RequestBody Login x){
        if(loginBLL.findById(x.getId()) == null){
            return;
        }
        loginBLL.update(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/updatePrestator")
    public void updatePrestator(@RequestBody Prestator x){
        if(prestatorBLL.findById(x.getId()) == null){
            return;
        }
        prestatorBLL.update(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/updateService")
    public void updateService(@RequestBody Service x){
        if(serviceBLL.findById(x.getId()) == null){
            return;
        }
        serviceBLL.update(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/updateProgramare")
    public void updateProgramare(@RequestBody ProgramariService x){
        if(programariServiceBLL.findById(x.getId()) == null){
            return;
        }
        programariServiceBLL.update(x);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deleteClient")
    public int deleteClient(@RequestParam(value = "id_client") int id){
        if(clientBLL.findById(id) == null){
            return -1;
        }
        clientBLL.deleteById(id);
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deleteLogin")
    public int deleteLogin(@RequestParam(value = "id_login") int id){
        if(loginBLL.findById(id) == null){
            return -1;
        }
        loginBLL.deleteById(id);
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deleteService")
    public int deleteService(@RequestParam(value = "id_service") int id){
        if(serviceBLL.findById(id) == null){
            return -1;
        }
        serviceBLL.deleteById(id);
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deletePrestator")
    public int deletePrestator(@RequestParam(value = "id_prestator") int id){
        if(prestatorBLL.findById(id) == null){
            return -1;
        }
        prestatorBLL.deleteById(id);
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deleteProgramare")
    public int deleteProgramare(@RequestParam(value = "id") int id){
        if(programariServiceBLL.findById(id) == null){
            return -1;
        }
        programariServiceBLL.deleteById(id);
        return 0;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/login")
    public int login(@RequestParam(value="username") String username,@RequestParam(value="password") String password){
        if(username.compareTo("admin") == 0 && password.compareTo("admin") == 0){
            return 0;
        }
        ArrayList<Login> loginData = loginBLL.selectAll();
        for(Login x: loginData){
            if(username.compareTo(x.getUsername()) == 0 && password.compareTo(x.getPassword()) == 0) {
                if (x.getUsertype() == 1) {
                    userID = x.getId_client();
                    client = clientBLL.findById(userID);
                    currentUserName = client.getNume();
                    showBooksStrategy = new ShowClientBooks();
                    return 1;
                } else {
                    if (x.getUsertype() == 2) {
                        userID = x.getId_prestator();
                        prestator = prestatorBLL.findById(userID);
                        currentUserName = prestator.getNume();
                        showBooksStrategy = new ShowPrestatorBooks();
                        return 2;
                    }
                }
            }
        }
        return -1;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/getCurrentUserName")
    public String getCurrentUserName(){
        return currentUserName;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/showBooks")
    public ArrayList<ProgramariService> showBooks(){
        if(showBooksStrategy == null){
            return null;
        }
        return showBooksStrategy.showBooks(programariServiceBLL,userID);
    }

    @CrossOrigin(origins="*")
    @PostMapping("/register")
    public int register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "inputUserType") int usertype, @RequestParam(value = "email") String email, @RequestParam(value = "nume") String nume){
        Login x = new Login();
        x.setPassword(password);
        x.setUsername(username);
        x.setUsertype(usertype);
        int maxID = -1;
        for(Login y: loginBLL.selectAll()){
            if(y.getId() > maxID){
                maxID = y.getId();
                if(x.getUsername().compareTo(y.getUsername()) == 0){
                    return -1;
                }
            }
        }
        x.setId(maxID+1);
        if(x.getUsertype() == 1){
            Client c = new Client();
            c.setNume(nume);
            c.setEmail(email);
            if(registerClient(c) == -1){
                return -2;
            }
            clientBLL.insert(registerClient);
            x.setId_client(registerClient.getId());
            x.setId_prestator(0);
            loginBLL.insert(x);
            return 0;
        }
        else{
            Prestator p = new Prestator();
            p.setNume(nume);
            p.setEmail(email);
            if(x.getUsertype() == 2){
                if(registerPrestator(p) == -1){
                    return -2;
                }
                prestatorBLL.insert(registerPrestator);
                x.setId_prestator(registerPrestator.getId());
                x.setId_client(0);
                loginBLL.insert(x);
                return 0;
            }
        }
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/registerClient")
    public int registerClient(@RequestBody Client x){
        String data_creare;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        data_creare = formatter.format(date);
        x.setDatainreg(data_creare);
        int maxID = -1;
        for(Client y: clientBLL.selectAll()){
            if(y.getId() > maxID){
                maxID = y.getId();
                if(x.getEmail().compareTo(y.getEmail()) == 0){
                    return -1;
                }
            }
        }
        x.setId(maxID+1);
        registerClient = x;
        return 0;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/registerPrestator")
    public int registerPrestator(@RequestBody Prestator x){
        String data_creare;
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        data_creare = formatter.format(date);
        x.setData_inreg(data_creare);
        int maxID = -1;
        for(Prestator y: prestatorBLL.selectAll()){
            if(y.getId() > maxID){
                maxID = y.getId();
                if(x.getEmail().compareTo(y.getEmail()) == 0){
                    return -1;
                }
            }
        }
        x.setId(maxID+1);
        registerPrestator = x;
        return 0;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/showWaitingBooks")
    public ArrayList<ProgramariService> showWaitingBooks(){
        ArrayList<ProgramariService> result = new ArrayList<ProgramariService>();
        for(ProgramariService x : programariInAsteptare) {
            Prestator pr = prestatorBLL.findById(serviceBLL.findById(x.getService_id()).getPrestator_id());
            if (pr.getId() == userID) {
                result.add(x);
            }
        }
        return result;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/showWaitingBooksInfo")
    public ArrayList<String> showWaitingBooksInfo(){
        ArrayList<String> result = new ArrayList<String>();
        for(ProgramariService x : programariInAsteptare) {
            Prestator pr = prestatorBLL.findById(serviceBLL.findById(x.getService_id()).getPrestator_id());
            if (pr.getId() == userID) {
                result.add(informatiiProgramari.get(x.getId()));
            }
        }
        return result;
    }

    @CrossOrigin(origins="*")
    @GetMapping("/showServicePrestator")
    public ArrayList<Service> showServicePrestator(){
        ArrayList<Service> result = new ArrayList<Service>();
        for(Service x: serviceBLL.selectAll()){
            if(x.getPrestator_id() == userID){
                result.add(x);
            }
        }
        return result;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deleteServiceByPrestator")
    public int deleteServiceByPrestator(@RequestParam(value = "id_deleteservice") int id){
        Service x = serviceBLL.findById(id);
        if(x == null){
            return -1;
        }
        if(x.getPrestator_id() == userID){
            serviceBLL.deleteById(id);
            return 0;
        }
        return -1;
    }

    @CrossOrigin(origins="*")
    @PostMapping("/deleteBookByPrestator")
    public int deleteBookByPrestator(@RequestParam(value = "id_deletebook") int id){
        ProgramariService x = programariServiceBLL.findById(id);
        if(x == null){
            return -1;
        }
        if(serviceBLL.findById(x.getService_id()).getPrestator_id() == userID){
            programariServiceBLL.deleteById(id);
            return 0;
        }
        return -1;
    }
}