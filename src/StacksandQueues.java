import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StacksandQueues extends JFrame {
    private JTabbedPane Ingreso;
    private JButton imprimirDatosEstudianteButton;
    private JTextArea Datos;
    private JButton imprimirDatosButton;
    private JTextArea Datosquemados;
    private JTextField numtext;
    private JTextField idtext;
    private JTextField timetext;
    private JButton Ingresar;
    private JTextArea Datosingresados;
    private JTextField Quantumtext;
    private JButton cambiarValorButton;
    private JTextArea MostrarQuantum;
    private JButton ejecutarRoundRobinButton;
    private JTextArea RoundRobinText;
    private JButton mostrarHistorialButton;
    private JButton consultarÚltimoProcesoButton;
    private JTextArea Historialtext;
    private JButton eliminarHistorialButton;
    private int Quantum1=10;
    private int time=0;
    private int conmutaciones=0;
    public StacksandQueues() {
        super("hola");
        setContentPane(Ingreso);
        Queue<Proceso> proceso = new LinkedList<>();
        Stack<Proceso> Stack1= new Stack<>();
        Stack<Proceso> Stackaux = new Stack<>();
        Stack1.push(new Proceso("P6","1235325235",55));
        Stack1.push(new Proceso("P5","1725235325",45));
        Stack1.push(new Proceso("P4","1732523532",15));
        Stack1.push(new Proceso("P3","1545235235",50));
        Stack1.push(new Proceso("P2","1724423423",40));
        Stack1.push(new Proceso("P1","1724051592",20));
        imprimirDatosEstudianteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datos.setText("Dylan Clerque\n1724051592\nA00089629");

            }
        });
        imprimirDatosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datosquemados.setText(" ");
                if (!proceso.isEmpty()){
                    while(proceso.size()>0){
                       Stackaux.push(proceso.poll());


                    }
                    while(!Stackaux.isEmpty()){
                        Stack1.push(Stackaux.pop());
                    }
                }




                if (!Stack1.isEmpty()){
                    while(Stack1.size()>0){
                        Datosquemados.setText((Datosquemados.getText()+"\n"+Stack1.peek().toString()));
                        proceso.add(Stack1.pop());
                    }
                }

            }
        });
        Ingresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Datosingresados.setText(" ");
                Stack1.push(new Proceso(numtext.getText(), idtext.getText(),Integer.parseInt(timetext.getText())));
                Datosingresados.setText(Datosquemados.getText()+ "\n"+Stack1.peek().toString());
                Datosquemados.setText(Datosquemados.getText()+"\n"+Stack1.peek().toString());
                proceso.add(Stack1.pop());
            }
        });
        cambiarValorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Quantum1 = Integer.parseInt(Quantumtext.getText());
                MostrarQuantum.setText("El nuevo valor del Quantum es igual a "+Quantum1);
            }
        });
        ejecutarRoundRobinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoundRobinText.setText("");
                time=0;
                conmutaciones =0;
                RoundRobinText.setText("");
               while(!proceso.isEmpty()){
                   RoundRobinText.setText(RoundRobinText.getText()+"\n"+"Tiempo: "+ time +": "+proceso.peek().getNum()+" entra a ejecución.");
                   //proceso.peek().setTime(proceso.peek().getTime()-Quantum1);
                   if(proceso.peek().getTime()<=Quantum1){
                       time= time+proceso.peek().getTime();
                       proceso.peek().setTime(0);
                       RoundRobinText.setText(RoundRobinText.getText()+"\n"+"Tiempo: "+ time +": "+proceso.peek().getNum()+" termina ejecución.");
                       Stack1.push(proceso.poll());
                   }else if(proceso.size()<1) {
                       time= time+Quantum1;
                       proceso.peek().setTime(proceso.peek().getTime()-Quantum1);

                       RoundRobinText.setText(RoundRobinText.getText()+"\n"+"Tiempo: "+ time +": "+proceso.peek().getNum()+
                               "Continua ejecutandose. Pendiente por ejecutar: "+proceso.peek().getTime());
                       proceso.add(proceso.poll());

                   }
                   else{
                           time= time+Quantum1;
                           proceso.peek().setTime(proceso.peek().getTime()-Quantum1);

                           RoundRobinText.setText(RoundRobinText.getText()+"\n"+"Tiempo: "+ time +": "+proceso.peek().getNum()+" se conmuta. " +
                                   "Pendiente por ejecutar "+proceso.peek().getTime());
                           proceso.add(proceso.poll());
                       conmutaciones++;
                       }

               }
                        RoundRobinText.setText((RoundRobinText.getText()+"\nEstadísticas generadas:\nTotal tiempo de ejecución: "+time+"\n" +
                        "Total de conmutaciones: "+conmutaciones));
            }
        });
        mostrarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Historialtext.setText("Historial de procesos terminados:");
                if(!Stack1.isEmpty()) {
                    while (Stack1.size() > 0) {
                        Historialtext.setText(Historialtext.getText() + "\n" + "Num: " + Stack1.peek().getNum() + "\nCédula: " + Stack1.peek().getId());
                        Stackaux.push(Stack1.pop());
                    }
                } else if(!proceso.isEmpty()) {
                    while (proceso.size() > 0) {
                        Historialtext.setText(Historialtext.getText() + "\n" + "Num: " + proceso.peek().getNum() + "\nCédula: " + proceso.peek().getId());
                        Stack1.push(proceso.poll());
                    }} else if (!Stackaux.isEmpty()){
                    while(Stackaux.size()>0){
                    Stack1.push(Stackaux.pop());}
                    while(Stack1.size()>0){
                        Historialtext.setText(Historialtext.getText() + "\n" + "Num: " + Stack1.peek().getNum() + "\nCédula: " + Stack1.peek().getId());
                        Stackaux.push(Stack1.pop());
                    }
                    }

                else {
                    Historialtext.setText("No hay procesos por mostrar.");
                }
                }

        });
        consultarÚltimoProcesoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!proceso.isEmpty()) {
                    Historialtext.setText("Num: "+proceso.peek().getNum() + "\nCédula: " + proceso.peek().getId());
                }else if(!Stack1.isEmpty()){
                    Historialtext.setText("Num: "+Stack1.peek().getNum()+ "\nCédula: "+ Stack1.peek().getId());
                }else if(!Stackaux.isEmpty()){
                    while(Stackaux.size()>0){
                        Stack1.push(Stackaux.pop());}
                    Historialtext.setText("Num: "+Stack1.peek().getNum()+ "\nCédula: "+ Stack1.peek().getId());
                }else{
                    Historialtext.setText("No hay procesos por mostrar.");
                }
            }
        });
        eliminarHistorialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                proceso.clear();
                Stack1.clear();
                Stackaux.clear();
                Historialtext.setText("");
                Datosquemados.setText("");
                Datosingresados.setText("");
            }
        });
    }
}
