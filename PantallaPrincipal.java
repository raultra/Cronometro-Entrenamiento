package cronometro;

import java.util.Date;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author raul
 */
public class PantallaPrincipal extends Form implements CommandListener, ItemStateListener {

    private MIDletCronometro app;
    private Command cmdIniciar;
    private Command cmdSalir;
    private Command cmdPausa;
    private Command cmdContinuar;
    private Command cmdMarcar;
    private Command cmdDetener;
    private Command cmdConfigurar;
    private PantallaResultados res;

    private StringItem tiempoContado;                  // Tiempo Total
    private StringItem tiempoVueltaAnterior;          // vuelta anterior
    private StringItem totalVueltas;                  // Total Vueltas
    private StringItem pista[];                       // Tiempo de cada pista (4)
    private StringItem fecha;
    private Gauge tiempoBarra;
    private TextField tiempo;

    private Cronometro cronometro;
    private Caminata caminata;
    private Vuelta vueltaGoal;
    private Entrenamiento bitacora;
    private AlmacenamientoDB records;

    
    //private PantallaResultados estadisticas;
    private int contadorPistas = 0;
    private int tiempoActual = 0;
    private int tiempoAnterior = 0;
    private int tiempoAgregado = 0;
    private int milisTemp = 100;

    PantallaPrincipal(MIDletCronometro a) {
        super("Cronómetro");
        app = a;
      
        cmdIniciar = new Command("Iniciar", Command.OK, 0);
        cmdSalir = new Command("Salir", Command.EXIT, 1);
        cmdPausa = new Command("Pausar", Command.STOP, 0);
        cmdContinuar = new Command("Continuar", Command.OK, 1);
        cmdMarcar = new Command("Marcar", Command.OK, 0);
        cmdDetener = new Command("Terminar", Command.EXIT, 0);
        cmdConfigurar=new Command("Configuración",Command.OK,0);
        
        //fecha= new StringItem("",Cronometro.getFecha());
        tiempoContado = new StringItem("Tiempo:  ", "00:00:00:00");
        tiempoVueltaAnterior = new StringItem("Lap:  ", "00:00:00:00");
        totalVueltas = new StringItem("Vuelta:", "00");

        tiempoBarra = new Gauge("", false, 10, 0);
        tiempoBarra.setLayout(Item.LAYOUT_CENTER);
        //tiempo = new TextField("Tiempo Meta", null, 5, TextField.DECIMAL);

        pista = new StringItem[4];

        pista[0] = new StringItem("Tiempo Meta 1: ", "00:00:00:00");
        pista[0].setLayout(Item.LAYOUT_LEFT);
        pista[1] = new StringItem("Tiempo Meta 2: ", "00:00:00:00");
        pista[2] = new StringItem("Tiempo Meta 3: ", "00:00:00:00");
        pista[3] = new StringItem("Tiempo Meta 4: ", "00:00:00:00");

        this.addCommand(cmdConfigurar);
        this.addCommand(cmdSalir);
        this.addCommand(cmdIniciar);
        //this.append(fecha);
        this.append(tiempoContado);
        this.append(totalVueltas);
        this.append(tiempoVueltaAnterior);
        //this.append(tiempo);
        
       

        this.append(tiempoBarra);

        this.append(pista[0]);
        this.append(pista[1]);
        this.append(pista[2]);
        this.append(pista[3]);
        
      
        cronometro = new Cronometro(tiempoContado, tiempoVueltaAnterior);
        
//        bitacora= new Entrenamiento();
        
//        records= new AlmacenamientoDB();
//        
//        
//        
//        if (records.getRecordStore()!=null)
//        {
//            records.escribirDatos("20 Marzo", 3800, 136);
//            records.escribirDatos("21 Marzo", 3700, 146);
//            records.escribirDatos("22 Marzo", 3600, 146);
//            records.cerrarRecordStore();
//        }
//            
          

//        caminata = new Caminata((byte) 13);
//        
//        vueltaGoal = new Vuelta();
//        vueltaGoal.setTiemposPistas(5250, 2685, 5250, 2506);
        caminata = new Caminata((byte) 8);
        
        vueltaGoal = new Vuelta(1);
        //vueltaGoal.setTiemposPistas(20000, 20000);       
        vueltaGoal.setTiempoxPista(0,2000);
        
        MostrarObjetivos();
        this.setCommandListener(this);
        //this.setItemStateListener(this);
    }

