package cronometro;


import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;


public class MIDletCronometro extends MIDlet {
     private Displayable formulario;
     private Display pantalla;



     public MIDletCronometro(){
                     pantalla=Display.getDisplay(this);
                     formulario= new PantallaPrincipal(this);

     }

     public void startApp(){
                    pantalla.setCurrent(formulario);
     }
     public void pauseApp(){
     }
     public void destroyApp(boolean unconditional){
     }

	
}


