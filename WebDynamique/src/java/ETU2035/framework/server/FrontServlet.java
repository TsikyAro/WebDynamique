package ETU2035.framework.server;
import ETU2035.framework.model.Departement;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;

public class FrontServlet extends HttpServlet {
      HashMap<String,Mapping> MappingUrls;
    public void init (){
        MappingUrls = new HashMap<>();
          try {
            String directory ="C://Users//Tsiky Aro//Documents//NetBeansProjects//s4//Mr Naina//Framework//WebDynamique//Test-Framework//src//java//ETU2035//framework//model";
            String [] classe = reset(directory);
            for(int i =0 ;i< classe.length; i++){
                 String className = classe[i];
                String name = classe[i];
                className = "ETU2035.framework.model." +className;
                Class<?> clazz= Class.forName(className);
                Method [] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                     Annotation[] an = method.getAnnotations();
                     if(an.length!=0){
                         GetUrl annotation = method.getAnnotation(GetUrl.class);
                         MappingUrls.put(annotation.url(),new Mapping(name,method.getName()));
                     }
                }
            }
         } catch (Exception ex) {
              ex.printStackTrace();
         }
    }
    public String[] reset(String Directory){
        ArrayList<String> rar=new ArrayList<>();
        File dossier = new File(Directory);
        String[] contenu = dossier.list();
        for(int i=0; i<contenu.length; i++){
            String fe=FilenameUtils.getExtension(contenu[i]);
            if(fe.equalsIgnoreCase("java")){
                String [] value = contenu[i].split("[.]");
                rar.add(value[0]);
            }
        }
       return rar.toArray(new String[rar.size()]); 
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PrintWriter out = response.getWriter();
        try{
            Mapping m = MappingUrls.get(request.getRequestURI().replace(request.getContextPath()+"/",""));
            String key = request.getRequestURI().replace(request.getContextPath()+"/","");
            String name =  "ETU2035.framework.model."+m.getClassName();
            Object o =Class.forName(name).getConstructor().newInstance(null);
            Object vao = o.getClass().getMethod(m.getMethod()).invoke(o);
            out.println(name);
            if (m.getMethod().compareToIgnoreCase("findAll")==0){
                ModelView view = new ModelView(vao.getClass().getSimpleName());
                view.addItem(key, vao);
                request.setAttribute(key,view.getData());
                RequestDispatcher dispat = request.getRequestDispatcher(view.getUrl());
                dispat.forward(request, response);
            }
            else if(m.getMethod().compareToIgnoreCase("save")==0){
                
                Class<?> clazz = o.getClass();
//                out.println(clazz);
                Field[] fields = clazz.getDeclaredFields();
                Method[] listM = new Method[fields.length];
                for(int i =0; i<fields.length; i++){
                    String capitalized = Character.toUpperCase(fields[i].getName().charAt(0)) + fields[i].getName().substring(1);
                    Method temp = clazz.getDeclaredMethod("get"+ capitalized);
                    Object value = request.getParameter(fields[i].getName());
//                    fields[i].getType().cast(value);
                    listM[i] = clazz.getDeclaredMethod("set"+ capitalized,String.class);
                    listM[i].invoke(o, value);
                    out.println(temp.invoke(o, null).toString());
                }
            }
            
        }catch(Exception e){
            out.println(e);
            e.printStackTrace();
        }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
          try {
              
              processRequest(request, response);
          } catch (Exception ex) {
              Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
         
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        ArrayList<String> stringe = new ArrayList<>();
        out.println("huhu on est la");
        String classe ="ETU2035.framework.model."+request.getParameter("classe");
          try {
              Object objet = Class.forName(classe).getConstructor().newInstance() ;
              Class<?> clazz = objet.getClass();
              Field[] field = clazz.getDeclaredFields();
              for(int i =0; i<field.length; i++){
                  String value = request.getParameter(field[i].getName());
//                  out.println(field[i].getName());
                    
              }
          } catch (Exception ex) {
              out.println(ex);
              Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
         
    }
}