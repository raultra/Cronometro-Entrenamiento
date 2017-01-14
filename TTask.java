
package cronometro;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author raul
 */
import java.util.TimerTask;

final class TTask  extends TimerTask
{
      private final Cronometro a;

            
      TTask(Cronometro paramCronometro)
      {
        this.a = paramCronometro;
      }


      
     public void run(){
         
                     a.incContador(1); //incremento  centésimas el tiempo
                     a.incLapso(1);
                     a.printContador();
                     a.printLapso();
                     
     }
  
}
