
package cronometro;


import javax.microedition.lcdui.*;

import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;

import java.util.Timer;

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
                     //lapso=0;
                     timerVelocidad=new Timer();
                     task= new TTask(this);
                     //timerVelocidad.schedule(task,0L,10L); 
                     timerVelocidad.scheduleAtFixedRate(task, 0L, 100L);
     }
     public void Pausar(){
                    timerVelocidad.cancel();
     }
     
     public void resetLapso()
     {
         lapso=0;
     }
     
     public long getLapso()
     {
         return lapso;
     }
     public void setLapso(int l)
     {
         lapso= l;
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
         lapso-=i;
         
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
         String s1;
         
         if(lapso<0)
             s1= DameFormatoHora(0) + " +" + DameFormatoHoraCorta(lapso);
         else
             s1=DameFormatoHora(lapso);
         
         txt2.setText(s1);
     }


    public static String DameFormatoHora(long m){
         String Hora;
         long centesimas, segundos,minutos,horas,millis;
         
         millis= Math.abs(m);
         
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
    
     public static String DameFormatoHoraCorta(long m)
     {
         String Hora="";
         long centesimas, segundos,minutos,horas,millis;
         
         millis= Math.abs(m);
         
         centesimas=millis%100;
         segundos=(millis/100) % 60;
         minutos=(millis/100)/60;
         minutos=minutos%60;
         horas=(((millis/100)/60)/60);
         
         if(horas!=0)
         {
             if (horas<10)
             Hora="0" + horas + ":" ;
             else
             Hora= horas + ":";
         }
         
         if(minutos!=0)
         {
             if (minutos<10)
             Hora=Hora + "0" + minutos + ":";
             else
             Hora= Hora + minutos + ":";
         }
         
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
     
     
    public static String getFecha()
    {
        String fecha;
       
        Calendar calendar = Calendar.getInstance();
        
        Date myDate = new Date(); // as an example, date of "now"
        calendar.setTimeZone(TimeZone.getTimeZone("America/Mexico_City"));
        calendar.setTime(myDate);

        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int monthNumber = calendar.get(Calendar.MONTH);

     
   
        String[] monthNames = {"Enero", "Febrero",
                    "Marzo", "Abril", "Mayo", "Junio", "Julio",
                    "Agosto", "Septiembre", "Octubre", "Noviembre",
                    "Deciembre"};
       
        String month = monthNames[monthNumber]; //or String month = monthNames[monthNumber];

        int year = calendar.get(Calendar.YEAR);

        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        String amPm = calendar.get(Calendar.AM_PM)==1 ? "am" : "pm";
        
        if(hour<10)
        
            fecha= day + " " + month + " " + year + ", " + "0"+hour + ":" + minute + amPm;
        else if(minute<10)
            fecha= day + " " + month + " " + year + ", " + hour + ":" + "0"+ minute + amPm;   
        
        else
            fecha= day + " " + month + " " + year + ", " + hour + ":" + minute + amPm;
        
        return fecha;
     }
     static StringItem a(Cronometro paramCronometro)
     {
            return paramCronometro.txt;
     }
}