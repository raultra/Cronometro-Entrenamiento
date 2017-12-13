
package cronometro;

import java.util.Date;
import java.util.Vector;

/**
 *
 * @author raul
 */
public class Vuelta {
    
   
    private int distanciaxPista[];					//  Distancia de cada pista que tiene el campo
    private int tiempoxPista[];    					// Tiempo recorrido en cada pista
    private int tiempoRecorrido;					// Tiempo total recorrido en la vuelta
    private short distancia;							// Distancia vuelta (o del campo)
    private byte p=4;       								// Número de pistas que tiene la vuelta (campo)
    private byte pistasContador;					// Pista actualmente recorrida
    
    private Pista pistas[];					// Lleva conteo y estadísticas de cada pista
    
    Vuelta(){
        
        pistasContador=0;
        distancia=0;
        distanciaxPista= new int[p];
        tiempoRecorrido=0;
        tiempoxPista=new int[p];
        // setDistanciaxPista(88, 45, 88, 42);
                
    }
    
    Vuelta(int numPistas){
        
        p=(byte) numPistas;
        pistasContador=0;
        distancia=0;
        distanciaxPista= new int[p];
        tiempoRecorrido=0;
        tiempoxPista=new int[p];
        
        setDistanciaxPista(263);
       
        
        
        // nuevo código
        
     
                
    }
    
    public short getDistancia()
    {
        distancia=0;
        for(byte i=0;i<=(p-1);i++)
            distancia+=distanciaxPista[i];
        
        return distancia;
    }
    
    public int getDistanciaxPista(int p)
    {
        if(p>=0 && p<this.p)
            return distanciaxPista[p];
        else
            return -1;
    }
    
    public int getTiempoRecorrido()
    {
        tiempoRecorrido=0;
        for(byte i=0;i<=(p-1);i++)
            tiempoRecorrido+=tiempoxPista[i];
        
        return tiempoRecorrido;
        
        
    }
    
    public void setTiempoRecorrido(int t)
    {
        tiempoRecorrido=t;
    }
    
//    public void setDistanciaxPista(int p1,int p2,int p3,int p4)
//    {
//        distanciaxPista[0]=(byte)p1;
//        distanciaxPista[1]=(byte)p2;
//        distanciaxPista[2]=(byte)p3;
//        distanciaxPista[3]=(byte)p4;
//        
//    }
//    
     public void setDistanciaxPista(int p1)
    {
        distanciaxPista[0]=p1;
      //  distanciaxPista[1]=p2;
               
    }
     
//   public void setDistanciaxPista(Vector dxp)
//   {
//       int i;
//       
//       for(i=0;i<=p;i++)
//       {
//           distanciaxPista[i]= (Integer) dxp.elementAt(i);
//           
//       }
//        
//               
//   }     
    
//    public void setDistanciaxPista(int p,int dist)
//    {
//        distanciaxPista[p]=(byte)dist;
//               
//    }
    
      
    public void setTiempoxPista(int pista,int tiempo)
    {
        tiempoxPista[pista]=tiempo;
    }
    
    public int setTiempoxPista(int tiempo)
    {
        if(pistasContador<p)
        {
            setTiempoxPista(pistasContador, tiempo);
            return ++pistasContador;
            
        }
        
        return -1;
    }
    
    public int getTiempoxPista(int pista)
    {
        return tiempoxPista[pista];
    }
    
    public void setTiemposPistas(float tp1,float tp2,float tp3,float tp4)
    {
        int c=1;
                     
        tiempoxPista[0]=(int)tp1*c;
        tiempoxPista[1]=(int)tp2*c;
        tiempoxPista[2]=(int)tp3*c;
        tiempoxPista[3]=(int)tp4*c;    
    }
    
    public void setTiemposPistas(float tp1,float tp2)
    {
        int c=1;
             
                
        tiempoxPista[0]=(int)tp1*c;
        tiempoxPista[1]=(int)tp2*c;
       
    }  
    
}



class Pista
{
		byte numero;	//  Identifica pista
		byte totalPistas;	// Conteo de pistas
		int distancia;		// Obvio
		int tiempo;			// Tiempo que lleva recorrerla
		
		Pista(int id)
		{
				numero=(byte) id;
				totalPistas++;
		}
		
		void setDistancia(int d)
		{
			distancia=d;
		}
		
		void setTiempo(int t)
		{
			tiempo=t;
		}
		
		int getDistancia()
		{
			return distancia;
		}
		
		int getTiempo()
		{
			return tiempo;
		}
		
		byte getTotalPistas()
		{
				return totalPistas;
		}
		
}



//class Semana
//{
    //byte idSemana;               // Identifica numero de semana
    //Caminata diasCaminata[];    // guarda datos delas caminatas en la semana
    //byte numeroDiasCaminata=5;     // cuantos dias de caminata en la semana
    //byte indiceCaminataActiva=0;
    
    //Semana(byte id)
    //{
        //idSemana=id;
        //diasCaminata=new Caminata[numeroDiasCaminata];
        //indiceCaminataActiva=0;
    //}
    
    //public byte getNumeroDiasCaminata()
    //{
        //return numeroDiasCaminata;
    //}
    
    //public Caminata getCaminata(byte i)
    //{
        //return diasCaminata[i];
    //}
    
    //public Caminata getCaminataActiva()
    //{
        //return diasCaminata[getIndice()];
    //}
    
    ///**
     //* Agrega una nueva sesión de caminata en la semana
     //* 
     //* @param c
     //* @return Devuelve el indice de dicha caminata
     //*/
    //public byte SetCaminataActiva(Caminata c)
    //{
        //byte indice= this.getIndice();
        //diasCaminata[indice]= c;
        //this.setNuevoIndice();
        //return indice;
    //}
    
    //private byte getIndice()
    //{
        //return indiceCaminataActiva;
    //}
    //private void setNuevoIndice()
    //{
        //indiceCaminataActiva++;
    //}
//}
