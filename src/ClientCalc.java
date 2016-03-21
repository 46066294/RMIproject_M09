import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


/**Calculadora de part del client.
 * S'introdueix la ip del servidor i el port.
 * Tot seguit intoduir la operacio a realitzar
 *
 * Created by 46066294p on 02/03/16.
 */
public class ClientCalc {

    public static void main(String[] args) {

        InterfaceServerRMI calcRMI = null;

        String operacion = "";
        String strA = "";
        String strB = "";
        double a;
        double b;
        Scanner input = new Scanner(System.in);
        Scanner inputPort = new Scanner(System.in);
        System.out.println("M09 CALCULADORA INTERFACE METHOD INVOCATION (RMI)");
        System.out.println("Entra IP:");
        String strIP = input.nextLine();
        System.out.println("Entra port:");
        int port = inputPort.nextInt();
        System.out.println("Entra operacion:");
        operacion = input.nextLine();

        boolean flag = true;
        boolean sumaId = false;
        boolean restaId = false;
        boolean multIp = false;
        boolean divId = false;
        if(operacion.contains("+")){
            for(int i = 0; i < operacion.length(); i++){
                if(operacion.charAt(i) != '+' && flag){
                    strA = strA + operacion.charAt(i);
                }
                else if(operacion.charAt(i) == '+'){
                    flag = false;
                    sumaId = true;
                }
                else {
                    strB = strB + operacion.charAt(i);
                }
            }
        }else if(operacion.contains("-")){
            for(int i = 0; i < operacion.length(); i++){
                if(operacion.charAt(i) != '-' && flag){
                    strA = strA + operacion.charAt(i);
                }
                else if(operacion.charAt(i) == '-'){
                    flag = false;
                    restaId = true;
                }
                else {
                    strB = strB + operacion.charAt(i);
                }
            }
        }else if(operacion.contains("*")){
            for(int i = 0; i < operacion.length(); i++){
                if(operacion.charAt(i) != '*' && flag){
                    strA = strA + operacion.charAt(i);
                }
                else if(operacion.charAt(i) == '*'){
                    flag = false;
                    multIp = true;
                }
                else {
                    strB = strB + operacion.charAt(i);
                }
            }
        }else if(operacion.contains("/")){
            for(int i = 0; i < operacion.length(); i++){
                if(operacion.charAt(i) != '/' && flag){
                    strA = strA + operacion.charAt(i);
                }
                else if(operacion.charAt(i) == '/'){
                    flag = false;
                    divId = true;
                }
                else {
                    strB = strB + operacion.charAt(i);
                }
            }
        }

        a = Double.parseDouble(strA);
        b = Double.parseDouble(strB);

        try{
            System.out.println("Localitzant registre d'objectes remots ...");
            Registry registry = LocateRegistry.getRegistry(strIP, port);
            System.out.println("Obtenint l'objecte remot...\n");
            calcRMI = (InterfaceServerRMI) registry.lookup("generico");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(calcRMI!= null){
            System.out.println("...realitzant operacions");

            try{
                System.out.println("El resultat és:");
                if(sumaId)System.out.println("SUMA: " + a + " + " + b + " = " + calcRMI.suma(a, b));
                else if(restaId)System.out.println("RESTA: " + a + " - " + b + " = " + calcRMI.resta(a, b));
                else if(multIp)System.out.println("MULTIPLICACION: " + a + " * " + b + " = " + calcRMI.multiplicacion(a, b));
                else if(divId)System.out.println("DIVISION: " + a + " / " + b + " = " + calcRMI.division(a, b));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            finally {
                input.close();
                inputPort.close();
            }
        }
        System.out.println("\n...fi");

    }
}//ClientCalc
