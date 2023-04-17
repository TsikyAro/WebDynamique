package ETU2035.framework.server;

import java.util.HashMap;

public class ModelView {
    String url;
  HashMap<String, Object> data = new HashMap<>(); // initialisez la variable "data" avant d'utiliser la méthode

    public void addItem(String key, Object objet) {
        if (data == null) { // vérifiez si "data" est null au lieu de vérifier sa taille
            data = new HashMap<>();
        }
        data.put(key, objet);
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ModelView(String url){
        this.url = url;
    }

}
