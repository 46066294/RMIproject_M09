import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by 46066294p on 02/03/16.
 */
public interface InterfaceServerRMI extends Remote{

    double suma(double a, double b) throws RemoteException;
    double resta(double a, double b) throws RemoteException;
    double multiplicacion(double a, double b) throws RemoteException;
    double division(double a, double b) throws RemoteException;

}