    public void iniciarCron() {
        Display.getDisplay(app).vibrate(100);
        resetPistas();
        //this.delete();
        totalVueltas.setText("" + caminata.getVueltaActual());
        escribirPista(vueltaGoal.getTiempoxPista(contadorPistas));
        cronometro.setLapso(vueltaGoal.getTiempoxPista(contadorPistas));
        cronometro.setMilis(milisTemp);
        cronometro.Iniciar();
//        tiempoBarra.setMaxValue(caminata.getNumeroDeVueltasTotal());
//        tiempoBarra.setLayout(Item.LAYOUT_CENTER);
//
//        super.set(borrarItem(tiempo), tiempoBarra);
        this.removeCommand(cmdIniciar);
        this.removeCommand(cmdSalir);
        this.removeCommand(cmdConfigurar);
        //this.addCommand(cmdPausa);
        this.addCommand(cmdMarcar);
        this.addCommand(cmdDetener);

        this.setItemStateListener(null);

    }

    public void pararCron() {
        cronometro.Pausar();
        this.removeCommand(cmdPausa);
        this.removeCommand(cmdContinuar);
        this.addCommand(cmdSalir);
        this.addCommand(cmdIniciar);

    }

    public void continuarCron() {
        cronometro.Pausar();
        cronometro = new Cronometro(tiempoContado);
        cronometro.Iniciar();
    }

    public void marcarCron() {
        int temp;

        Display.getDisplay(app).vibrate(200);

        tiempoActual = (int) cronometro.getTiempoCron();
        //cronometro.resetLapso();

        temp = tiempoActual - tiempoAnterior;

        actualizarVista(temp);
        tiempoAnterior = tiempoActual;

    }

    public void irConfiguracion() {
          PantallaConfiguracion conf= new PantallaConfiguracion(app);

//        conf.setPantallaAnterior(this);
        conf.setVueltaReferencia(vueltaGoal);
        conf.setCaminataDatos(caminata);
        conf.setAlmacenamiento(records);  
        
        conf.setPantallaPrincipal(this);
        
//        Display.getDisplay(app).setCurrent(conf);

    }

    public void terminarCron() {
        caminata.cancelarCaminata();

        res = new PantallaResultados(app);
        res.setCaminata(caminata);
        res.setVueltaGoal(vueltaGoal);
        res.mostrarResultados();
        Display.getDisplay(app).setCurrent(res);
    }

    private void actualizarVista(int temp) {
        int c, t, dif;

        c = getcontadorPistas();
        t = vueltaGoal.getTiempoxPista((byte) c);

        dif = t - temp;

        tiempoAgregado += dif;

        mostrarPistas(temp, tiempoAgregado);

        caminata.guardarTiempoxPista(temp);
        totalVueltas.setText(Integer.toString(caminata.getVueltaActual()));
        tiempoBarra.setValue(caminata.getVueltaActual());

        cronometro.setLapso(vueltaGoal.getTiempoxPista(contadorPistas));
        cronometro.setTiempoAgregado(tiempoAgregado);

        escribirPista(vueltaGoal.getTiempoxPista(contadorPistas));

    }

    private int getcontadorPistas() {
        return contadorPistas;
    }

    private void escribirPista(int temp) {
        pista[contadorPistas].setText(Cronometro.DameFormatoHora(temp));
    }

    public MIDletCronometro getApp()
    {
        return app;
    }
    private void mostrarPistas(int temp, int dif) {
        int d;
        String s;

        if (contadorPistas == 0) {
            resetPistas();
        }

        d = Math.abs(dif);

        if (dif < 0) {
            s = "  + ";
        } else {
            s = "  - ";
        }

        s = s + Cronometro.DameFormatoHoraCorta(d);

        pista[contadorPistas].setText(Cronometro.DameFormatoHora(temp) + s);

        if (contadorPistas < 3) {
            contadorPistas++;
        } else {
            contadorPistas = 0;
        }

    }

