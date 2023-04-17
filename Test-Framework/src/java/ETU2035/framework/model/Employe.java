package ETU2035.framework.model;

import ETU2035.framework.server.GetUrl;
public class Employe {
    
    private String name;
    private int ages ;
    
    @GetUrl(url="findAllEmp")
    public Employe findAll(){
        Employe emp = new Employe("Rotsy",20);
        return emp;
    }

    public Employe() {
    }
    
    public Employe(String name, int ages) {
        this.name = name;
        this.ages = ages;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAges() {
        return ages;
    }

    public void setAges(int ages) {
        this.ages = ages;
    }
    
    
}
