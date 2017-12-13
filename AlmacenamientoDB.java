package cronometro;


import java.io.*;
import java.util.Vector;
import javax.microedition.rms.*;


/**
 *
 * @author 
 */
public class AlmacenamientoDB {
    
    private String nombreDB="Aerobics stats";
    private RecordStore rs;
    
    private Vector v;
    
    AlmacenamientoDB()
    {
        abrirRecordStore();
    }
    AlmacenamientoDB(String nDB)
    {
        nombreDB= nDB;
        abrirRecordStore();
    }
    
    public void setVector(Vector v)
    {
        this.v=v;
    }
    
    public void abrirRecordStore(){
        try{
            rs = RecordStore.openRecordStore(nombreDB,true);
        }
        catch (RecordStoreException e){
            rs=null;
            System.out.println("Error al abrir el Record Store");

        }

    }
    
    public RecordStore getRecordStore()
    {
        return rs;
    }
    
    
    public void escribirDatos(String fecha,int tiempoTotal,int ppm){
        
        
        
        escribirRegistro(fecha,tiempoTotal,ppm);
        
    }
    
    
    public void escribirRegistro(String fecha,int tiempoTotal,int ppm){
        
        byte[] registro;
        ByteArrayOutputStream baos;
        DataOutputStream dos;
        try{
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeUTF(fecha);
            dos.writeInt(tiempoTotal);
            dos.writeInt(ppm);
            dos.flush();
            registro = baos.toByteArray();
            rs.addRecord(registro,0,registro.length);
            baos.close();
            dos.close();
        }
        catch (Exception e){
            System.out.println("Error al insertar registro");
        }
    }
    
    public void leerRegistros(){
        ByteArrayInputStream bais;
        DataInputStream dis;
        byte[] registro = new byte[20];
        try{
            bais = new ByteArrayInputStream(registro);
            dis = new DataInputStream(bais);
            for (int i=1;i<=rs.getNumRecords();i++){
                rs.getRecord(i,registro,0);
                //System.out.println("Registro "+i);
                //System.out.println("Fecha: "+dis.readUTF()+" Tiempo:"+dis.readInt()+ "Pulso: "+ dis.readInt());
                v.addElement(dis.readUTF());
                v.addElement(""+dis.readInt());
                v.addElement(""+dis.readInt());
                bais.reset();
            }
            bais.close();
            dis.close();
        }
        catch (Exception e){
            System.out.println("Error al leer los registros");
        }
        registro = null;
    }
    
    public void cerrarRecordStore(){
        try{
            rs.closeRecordStore();
        }
        catch (RecordStoreException e){
            System.out.println("Error al cerrar el Record Store");
        }
    }
    
    public void destruirRecordStore(){
            try{
                RecordStore.deleteRecordStore(nombreDB);
            }
            catch (RecordStoreException e){
            System.out.println("Error al eliminar el Record Store");
            }
    }
}
