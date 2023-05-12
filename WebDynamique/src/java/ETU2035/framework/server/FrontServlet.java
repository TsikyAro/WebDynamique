package ETU2035.framework.server;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
            String directory =getServletContext().getRealPath("\\WEB-INF\\classes\\model");
            String [] classe = reset(directory);
            for(int i =0 ;i< classe.length; i++){
                 String className = classe[i];
                String name = classe[i];
                className = "model." +className;
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
            String fe = FilenameUtils.getExtension(contenu[i]);
            if(fe.equalsIgnoreCase("class")){
                String [] value = contenu[i].split("[.]");
                rar.add(value[0]);
            }
        }
       return rar.toArray(new String[rar.size()]); 
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        PrintWriter out = response.getWriter();
        out.println(MappingUrls.size());
        try{
            Mapping m = MappingUrls.get(request.getRequestURI().replace(request.getContextPath()+"/",""));
            String key = request.getRequestURI().replace(request.getContextPath()+"/","");
            String name =  "model." + m.getClassName();
            Object o =Class.forName(name).getConstructor().newInstance(null);
            Method[] methods = o.getClass().getMethods();
            Method mets = null;
            for(int i =0; i<methods.length; i++){
                if(methods[i].getName().equalsIgnoreCase(m.getMethod())){
                    mets = methods[i];
                    break;
                }
            }
            Parameter [] parametre = mets.getParameters();
            Object [] objet = new Object[parametre.length];
            for(int i = 0; i<parametre.length; i++){
                Object value = request.getParameter(parametre[i].getName());
                objet[i] = value;
            }
            Object vao = mets.invoke(o, objet);
            int paramCount = mets.getParameterCount();
            action(m.getMethod(),key,vao,o,request,response);
        }catch(Exception e){
            out.println(e);
            e.printStackTrace();
        }
        
    }
    public void action(String action,String key,Object vao,Object o,HttpServletRequest request, HttpServletResponse response) throws Exception{
                PrintWriter out = response.getWriter();
        if (action.compareToIgnoreCase("findAll")==0){
                ModelView view = new ModelView(vao.getClass().getSimpleName());
                view.addItem(key, vao);
                request.setAttribute(key,view.getData());
                RequestDispatcher dispat = request.getRequestDispatcher(view.getUrl());
                dispat.forward(request, response);
        }
        else if(action.compareToIgnoreCase("save")==0){

            Class<?> clazz = o.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Method[] listM = new Method[fields.length];
            for(int i =0; i<fields.length; i++){
                String capitalized = Character.toUpperCase(fields[i].getName().charAt(0)) + fields[i].getName().substring(1);
//                out.println(capitalized);
                Method temp = clazz.getDeclaredMethod("get"+ capitalized);
                Object value = request.getParameter(fields[i].getName());
                listM[i] = clazz.getDeclaredMethod("set"+ capitalized,String.class);
                listM[i].invoke(o, value);
                out.println(temp.invoke(o, null).toString());
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          try {
              processRequest(request,response);
          } catch (Exception ex) {
              Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         try {
              processRequest(request,response);
          } catch (Exception ex) {
              Logger.getLogger(FrontServlet.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
}