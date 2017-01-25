
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
      private int cen;
            
      TTask(Cronometro paramCronometro,int milis)
      {
         cen= milis;
        this.a = paramCronometro;
       
      }


      
     public void run(){
                     
                     a.incContador(cen); //incremento  centésimas el tiempo
                     a.incLapso(cen);
                     a.printContador();
                     a.printLapso();
                     
     }
  
}
