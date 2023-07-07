package model;
import ETU2035.framework.server.Argument;
import ETU2035.framework.server.AuthAnnotation;
import ETU2035.framework.server.FileUpload;
import ETU2035.framework.server.GetUrl;
import ETU2035.framework.server.ModelView;
import ETU2035.framework.server.RestApi;
import ETU2035.framework.server.Session;
import ETU2035.framework.server.Singleton;
import java.util.HashMap;

@Singleton(url="scope")
public class Departement {
    private String nom_departement;
    private Integer nbr_departement;
    private ETU2035.framework.server.FileUpload upload;
        
    HashMap<String, Object> session;

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void setSession(HashMap<String, Object> session) {
        this.session = session;
    }
    
    
    
    public FileUpload getUpload() {
        return upload;
    }

    public void setUpload(FileUpload upload) {
        this.upload = upload;
    }
    @RestApi(url="resteapi")
    public Departement select(){
       Departement emp = new Departement("Departement Marketing",12);
       return emp;
    }
    @Session
    @AuthAnnotation(url="admin")
    @RestApi(url="resteapi")
    @GetUrl(url="findAllDept")
    public ModelView findAll(Integer id){
        Departement emp = new Departement("Departement Marketing",12);
        ModelView view  = new ModelView(this.getClass().getSimpleName());
        System.out.println(this.getSession());
        view.addItem("dept", emp);
        return view;
    }
    
    @GetUrl( url = "login" )
    public ModelView login(){
        ModelView view = new ModelView("index");
        view.addSession("isconnected", true);
        view.addSession("profil", "admin");
        return view;
    }
    
    
    @GetUrl(url="saveDept")
    public void save(){
        Departement emp = new Departement(this.getNom_departement(),this.getNbr_departement());
    }
    public Departement() {
    }
    public Departement(String nom_departement, Integer nbr_departement) {
        this.nom_departement = nom_departement;
        this.nbr_departement = nbr_departement;
    }
    
    public String getNom_departement() {
        return nom_departement;
    }

    @Argument(arg="nom_departement")
    public void setNom_departement(String nom_departement) {
        this.nom_departement = nom_departement;
    }

    public int getNbr_departement() {
        return nbr_departement;
    }
    
    @Argument(arg="nbr_departement")
    public void setNbr_departement(Integer nbr_departement) {
        this.nbr_departement = nbr_departement;
    }
}
