
package cronometro;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author raul
 */
public class PantallaResultados  extends Form implements CommandListener{
    
    private MIDlet app;
   
    private Command cmdSalir;
    private Vuelta vueltaRef;
    private Caminata caminataDatos;
    
    private StringItem tiempoTotal;
    private StringItem tiempoMeta;
    private StringItem puntosSesion;
    private StringItem fecha;
    
    
    PantallaResultados(MIDlet a)
    {
         super("Estadísticas");
         app= a;
         fecha= new StringItem("Sesión: ",Cronometro.getFecha());
         tiempoTotal = new StringItem("Tiempo total: ", "00:00:00:00");
         tiempoMeta = new StringItem( "Tiempo Meta : ", "00:00:00:00");
 
         //totalVueltas = new StringItem("Vuelta:", "00");
         
         
                 
         cmdSalir=new Command("Salir",Command.EXIT,0);
         this.append(fecha);
         this.addCommand(cmdSalir);
         this.append(tiempoTotal);
         this.append(tiempoMeta);
         
         this.setCommandListener(this);
    }
    
    public void setCaminata(Caminata c)          
    {
        caminataDatos=c;
    }
    
    public void setVueltaGoal(Vuelta v)
    {
        vueltaRef=v;
    }
    
    /**
     * Regresa tiempo requerido para obtener puntos
     * 
     * @return 
     */
    public int getTiempoMeta()
    {
        int d,t;
        
        d= caminataDatos.getNumeroDeVueltasTotal();
        t=d*vueltaRef.getTiempoRecorrido();
        return t;
    }
    
    public void mostrarResultados()
    {
        String tiempo="";
        int dif,tfinal,tmeta;
        
        tfinal=caminataDatos.getTiempoCaminata();
        tmeta= getTiempoMeta();
        
        dif=tmeta-tfinal;
        
        if (dif>0)
            tiempo= " - ";
        else
            tiempo= " + ";
        
        tiempo+=Cronometro.DameFormatoHoraCorta(Math.abs(dif));
        
        tiempoMeta.setText(Cronometro.DameFormatoHora(tmeta));
        tiempoTotal.setText(Cronometro.DameFormatoHora(tfinal)+tiempo);
        
        
    }
    public void commandAction(Command c, Displayable d)
    {
         if (c==cmdSalir){
             //app.destroyApp(false);
         //    estadisticas= new PantallaResultados(app);
             
             app.notifyDestroyed();
         }
  
    }  
}
