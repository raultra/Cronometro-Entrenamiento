package cronometro;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class MIDletCronometro extends MIDlet {
     private PantallaPrincipal formulario;
     


     public MIDletCronometro(){
                     
                     formulario= new PantallaPrincipal(this);

     }
     
     

     public void startApp(){
                    Display.getDisplay(this).setCurrent(formulario);
     }
     public void pauseApp(){
     }
     public void destroyApp(boolean unconditional){
     }

	
}


