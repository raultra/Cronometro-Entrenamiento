
package cronometro;

import java.util.Date;

/**
 *
 * @author raul
 */
public class Vuelta {
    
   
    private byte distanciaxPista[];
    private int tiempoxPista[];
    
    private int tiempoRecorrido;
    private short distancia;
    private byte p=4;       // numero de pistas x vuelta
    
    private byte pistasContador;
    
    Vuelta(){
        
        pistasContador=0;
        distancia=0;
        distanciaxPista= new byte[p];
        tiempoRecorrido=0;
        tiempoxPista=new int[p];
        setDistanciaxPista(88, 45, 88, 42);
                
    }
    
    public short getDistancia()
    {
        for(byte i=0;i<=(p-1);i++)
            distancia+=distanciaxPista[i];
        
        return distancia;
    }
    
    
    public int getTiempoRecorrido()
    {
        for(byte i=0;i<=(p-1);i++)
            tiempoRecorrido+=tiempoxPista[i];
        
        return tiempoRecorrido;
        
        
    }
    
    public void setDistanciaxPista(int p1,int p2,int p3,int p4)
    {
        distanciaxPista[0]=(byte)p1;
        distanciaxPista[1]=(byte)p2;
        distanciaxPista[2]=(byte)p3;
        distanciaxPista[3]=(byte)p4;
        
    }
      
    public void setTiempoxPista(byte pista,int tiempo)
    {
        tiempoxPista[pista]=tiempo;
    }
    
    public boolean setTiempoxPista(int tiempo)
    {
        if(pistasContador<p)
        {
            setTiempoxPista(pistasContador++, tiempo);
            return true;
            
        }
        
        return false;
    }
    
    public int getTiempoxPista(byte pista)
    {
        return tiempoxPista[pista];
    }
    
    
}


class Caminata
{
    private Vuelta vueltas[];           // Contabiliza los datos de cada vuelta dada al campo
    private byte numeroVueltas;          // Total vueltas dadas
    private short distanciaCaminata;
    private int tiempoCaminata;
    private short ppm;                    // pulso promedio
    private Date fechaCaminata;           // usada para identificar la caminata en la semana
    
    private byte vueltasContador;
    private byte vueltaActual;
    
    
    Caminata(byte numeroVueltas)
    {
        
        this.numeroVueltas=numeroVueltas;
        vueltas= new Vuelta[this.numeroVueltas];        
        //distanciaCaminata= (short)(this.numeroVueltas*Vuelta.getDistancia());
        vueltasContador=0;
        vueltaActual=0;
        
        this.setNuevaVuelta();
    }
    
    public void setPPM(short pulsoPromedio)
    {
        ppm=pulsoPromedio;
    }
    
    public short getPPM()
    {
        return ppm;
    }
      
    public short getDistancia()
    {
        return distanciaCaminata;
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
        return (byte)(vueltaActual);
    }
    
    public byte setNuevaVuelta()
    {
        if(vueltasContador<this.numeroVueltas)
        {
            vueltas[vueltasContador]=new Vuelta();
            vueltaActual=vueltasContador;
            return vueltasContador++;
        }
           
        return -1;
    }
          
  
    public void guardarTiempoxPista(int tiempo)
    {
        boolean t;
        
        t=vueltas[vueltaActual].setTiempoxPista(tiempo);
        if(!t)
        {
            this.setNuevaVuelta();
            vueltas[vueltaActual].setTiempoxPista(tiempo);
        }
    }
      
}


class Semana
{
    byte idSemana;               // Identifica numero de semana
    Caminata diasCaminata[];    // guarda datos delas caminatas en la semana
    byte numeroDiasCaminata=5;     // cuantos dias de caminata en la semana
    byte indiceCaminataActiva=0;
    
    Semana(byte id)
    {
        idSemana=id;
        diasCaminata=new Caminata[numeroDiasCaminata];
        indiceCaminataActiva=0;
    }
    
    public byte getNumeroDiasCaminata()
    {
        return numeroDiasCaminata;
    }
    
    public Caminata getCaminata(byte i)
    {
        return diasCaminata[i];
    }
    
    public Caminata getCaminataActiva()
    {
        return diasCaminata[getIndice()];
    }
    
    /**
     * Agrega una nueva sesión de caminata en la semana
     * 
     * @param c
     * @return Devuelve el indice de dicha caminata
     */
    public byte SetCaminataActiva(Caminata c)
    {
        byte indice= this.getIndice();
        diasCaminata[indice]= c;
        this.setNuevoIndice();
        return indice;
    }
    
    private byte getIndice()
    {
        return indiceCaminataActiva;
    }
    private void setNuevoIndice()
    {
        indiceCaminataActiva++;
    }
}