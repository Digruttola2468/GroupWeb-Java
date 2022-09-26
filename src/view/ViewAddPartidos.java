package view;

import controler.EquiposBBDD;
import controler.JugadoresBBDD;
import controler.PartidosBBDD;
import modulos.Equipos;
import modulos.Jugadores;
import modulos.Partidos;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ViewAddPartidos extends JFrame implements ActionListener{

    //PANEL: Add partidos
    private JTextField txtResultadoLocal,txtResultadoVisitante;
    private JComboBox cb_nameLocal,cb_equipoLocal,cb_nameVisitante,cb_equipoVisitante;
    private JButton btGuardar;

    //PANEL:
    private JTextField txtNombreClub;

    //PANEL:
    private JTextField txtApellido, txtName;

    private static ArrayList<Jugadores> playersListBBDD; //
    private static ArrayList<Equipos> teamsListBBDD; //
    private static ArrayList<Partidos> listPartidosBBDD;

    public ViewAddPartidos(ArrayList<Jugadores> listPlayer, ArrayList<Equipos> listEquipos, ArrayList<Partidos> listPartidos){
        playersListBBDD = listPlayer;
        teamsListBBDD = listEquipos;
        listPartidosBBDD = listPartidos;

        this.setLayout(new BorderLayout());
        this.setTitle("Guardar/Agregar");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);



        panelAddPartidos();
        panelAddJugador();
        panelAddEquipo();

        menu();
    }

    private void menu(){
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItemHome, menuItemGetEquipo;

        menuBar = new JMenuBar();

        menu = new JMenu("Views");
        menu.setMnemonic(KeyEvent.VK_C);
        /*menu.getAccessibleContext().setAccessibleDescription(
                "Mostrar Equipos");*/
        menuBar.add(menu);

        menuItemHome = new JMenuItem("Home");
        menuItemGetEquipo = new JMenuItem("Clubes/Players");

        menuItemHome.setActionCommand("back");
        menuItemGetEquipo.setActionCommand("Go to Clubes/Players");

        menuItemGetEquipo.addActionListener(this);
        menuItemHome.addActionListener(this);

        menu.add(menuItemHome);
        menu.add(menuItemGetEquipo);

        this.setJMenuBar(menuBar);
    }

    private void panelAddPartidos(){
        //Layout
        FlowLayout experimentLayout = new FlowLayout();

        //Panel agregar partidos
        JPanel panelPartido = new JPanel();
        panelPartido.setLayout(experimentLayout);
        experimentLayout.setAlignment(FlowLayout.CENTER);

        String arrayPlayer[] = new String[playersListBBDD.size()];
        for (int i = 0; i < playersListBBDD.size(); i++) {
            arrayPlayer[i] = playersListBBDD.get(i).getApellido();
        }

        String arrayTeams[] = new String[teamsListBBDD.size()];
        for (int i = 0; i < teamsListBBDD.size(); i++) {
            arrayTeams[i] = teamsListBBDD.get(i).getNombreEquipo();
        }

        txtResultadoLocal = new JTextField("",3);
        txtResultadoVisitante = new JTextField("",3);
        cb_nameLocal = new JComboBox(arrayPlayer);
        cb_nameVisitante = new JComboBox(arrayPlayer);
        cb_equipoLocal = new JComboBox(arrayTeams);
        cb_equipoVisitante = new JComboBox(arrayTeams);
        btGuardar = new JButton("Guardar");
        btGuardar.setActionCommand("Guardar Partido");
        btGuardar.addActionListener(this);


        panelPartido.add(btGuardar);
        panelPartido.add(cb_nameVisitante);
        panelPartido.add(cb_equipoVisitante);
        panelPartido.add(txtResultadoVisitante);
        panelPartido.add(txtResultadoLocal);
        panelPartido.add(cb_equipoLocal);
        panelPartido.add(cb_nameLocal);

        panelPartido.setComponentOrientation(
                ComponentOrientation.RIGHT_TO_LEFT);

        Border factory =  BorderFactory.createLineBorder(new Color(66,66,66),1);
        Border title = BorderFactory.createTitledBorder(factory,"Agregar Partido");
        panelPartido.setBorder(title);

        this.add(panelPartido, BorderLayout.PAGE_START);
    }

    private void panelAddEquipo(){
        //Panel equipo
        JPanel panelEquipo = new JPanel();
        panelEquipo.setLayout(new BorderLayout());

        Container component = new Container();
        component.setLayout(new FlowLayout());

        JLabel lbEquipo = new JLabel("Club: ");
        txtNombreClub = new JTextField("",12);

        JButton jButton = new JButton("Guardar Club");
        jButton.addActionListener(this);
        jButton.setActionCommand("Guardar Club");

        lbEquipo.setForeground(new Color(0,0,0));
        lbEquipo.setFont(new Font(Font.MONOSPACED, Font.PLAIN,16));

        component.add(lbEquipo);
        component.add(txtNombreClub);

        panelEquipo.add(component, BorderLayout.PAGE_START);
        panelEquipo.add(jButton, BorderLayout.PAGE_END);

        Border factory = BorderFactory.createLineBorder(new Color(66,66,66), 1);
        Border title = BorderFactory.createTitledBorder(factory,"Agregar Equipo");

        panelEquipo.setBorder(title);
        this.add(panelEquipo, BorderLayout.LINE_END);
    }

    private void panelAddJugador() {
        //Panel Jugadores
        JPanel panelJugadores = new JPanel();
        panelJugadores.setLayout(new FlowLayout());

        Container container = new Container();
        container.setLayout(new FlowLayout());

        Container container1 = new Container();
        container1.setLayout(new FlowLayout());

        JLabel lbName = new JLabel("Nombre: ");
        container.add(lbName);

        txtName = new JTextField(7);
        container.add(txtName);


        JLabel lbApellido = new JLabel("Apellido: ");
        container1.add(lbApellido);

        txtApellido = new JTextField(7);
        container1.add(txtApellido);

        JButton jButton = new JButton("Guardar Jugador");
        jButton.addActionListener(this);
        jButton.setActionCommand("Guardar Jugador");

        lbName.setForeground(new Color(0,0,0));
        lbName.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));

        lbApellido.setForeground(new Color(0,0,0));
        lbApellido.setFont(new Font(Font.MONOSPACED,Font.PLAIN,16));

        panelJugadores.add(container);
        panelJugadores.add(container1);
        panelJugadores.add(jButton);

        Border factory = BorderFactory.createLineBorder(new Color(66,66,66), 1);
        Border title = BorderFactory.createTitledBorder(factory,"Agregar Jugador");

        panelJugadores.setBorder(title);
        this.add(panelJugadores);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if( ("Guardar Partido").equals(e.getActionCommand()) ){
            boolean isOK = false;

            int idnombreLocal = cb_nameLocal.getSelectedIndex() + 1;
            int idEquipoLocal = cb_equipoLocal.getSelectedIndex() + 1;

            int equipoVisitante = cb_equipoVisitante.getSelectedIndex() + 1;
            int nombreVisitante = cb_nameVisitante.getSelectedIndex() + 1;

            int resultadoLocal = 0;
            int resultadoVisitante = 0;
            if(!txtResultadoVisitante.getText().equals("") && !txtResultadoLocal.getText().equals("")){
                resultadoLocal = Integer.parseInt(txtResultadoLocal.getText());
                resultadoVisitante = Integer.parseInt(txtResultadoVisitante.getText());
                if(idnombreLocal != nombreVisitante){
                    isOK = true;
                }
            }else
                isOK = false;

            if(isOK)
                PartidosBBDD.addPartido(idnombreLocal,idEquipoLocal,resultadoLocal,resultadoVisitante,equipoVisitante,nombreVisitante);

        }
        if( "Guardar Club".equals(e.getActionCommand())){
            String nombre = txtNombreClub.getText().toString();
            if(!nombre.equals("")) {
                EquiposBBDD.addEquipo(nombre);
            }
        }
        if( "Guardar Jugador".equals(e.getActionCommand())){
            String nombre = txtName.getText().toString();
            String apellido = txtApellido.getText().toString();
            if(!nombre.equals("") && !apellido.equals("")) {
                JugadoresBBDD.addPlayer(nombre,apellido);
            }
        }
        if ("back".equals(e.getActionCommand())){
            this.setVisible(false);
            new ViewPrincipal().setVisible(true);
        }
        if("Go to Clubes/Players".equals(e.getActionCommand())){
            this.setVisible(false);
            new ViewGetEquipo(listPartidosBBDD,teamsListBBDD,playersListBBDD).setVisible(true);
        }
    }
}
