
package cronometro;

import java.util.Date;
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
    private Vuelta vueltaRef;
    private Caminata caminataDatos;
    
    private TextField tiempoMeta;
    private TextField peso;
    private StringItem fecha;
    private DateField datefield;
    
    PantallaConfiguracion(MIDletCronometro  a)
    {
         super("Configuración");
         app = a;
         
         //fecha= new StringItem("Sesión: ",Cronometro.getFecha());
         tiempoMeta = new TextField("Tiempo Meta: ",null,5,TextField.DECIMAL);
         peso = new TextField("Peso (kg): ",null,5,TextField.DECIMAL);
         
         //datefield = new DateField("", DateField.DATE_TIME);
    //     today = new Date(System.currentTimeMillis());
 
         //totalVueltas = new StringItem("Vuelta:", "00");
         
         
                 
         cmdContinuar=new Command("Continuar",Command.OK,0);                
         
        // this.append(datefield);
         //this.append(fecha);
         this.addCommand(cmdContinuar);
         this.append(tiempoMeta);
         this.append(peso);
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
    
    public void setPantallaPrincipal(PantallaPrincipal p)
    {
        pp= p;
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
        t2 = c * vueltaRef.getDistanciaxPista(1) / vel;
        t3 = c * vueltaRef.getDistanciaxPista(2) / vel;
        t4 = c * vueltaRef.getDistanciaxPista(3) / vel;
        
        vueltaRef.setTiemposPistas(t1, t2, t3, t4);
        
             
    }
    public void commandAction(Command c, Displayable d)
    {
  
        
         if (c==cmdContinuar){
         
             calcularNuevosTiempos();
             pp.MostrarObjetivos();
             Display.getDisplay(app).setCurrent(pp);
            
         }
  
    }  
    
    public void itemStateChanged(Item i){
     

        if (i == tiempoMeta){

        }
    }
    
}
