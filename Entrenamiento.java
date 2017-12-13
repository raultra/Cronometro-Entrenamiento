

package cronometro;

import java.util.Vector;

/**
 *
 * @author raul
 */
public class Entrenamiento {
    
    private byte numeroDeSemana;        // cuántas semanas durará el entrenamiento
                                // se  incrementará si no se cumplen los objetivos de la semana
    private SemanaEntrenamiento semana;
    private Vector datosSemanas;
    
    private ProgramaEntrenamiento tablaReferencia;

    // Variables auxiliares
    private Vuelta vTemp;
    private Caminata cTemp;

    Entrenamiento()
    {
        
        semana=abrirDB();
        tablaReferencia= new ProgramaEntrenamiento();
        
   
    }
    
    private SemanaEntrenamiento abrirDB()
    {
        SemanaEntrenamiento sem;
        
        sem=new SemanaEntrenamiento(numeroDeSemana);
        return sem;
    }
    
}


class SemanaEntrenamiento
{
    int numeroSemana;
    int diasCaminata;
    Caminata [] caminatas;

    public SemanaEntrenamiento(int s) {
        diasCaminata=0;
        numeroSemana=s;
        caminatas= new Caminata[5];
    }
        
    public void setCaminata(Caminata c)
    {
        caminatas[diasCaminata]=c;
        diasCaminata++;
    }
    
    public Caminata getCaminata(int numeroCaminata)
    {
        return caminatas[numeroCaminata-1];
    }

    public int getcontadorCaminatas()
    {
        return diasCaminata;
    }
}



class ProgramaEntrenamiento
{
    
    private int dias=5;
   
    private float[][] tablaDistanciaxTiempo={{2.119f,38.142f},{2.119f,36.023f},{2.119f,33.904f},{2.119f,31.785f},{2.608f,40.6848f},
                                       {2.608f,39.6416f},{2.608f,38.5984f},{3.097f,47.4873f},{3.097f,46.455f},{3.097f,45.4227f}};

    public ProgramaEntrenamiento() {
    }
    
    
    public void setDias(int n)
    {
        dias=n;
    }
    
    public float getVelocidadMph(int semana)
    {
        return (tablaDistanciaxTiempo[semana-1][0]*60)/tablaDistanciaxTiempo[semana-1][1];
    }
    
    public float getVelocidadMs(int semana)
    {
        float t;
        t= getVelocidadMph(semana);
        
        return (t/3600)*1609;
        
    }
    
    public float getPuntos(float distancia,float velocidad)
    {
        return ((velocidad-1)*distancia)-1;
    }
    
    public float getDistancia(int semana)
    {
        return tablaDistanciaxTiempo[semana-1][0];
    }
    
    public float getTiempo(int semana)  // Tiempo meta de la semana
    {
        return tablaDistanciaxTiempo[semana-1][1];
    }
    
    public float getTiempo(int semana,int dia)  // Tiempo meta del dia especifico
    {
        float inc,subtiempo;
        
        inc= tablaDistanciaxTiempo[semana-1][1]/dias;
        subtiempo=inc*dia;
        
        return subtiempo;
    }
    
}