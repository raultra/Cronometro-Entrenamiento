/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cronometro;

import java.util.Date;

/**
 *
 * @author raul
 */

public class Caminata
{
    private Vuelta vueltas[];           // Contabiliza los datos de cada vuelta dada al campo
    private byte numeroVueltas;          // Total vueltas dadas
    
    private int tiempoCaminata;
    private boolean tiempoCumplido=false;
    private short ppm;                    // pulso promedio
    //private String fechaCaminata;           // usada para identificar la caminata en la semana
    
    private byte vueltasContador;
   // private byte vueltaActual;
    private Date fechaCaminata;
    
    Caminata(byte numeroVueltas)
    {
        //fechaCaminata= Cronometro.getFecha();
        fechaCaminata = new Date(System.currentTimeMillis());
        this.numeroVueltas=numeroVueltas;
        vueltas= new Vuelta[this.numeroVueltas];        
        
        vueltasContador=0;
        //vueltaActual=0;
        
        this.setNuevaVuelta();
    }
    
    public Date getFechaCaminata()
    {
        return fechaCaminata;
    }
    
//    public String getFechaCaminata()
//    {
//        return fechaCaminata;
//    }
    
    public void setPPM(short pulsoPromedio)
    {
        ppm=pulsoPromedio;
    }
    
    public short getPPM()
    {
        return ppm;
    }
      
    public short getDistanciaCaminata()
    {
        short d;
        
        d= (short)(this.numeroVueltas*vueltas[vueltasContador].getDistancia());
        
        return d;
    }
    
    public int getNumeroDeVueltasTotal()
    {
        return numeroVueltas;
    }
    
    public void setNumeroDeVueltas(byte vueltasTotal)
    {
        numeroVueltas= vueltasTotal;
    }
    
    public int getTiempoCaminata()
    {
        tiempoCaminata=0;
        for(int i=0;i<this.numeroVueltas;i++)
            tiempoCaminata+=vueltas[i].getTiempoRecorrido();
        return tiempoCaminata;
    }
    
    public byte getVueltaActual()
    {
        //return (byte)(vueltaActual);
         return (byte)(vueltasContador);
    }
    
    public byte setNuevaVuelta()
    {
        if(vueltasContador<this.numeroVueltas)
        {
            vueltas[vueltasContador]=new Vuelta();
            //vueltaActual=vueltasContador;
            return vueltasContador;
        }
        //else if(vueltasContador>=this.numeroVueltas)
            
           
        return -1;
    }
          
  
    public void cancelarCaminata()
    {
        setNumeroDeVueltas(vueltasContador);
        
    }
    
    
    public void guardarTiempoxPista(int tiempo)
    {
        int t=0;
        
        if(!tiempoCumplido)
            t=vueltas[vueltasContador].setTiempoxPista(tiempo);
        
        if(t==4)
        {
            vueltasContador++;
            if(this.setNuevaVuelta()<0)
                tiempoCumplido=true;
            //vueltas[vueltaActual].setTiempoxPista(tiempo);
        }
    }
      
}