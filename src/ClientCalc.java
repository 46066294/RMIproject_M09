import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Created by 46066294p on 02/03/16.
 */
public class ClientCalc {

    public static void main(String[] args) {

        InterfaceServerRMI calcRMI = null;

        String operacion = "";
        String strA = "";
        String strB = "";
        int a;
        int b;
        Scanner input = new Scanner(System.in);
        System.out.println("M09 CALCULADORA INTERFACE METHOD INVOCATION (RMI)");
        System.out.println("Entra operacion:");
        operacion = input.nextLine();

        boolean flag = true;
        if(operacion.contains("+")){
            for(int i = 0; i < operacion.length(); i++){
                if(operacion.charAt(i) != '+' && flag){
                    strA = strA + operacion.charAt(i);
                }
                else if(operacion.charAt(i) == '+'){
                    flag = false;
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
                }
                else {
                    strB = strB + operacion.charAt(i);
                }
            }
        }

        a = Integer.parseInt(strA);
        b = Integer.parseInt(strB);

        try{
            System.out.println("Localitzant registre d'objectes remots ...");
            Registry registry = LocateRegistry.getRegistry("localhost", 5555);
            System.out.println("Obtenint l'objecte remot...");
            calcRMI = (InterfaceServerRMI) registry.lookup("generico");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(calcRMI!= null){
            System.out.println("Realitzant operacions");

            try{
                System.out.println("El resultat és:");
                System.out.println("SUMA: " + calcRMI.suma(a, b));
                System.out.println("RESTA: " + calcRMI.resta(a, b));
                System.out.println("MULTIPLICACION: " + calcRMI.multiplicacion(a, b));
                System.out.println("DIVISION: " + calcRMI.division(a, b));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n...fi");

    }
}//ClientCalc
