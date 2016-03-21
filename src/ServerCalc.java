import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**Enllaça la peticio de la calculadora client
 * mitjanzant la interficie remota amb
 * l'identificador "generico".
 *
 * Created by 46066294p on 02/03/16.
 */
public class ServerCalc implements InterfaceServerRMI{

    public static void main(String[] args) {
        System.out.println("Creant registre d'objectes remots");
        Registry reg = null;

        try{
            reg = LocateRegistry.createRegistry(5555);
            System.out.println(reg.toString());

        }catch (Exception e){
            System.out.println("Error: No s'ha pogut crear el registre");
            e.printStackTrace();
        }

        System.out.println("Creant l'objecte servidor e inscribint-lo en el registre ...");
        InterfaceServerRMI serverObject = new ServerCalc();

        try{
            reg.rebind("generico",(InterfaceServerRMI) UnicastRemoteObject.exportObject(serverObject, 0));

        }catch (Exception e){
            System.out.println("No s'ha pogut inscriure l'objecte servidor");
            e.printStackTrace();
        }
    }//main


    @Override
    public double suma(double a, double b) throws RemoteException {
        return a+b;
    }

    @Override
    public double resta(double a, double b) throws RemoteException {
        return a-b;
    }

    @Override
    public double multiplicacion(double a, double b) throws RemoteException {
        return a*b;
    }

    @Override
    public double division(double a, double b) throws RemoteException {
        return a/b;
    }


}//ServerCalc
