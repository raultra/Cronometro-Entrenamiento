
package cronometro;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import java.util.Timer;
import java.util.TimerTask; 

class Cronometro { //extends TimerTask {
     private long contador=0;
     private long lapso=0;
     private Timer timerVelocidad;
     private TTask task;
     private StringItem  txt,txt2;
     
     public Cronometro(StringItem t){
                    txt=t;
                    txt2=t;
     }
     
     public Cronometro(StringItem t,StringItem l){
                    txt=t;
                    txt2=l;
     }
     
//     public void run(){
//                     contador=contador + 1; //incremento en 23 centésimas el tiempo
//                     lapso=lapso+1;
//                     txt.setText(DameFormatoHora(contador));
//                     txt2.setText(DameFormatoHora(lapso));
//     }
     public void Iniciar(){
                     contador=0;
                     lapso=0;
                     timerVelocidad=new Timer();
                     task= new TTask(this);
                     //timerVelocidad.schedule(this,0L,1000L); //cada 230 milisegundos dispara run()
                     timerVelocidad.scheduleAtFixedRate(task, 0L, 10L);
     }
     public void Pausar(){
                    timerVelocidad.cancel();
     }
     
     public void resetLapso()
     {
         lapso=0;
     }
     public long getTiempoCron()
     {
         return contador;
     }
     
 
     public void incContador(int i)
     {
         contador+=i;
     }
     
     public void incLapso(int i)
     {
         lapso+=i;
     }
     
//     public long getContador()
//     {
//         return contador;
//     }
//     
//     public long getLapso()
//     {
//         return lapso;
//     }
     
     public void printContador()
     {
         txt.setText(DameFormatoHora(contador));
     }
     
     public void printLapso()
     {
         txt2.setText(DameFormatoHora(lapso));
     }
//   public static String DameFormatoHora(long millis)
//   {  
//         String Hora;
//         long centesimas, segundos,minutos,horas;
//         
//         horas=millis/2600;
//         minutos=(millis/60)%60;
//         segundos= millis%60;
//   
//         if (horas<10)
//         Hora="0" + horas + ":" ;
//         else
//         Hora= horas + ":";
//         if (minutos<10)
//         Hora=Hora + "0" + minutos + ":";
//         else
//         Hora= Hora + minutos + ":";
//       
//         
//         if (segundos<10)
//         Hora=Hora + "0" + segundos ;
//         else
//         Hora= Hora + segundos ;      
//         return Hora;
//   }
//     

    public static String DameFormatoHora(long millis){
         String Hora;
         long centesimas, segundos,minutos,horas;
         
         centesimas=millis%100;
         segundos=(millis/100) % 60;
         minutos=(millis/100)/60;
         minutos=minutos%60;
         horas=(((millis/100)/60)/60);

         if (horas<10)
         Hora="0" + horas + ":" ;
         else
         Hora= horas + ":";
         if (minutos<10)
         Hora=Hora + "0" + minutos + ":";
         else
         Hora= Hora + minutos + ":";
       
         
         if (segundos<10)
         Hora=Hora + "0" + segundos + ":";
         else
         Hora= Hora + segundos + ":";
         if (centesimas<10)
         Hora=Hora + "0" + centesimas;
         else
         Hora= Hora + centesimas;
         return Hora ;
     }
    
     static StringItem a(Cronometro paramCronometro)
     {
            return paramCronometro.txt;
     }
}