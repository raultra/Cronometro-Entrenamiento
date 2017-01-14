

package cronometro;

/**
 *
 * @author raul
 */
public class Entrenamiento {
    
    private byte numeroDeSemanas;        // cuantas semanas durara el entrenamiento
                                // se  incrementará si no se cumplen los objetivos de la semana
    private Semana datosSemanas[];
    private ProgramaEntrenamiento datosReferencia;

    // Variables auxiliares
    private Vuelta vTemp;
    private Caminata cTemp;

    Entrenamiento()
    {
        numeroDeSemanas=1;
        datosSemanas= new Semana[numeroDeSemanas];
        datosSemanas[0]= new Semana((byte)1);
        
        cTemp= datosSemanas[0].getCaminataActiva();
       // vTemp= cTemp.getVueltaActiva();
        
    }
    
    
}

class CampoDeEntrenamiento
{
    private Vuelta vueltaInfo;
    private int numeroVueltas;
    private int distanciaTotal;
    private int pistasExtras;
    
}

class ParametrosReferencia
{
    private int tiempoxPista[];
    private int tiempoxVuelta[];
    private int tiempoTotal;
    private int metrosxSegundo;
    
}

class ProgramaEntrenamiento
{
    private int distancia;
    private int tiempo;
    private int puntos;
    private byte frecxSemana;
    private ParametrosReferencia parRef;
    private CampoDeEntrenamiento campoInfo;
}