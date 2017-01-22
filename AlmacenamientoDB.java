package cronometro;


import java.io.*;
import javax.microedition.rms.*;


/**
 *
 * @author 
 */
public class AlmacenamientoDB {
    
    private String nombreDB="Aerobics stats";
    private RecordStore rs;
    
    
    
    AlmacenamientoDB(String nDB)
    {
        
        abrirRecordStore();
    }
    
    
    public void abrirRecordStore(){
        try{
            rs = RecordStore.openRecordStore(nombreDB,true);
        }
        catch (RecordStoreException e){
            System.out.println("Error al abrir el Record Store");

        }

    }
    
    
    public void escribirDatos(String fecha,int tiempoTotal,int tiempoMeta,int ppm){
        
        
        
        escribirRegistro("Antonio",555987654);
        
    }
    
    
    public void escribirRegistro(String entrada, long tel){
        byte[] registro;
        ByteArrayOutputStream baos;
        DataOutputStream dos;
        try{
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeUTF(entrada);
            dos.writeLong(tel);
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
        byte[] registro = new byte[75];
        try{
            bais = new ByteArrayInputStream(registro);
            dis = new DataInputStream(bais);
            for (int i=1;i<=rs.getNumRecords();i++){
                rs.getRecord(i,registro,0);
                System.out.println("Registro "+i);
                System.out.println("Nombre: "+dis.readUTF()+" Telefono:"+dis.readLong());
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
