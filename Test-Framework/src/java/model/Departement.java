package model;
import ETU2035.framework.server.Argument;
import ETU2035.framework.server.GetUrl;

public class Departement {
    private String nom_departement;
    private int nbr_departement;
    
    @GetUrl(url="findAllDept")
    public Departement findAll(){
        Departement emp = new Departement("Departement Marketing",12);
        return emp;
    }
    @GetUrl(url="saveDept")
    public void save(){
        Departement emp = new Departement(this.getNom_departement(),this.getNbr_departement());
    }
    public Departement() {
    }
    public Departement(String nom_departement, int nbr_departement) {
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
    public void setNbr_departement(String nbr_departement) {
        this.nbr_departement = Integer.parseInt(nbr_departement);
    }
}
