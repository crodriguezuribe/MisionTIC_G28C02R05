import view.*;
import controller.*;

//Pruebas unicamente

public class App {
    
    public static void Console(){
        Controller controller = new Controller("jdbc:mysql://localhost:3306/proyectos");
        // Controller controller = new Controller("jdbc:sqlite:ProyectosConstruccion.db");
        controller.req1();
        controller.printReq1();
        controller.req2();
        controller.printReq2();
        controller.req3();
        controller.printReq3();
    }

    public static void Visual(){
        View principal = new View ("jdbc:mysql://localhost:3306/proyectos");
        // View principal = new View("jdbc:sqlite:ProyectosConstruccion.db");
        principal.setVisible(true);
    }

    //Pruebas locales
    public static void main(String[] args) throws Exception {
        Visual();
        // Console();
    }

}
