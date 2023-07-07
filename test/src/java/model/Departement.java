package model;
import ETU2035.framework.server.Argument;
import ETU2035.framework.server.FileUpload;
import ETU2035.framework.server.GetUrl;
import ETU2035.framework.server.ModelView;
import ETU2035.framework.server.Singleton;

@Singleton(url="scope")
public class Departement {
    private String nom_departement;
    private Integer nbr_departement;
    private ETU2035.framework.server.FileUpload upload;

    public FileUpload getUpload() {
        return upload;
    }

    public void setUpload(FileUpload upload) {
        this.upload = upload;
    }
    
    @GetUrl(url="findAllDept")
    public ModelView findAll(Integer id){
        Departement emp = new Departement("Departement Marketing",12);
        ModelView view  = new ModelView(this.getClass().getSimpleName());
        view.addItem("dept", emp);
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