    public void MostrarObjetivos() {
        int t;
        t = vueltaGoal.getTiempoRecorrido() * caminata.getNumeroDeVueltasTotal();

        tiempoContado.setText(Cronometro.DameFormatoHora(t));
        tiempoVueltaAnterior.setText(Cronometro.DameFormatoHora(vueltaGoal.getTiempoRecorrido()));

        totalVueltas.setText("" + caminata.getNumeroDeVueltasTotal());
        tiempoBarra.setMaxValue(caminata.getNumeroDeVueltasTotal());
        
        pista[0].setText(Cronometro.DameFormatoHora(vueltaGoal.getTiempoxPista(0)));
//        pista[1].setText(Cronometro.DameFormatoHora(vueltaGoal.getTiempoxPista(1)));
//        pista[2].setText(Cronometro.DameFormatoHora(vueltaGoal.getTiempoxPista(2)));
//        pista[3].setText(Cronometro.DameFormatoHora(vueltaGoal.getTiempoxPista(3)));
    }

    private void resetPistas() {
        String t = "00:00:00:00";

        pista[0].setText(t);
        pista[1].setText(t);
        pista[2].setText(t);
        pista[3].setText(t);
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSalir) {
            //app.destroyApp(false);
            //    estadisticas= new PantallaResultados(app);

            app.notifyDestroyed();
        } else if (c == cmdIniciar) {
            iniciarCron();

        } else if (c == cmdPausa) {
            //pararCron();
           

        } else if (c == cmdContinuar) {
            continuarCron();
        } else if (c == cmdMarcar) {
            marcarCron();

        } else if (c == cmdDetener) {
            terminarCron();
        }
        else if(c== cmdConfigurar){
             irConfiguracion();
        }
    }

    public void itemStateChanged(Item i) {
        String s, min, seg, cen;
        int t = 0, ini, fin;
        float t1, t2, t3, t4;
        float vel;
        final int c = 100;

        if (i == tiempo) {
            s = tiempo.getString();
//
//            if (s.length() == tiempo.getMaxSize()) {
//                ini = s.indexOf('.');
//                min = s.substring(0, ini);
//                seg = s.substring(ini + 1);
////                ini=seg.indexOf('.');
////                seg=seg.substring(0,ini);
//                ini = Integer.parseInt(min);
//                fin = Integer.parseInt(seg);
//
//                fin = fin + ini * 60;
//
//                t = fin * 100;
//
//                //tiempoContado.setText(Cronometro.DameFormatoHora(t));
//                //tiempoContado.setText(min+":"+seg);
//                vel = calcularVelocidad(t);
//
//                t1 = c * vueltaGoal.getDistanciaxPista(0) / vel;
//                t2 = c * vueltaGoal.getDistanciaxPista(1) / vel;
//                t3 = c * vueltaGoal.getDistanciaxPista(2) / vel;
//                t4 = c * vueltaGoal.getDistanciaxPista(3) / vel;
//
//                vueltaGoal.setTiemposPistas(t1, t2, t3, t4);
//                MostrarObjetivos();
//            }
//            tiempo.setLabel("Milis");
//            milisTemp=tiempo.getValue();
//            tiempo.setLabel("" + milisTemp);

        }

    }

//    private float calcularVelocidad(int t) {
//        int l, k;
//        float vel, tiempo, dis;
//
//        l = vueltaGoal.getDistancia();
//        k = caminata.getNumeroDeVueltasTotal();
//        dis = l * k;
//        dis = dis / 1609;
//        tiempo = (float) (t / 100) / 60;
//        vel = (60 * dis) / tiempo;    // mph
//
//        vel = (vel * 1609) / 3600;    // a m/s
//
//        return vel;
//
//    }

    private int borrarItem(Item m) {
        Item it;

        for (int i = 0; i < super.size(); i++) {
            it = super.get(i);
            if (it == m) {
                //super.delete(i);
                return i;
            }

        }

        return -1;
    }
}
