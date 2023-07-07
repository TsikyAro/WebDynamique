package ETU2035.framework.server;

import java.util.HashMap;

public class ModelView {
    String url;
    HashMap<String, Object> data = new HashMap<>(); 
    HashMap<String,Object>  session = new HashMap<>();

    public HashMap<String, Object> getSession() {
        return session;
    }

    public void addSession(String key,Object valeur) {
        session.put(key, valeur);
    }

    public Boolean getIsJson() {
        return isJson;
    }

    public void setIsJson(Boolean isJson) {
        this.isJson = isJson;
    }
   Boolean isJson = false;

    public Boolean GetIsJson(){
        return this.isJson;
    }
    public void SetIsJson(Boolean json){
        this.isJson = json;
    }
    public void addItem(String key, Object objet) {
        if (data == null) { 
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
        this.url = "/"+url+".jsp";
    }

    public ModelView(String url){
        setUrl(url);
    }

}
