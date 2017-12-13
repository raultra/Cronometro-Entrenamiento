
package cronometro;

import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;
/**
 *
 * @author raul
 */
public class PantallaConfiguracion  extends Form implements CommandListener,ItemStateListener {
    
   
    private MIDletCronometro app;
    private PantallaPrincipal pp;
   
    private Command cmdContinuar;
    private Command cmdRegresar;
    private Command cmdParametros;
    private Command cmdRegistros;
    
    
    private Vuelta vueltaRef;
    private Caminata caminataDatos;
    private AlmacenamientoDB record;    
    private Vector listaRegistros;
    
    private TextField tiempoMeta;
    private TextField peso;
    private TextField pista1;
    private TextField pista2;
    private TextField pista3;
    private TextField pista4;
    
    // Mostrar registro almacenados: fecha,tiempo,ppm
    private StringItem campo1;
    private StringItem campo2;          
    private StringItem campo3;                
    private StringItem campo4;
    private StringItem campo5;
    
    
    private StringItem fecha;
    private DateField datefield;
    
    PantallaConfiguracion(MIDletCronometro  a)
    {
         super("Configuración");
         app = a;
         
         //fecha= new StringItem("Sesión: ",Cronometro.getFecha());
         tiempoMeta = new TextField("Tiempo Meta: ",null,5,TextField.DECIMAL);
         peso = new TextField("Peso (kg): ",null,5,TextField.DECIMAL);
         
         pista1=new TextField("Pista 1: ",null,5,TextField.DECIMAL);
         pista2=new TextField("Pista 2: ",null,5,TextField.DECIMAL);
         pista3=new TextField("Pista 3: ",null,5,TextField.DECIMAL);
         pista4=new TextField("Pista 4: ",null,5,TextField.DECIMAL);
         
         fecha=new StringItem("Tiempos: ","");
         
         campo1=new StringItem("Registro :", "");
         campo2=new StringItem("Registro :", "");
         campo3=new StringItem("Registro :", "");
         campo4=new StringItem("Registro :", "");
         campo5=new StringItem("Registro :", "");
         
         listaRegistros= new Vector(18,3);
         
         //datefield = new DateField("", DateField.DATE_TIME);
    //     today = new Date(System.currentTimeMillis());
 
         //totalVueltas = new StringItem("Vuelta:", "00");
         
        
         cmdParametros=new Command("Cambiar Param.",Command.OK,0);
         cmdRegistros=new Command("Ver Registros",Command.OK,0);
         cmdContinuar=new Command("Salir",Command.OK,0);   
         cmdRegresar=new Command("Regresar",Command.BACK,0); 
         
        // this.append(datefield);
         //this.append(fecha);
         this.addCommand(cmdParametros);
         this.addCommand(cmdRegistros);
         
         this.addCommand(cmdContinuar);

         
         
         this.setCommandListener(this);
         Display.getDisplay(app).setCurrent(this);
    }   
    
      
    public void setVueltaReferencia(Vuelta v)
    {
        vueltaRef=v;
    }
    
    public void setCaminataDatos(Caminata c)
    {
        caminataDatos= c;
    }
    
    public void setAlmacenamiento(AlmacenamientoDB r)
    {
        record = r;
    }
    
    public void setPantallaPrincipal(PantallaPrincipal p)
    {
        pp= p;
    }
    
    private void mostrarEstadisticas()
    {
         this.deleteAll();
         this.addCommand(cmdParametros);
         this.addCommand(cmdRegistros);
         
         this.addCommand(cmdContinuar);
 
    }
            
    private void mostrarConfiguracion()
    {
         this.deleteAll();
         this.removeCommand(cmdParametros);
         this.removeCommand(cmdRegistros);
         this.removeCommand(cmdContinuar);
         this.addCommand(cmdRegresar);
         
         
         this.append(tiempoMeta);
         this.append(peso);
         this.append(fecha);
         this.append(pista1);
         this.append(pista2);
         this.append(pista3);
         this.append(pista4);
         
    }
    
    public void mostrarRegistros()
    {
        String s1;
        
         this.deleteAll();
         this.removeCommand(cmdParametros);
         this.removeCommand(cmdRegistros);
         this.removeCommand(cmdContinuar);
         this.addCommand(cmdRegresar);  
        
         this.append(fecha);
         this.append(campo1);
         this.append(campo2);
         this.append(campo3);
         this.append(campo4);
         this.append(campo5);
        
        
        
        
        
        
        
//        record.abrirRecordStore();
//        if (record.getRecordStore()!=null)
//        {
//            record.setVector(listaRegistros);
//            record.leerRegistros();
//            record.cerrarRecordStore();
//        }
//        
//        
//        s1=""+ listaRegistros.elementAt(0)+ ","+ listaRegistros.elementAt(1) +","+ listaRegistros.elementAt(2);
//        //s1="hola mundo";
//        fecha.setText(s1);
//        
       
        
    }
    public int convertirCadenaATiempo(String str)
    {
        String s, min, seg, cen;
        int t, ini, fin;
        
        
        s=str;
        
        ini = s.indexOf('.');
        min = s.substring(0, ini);
        seg = s.substring(ini + 1);
//                ini=seg.indexOf('.');
//                seg=seg.substring(0,ini);
        ini = Integer.parseInt(min);
        fin = Integer.parseInt(seg);

        fin = fin + ini * 60;

        t = fin * 100;
        
        return t;
    }
    
    private void calcularNuevosTiempos()
    {
        float t1,t2,t3,t4;
        int c=100;
        
        int t=convertirCadenaATiempo(tiempoMeta.getString());
        float vel= Caminata.calcularVelocidad(t, caminataDatos.getDistanciaCaminata());

        t1 = c * vueltaRef.getDistanciaxPista(0) / vel;
//        t2 = c * vueltaRef.getDistanciaxPista(1) / vel;
//        t3 = c * vueltaRef.getDistanciaxPista(2) / vel;
//        t4 = c * vueltaRef.getDistanciaxPista(3) / vel;
        
        //vueltaRef.setTiemposPistas(t1, t2, t3, t4);
        vueltaRef.setTiempoxPista(0,(int)t1);
             
    }
    public void commandAction(Command c, Displayable d)
    {
  
        
         if (c==cmdContinuar){
         

             Display.getDisplay(app).setCurrent(pp);
//               mostrarRegistros();
         }
          else if (c == cmdParametros) {
              
              mostrarConfiguracion();
            // calcularNuevosTiempos();
//             pp.MostrarObjetivos();

        } else if (c == cmdRegistros) {
      
            mostrarRegistros();

        }
        else if (c==cmdRegresar)
        {
             mostrarEstadisticas();
                    
        }
         
        
  
    }  
    
    public void itemStateChanged(Item i){
     

        if (i == tiempoMeta){

        }
    }
    
}
