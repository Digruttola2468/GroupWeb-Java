package view;

import modulos.Equipos;
import modulos.Jugadores;
import modulos.Partidos;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ViewGetEquipo extends JFrame implements ActionListener{

    private static  ArrayList<Partidos> listPartidosBBDD = new ArrayList<>(); //
    private static  ArrayList<Equipos> listEquiposBBDD = new ArrayList<>();    //
    private static  ArrayList<Jugadores> listPlayerBBDD = new ArrayList<>();//

    //PANEL:
    private DefaultListModel listModelEquipos;

    //PANEL:
    private DefaultListModel listModelPlayer;

    //PANEL:
    private JTextArea area;
    private JComboBox comboBoxPlayer,comboBoxTeam;

    public ViewGetEquipo(ArrayList<Partidos> listPartidos, ArrayList<Equipos> listEquipos, ArrayList<Jugadores> listPlayer){
        listPartidosBBDD = listPartidos;
        listEquiposBBDD = listEquipos;
        listPlayerBBDD = listPlayer;

        this.setLocationRelativeTo(null);
        this.setSize(700,500);
        this.setResizable(true);
        this.setDefaultCloseOperation(3);
        this.setTitle("Equipo");
        this.setMinimumSize(new Dimension(680,400));
        this.setLayout(new BorderLayout());

        addPanelGetsEquipos();
        addPanelGetsPlayers();
        addPanelPlayerUseTeam();
        menu();
    }

    private void menu(){
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItemHome,menuItemAddPartidos;

        menuBar = new JMenuBar();

        menu = new JMenu("Views");
        menu.setMnemonic(KeyEvent.VK_C);
        /*menu.getAccessibleContext().setAccessibleDescription(
                "Mostrar Equipos");*/
        menuBar.add(menu);

        menuItemHome = new JMenuItem("Home");
        menuItemAddPartidos= new JMenuItem("Add Partidos");

        menuItemHome.setActionCommand("back");
        menuItemAddPartidos.setActionCommand("Go to Add Partidos");

        menuItemAddPartidos.addActionListener(this);
        menuItemHome.addActionListener(this);

        menu.add(menuItemHome);
        menu.add(menuItemAddPartidos);

        this.setJMenuBar(menuBar);
    }

    private void addPanelGetsEquipos(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Border factory = BorderFactory.createLineBorder(new Color(60,60,60), 1);
        Border title = BorderFactory.createTitledBorder(factory,"Equipos");
        panel.setBorder(title);

        JButton button = new JButton("Obtener Equipos");
        button.setActionCommand("get Equipos");
        button.addActionListener(this);

        listModelEquipos = new DefaultListModel();

        JList list = new JList(listModelEquipos);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(10);

        JScrollPane listScrollPane = new JScrollPane(list);

        Container cont = new Container();
        cont.setLayout(new FlowLayout());

        cont.add(button);

        panel.add(cont, BorderLayout.PAGE_START);
        panel.add(listScrollPane, BorderLayout.CENTER);

        this.add(panel, BorderLayout.PAGE_START);
    }

    private void addPanelGetsPlayers(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Border factory = BorderFactory.createLineBorder(new Color(60,60,60), 1);
        Border title = BorderFactory.createTitledBorder(factory,"Jugadores");
        panel.setBorder(title);

        JButton button = new JButton("Obtener Jugadores");
        button.setActionCommand("get Players");
        button.addActionListener(this);
        listModelPlayer = new DefaultListModel();

        JList list = new JList(listModelPlayer);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(5);

        JScrollPane listScrollPane = new JScrollPane(list);

        Container cont = new Container();
        cont.setLayout(new FlowLayout());

        cont.add(button);

        panel.add(cont, BorderLayout.PAGE_START);
        panel.add(listScrollPane, BorderLayout.CENTER);

        this.add(panel, BorderLayout.LINE_START);
    }

    private void addPanelPlayerUseTeam(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        Border factory = BorderFactory.createLineBorder(new Color(60,60,60), 1);
        Border title = BorderFactory.createTitledBorder(factory,"Jugadores/equipos");
        panel.setBorder(title);

        JButton button = new JButton("Obtener resultado");
        button.addActionListener(this);
        button.setActionCommand("get player use team");

        comboBoxPlayer = new JComboBox();
        comboBoxTeam = new JComboBox();

        for (int i = 0; i < listPlayerBBDD.size(); i++) {
            comboBoxPlayer.addItem(listPlayerBBDD.get(i).getNombre());

        }
        comboBoxTeam.addItem("---");
        for (int i = 0; i < listEquiposBBDD.size(); i++) {
            comboBoxTeam.addItem(listEquiposBBDD.get(i).getNombreEquipo());
        }

        area = new JTextArea();

        JScrollPane listScrollPane = new JScrollPane(area);

        Container cont = new Container();
        cont.setLayout(new FlowLayout());

        cont.add(comboBoxPlayer);
        cont.add(comboBoxTeam);
        cont.add(button);


        panel.add(cont, BorderLayout.PAGE_START);
        panel.add(listScrollPane, BorderLayout.CENTER);

        this.add(panel, BorderLayout.LINE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("get Equipos".equals(e.getActionCommand())){
            listModelEquipos.clear();
            for (int i = 0; i < listEquiposBBDD.size(); i++) {
                int id = listEquiposBBDD.get(i).getIdequipo();
                String club = listEquiposBBDD.get(i).getNombreEquipo();

                String result = id + "." + "   " + club;
                listModelEquipos.addElement(result);
            }
        }
        if("get Players".equals(e.getActionCommand())){
            listModelPlayer.clear();
            for (int i = 0; i < listPlayerBBDD.size(); i++) {
                int id = listPlayerBBDD.get(i).getIdjugadores();
                String nombre = listPlayerBBDD.get(i).getNombre();
                String apellido = listPlayerBBDD.get(i).getApellido();

                String result = id + "." + "   " + nombre + "   " + apellido;
                listModelPlayer.addElement(result);
            }
        }
        if("back".equals(e.getActionCommand())) {
            this.setVisible(false);
            new ViewPrincipal().setVisible(true);
        }
        if("Go to Add Partidos".equals(e.getActionCommand())){
            this.setVisible(false);
            new ViewAddPartidos(listPlayerBBDD,listEquiposBBDD,listPartidosBBDD).setVisible(true);
        }

        if("get player use team".equals(e.getActionCommand())){
            area.setText("");
            area.append("------------------------------ \n");
            area.append("Jugador: ");

            int idNombre = comboBoxPlayer.getSelectedIndex() + 1;
            int idEquipo = comboBoxTeam.getSelectedIndex();

            for (int i = 0; i < listPlayerBBDD.size(); i++) {
                if(listPlayerBBDD.get(i).getIdjugadores() == idNombre){
                    area.append(listPlayerBBDD.get(i).getNombre() + " " + listPlayerBBDD.get(i).getApellido() + "\n");
                }
            }


            if(idEquipo == 0){
                Set<Integer> setList = new HashSet<>();
                System.out.println("No seleccionaste ningun equipo");

                for (int i = 0; i < listPartidosBBDD.size(); i++) {
                    if(listPartidosBBDD.get(i).getNombreLocal() == idNombre){
                        System.out.println(listPartidosBBDD.get(i).getEquipoLocal());
                        setList.add(listPartidosBBDD.get(i).getEquipoLocal());
                    }
                    if(listPartidosBBDD.get(i).getNombreVisitante() == idNombre){
                        System.out.println(listPartidosBBDD.get(i).getEquipoVisitante());
                        setList.add(listPartidosBBDD.get(i).getEquipoVisitante());
                    }
                }

                int partidosTotal = 0;
                int partidosGanadosTotal = 0;
                int partidosPerdidosTotal = 0;
                int partidosEmpatadosTotal = 0;

                int longitud = setList.size();
                for (int k = 0; k < longitud; k++) {
                    area.append("Equipo: ");

                    for (int i = 0; i < listEquiposBBDD.size(); i++) {
                        if(setList.contains(listEquiposBBDD.get(i).getIdequipo())){
                            area.append(listEquiposBBDD.get(i).getNombreEquipo() + "\n");
                            idEquipo = listEquiposBBDD.get(i).getIdequipo();
                            setList.remove(idEquipo);
                            i = listEquiposBBDD.size();
                        }
                    }

                    int partidosJugados = 0;
                    int partidosGanados = 0;
                    int partidosEmpatados = 0;
                    for (int i = 0; i < listPartidosBBDD.size(); i++) {

                        //Partidos Jugados
                        if( (listPartidosBBDD.get(i).getNombreLocal() == idNombre && listPartidosBBDD.get(i).getEquipoLocal() == idEquipo) ||
                                (listPartidosBBDD.get(i).getNombreVisitante() == idNombre && listPartidosBBDD.get(i).getEquipoVisitante() == idEquipo) ) {
                            partidosJugados++;

                            if(listPartidosBBDD.get(i).getNombreLocal() == idNombre && listPartidosBBDD.get(i).getEquipoLocal() == idEquipo){
                                if( listPartidosBBDD.get(i).getResultadoLocal() > listPartidosBBDD.get(i).getResultadoVisitante() ){
                                    partidosGanados++;
                                }
                            }
                            if(listPartidosBBDD.get(i).getNombreVisitante() == idNombre && listPartidosBBDD.get(i).getEquipoVisitante() == idEquipo){
                                if( listPartidosBBDD.get(i).getResultadoVisitante() > listPartidosBBDD.get(i).getResultadoLocal() ){
                                    partidosGanados++;
                                }
                            }
                            if( listPartidosBBDD.get(i).getResultadoVisitante() == listPartidosBBDD.get(i).getResultadoLocal() ){
                                partidosEmpatados++;
                            }

                        }
                    }

                    int partidosPerdidos = 0;

                    partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;

                    partidosTotal += partidosJugados;
                    partidosPerdidosTotal += partidosPerdidos;
                    partidosGanadosTotal += partidosGanados;
                    partidosEmpatadosTotal += partidosEmpatados;

                    area.append("\n");
                    area.append("Jugados: " + partidosJugados + " partidos \n");
                    area.append("Ganados: " + partidosGanados + "\n");
                    area.append("Perdidos: " + partidosPerdidos + "\n");
                    area.append("Empatados: " + partidosEmpatados + "\n");
                    area.append("------------------------------ \n");


                }
                area.append("Total partidos: " + partidosTotal + "\n");
                area.append("TOTAL GANADOS: " + partidosGanadosTotal+ "\n");
                area.append("TOTAL EMPATADOS: " + partidosEmpatadosTotal+ "\n");
                area.append("TOTAL PERDIDOS: " + partidosPerdidosTotal+ "\n");
            }else {
                area.append("Equipo: ");
                for (int i = 0; i < listEquiposBBDD.size(); i++) {
                    if(listEquiposBBDD.get(i).getIdequipo() == idEquipo){
                        area.append(listEquiposBBDD.get(i).getNombreEquipo() + "\n");
                    }
                }

                int partidosJugados = 0;
                int partidosGanados = 0;
                int partidosEmpatados = 0;
                for (int i = 0; i < listPartidosBBDD.size(); i++) {

                    //Partidos Jugados
                    if( (listPartidosBBDD.get(i).getNombreLocal() == idNombre && listPartidosBBDD.get(i).getEquipoLocal() == idEquipo) ||
                            (listPartidosBBDD.get(i).getNombreVisitante() == idNombre && listPartidosBBDD.get(i).getEquipoVisitante() == idEquipo) ) {
                        partidosJugados++;

                        if(listPartidosBBDD.get(i).getNombreLocal() == idNombre && listPartidosBBDD.get(i).getEquipoLocal() == idEquipo){
                            if( listPartidosBBDD.get(i).getResultadoLocal() > listPartidosBBDD.get(i).getResultadoVisitante() ){
                                partidosGanados++;
                            }
                        }
                        if(listPartidosBBDD.get(i).getNombreVisitante() == idNombre && listPartidosBBDD.get(i).getEquipoVisitante() == idEquipo){
                            if( listPartidosBBDD.get(i).getResultadoVisitante() > listPartidosBBDD.get(i).getResultadoLocal() ){
                                partidosGanados++;
                            }
                        }
                        if( listPartidosBBDD.get(i).getResultadoVisitante() == listPartidosBBDD.get(i).getResultadoLocal() ){
                            partidosEmpatados++;
                        }

                    }
                }

                int partidosPerdidos = partidosJugados - partidosGanados - partidosEmpatados;

                area.append("\n");
                area.append("Jugados: " + partidosJugados + " partidos \n");
                area.append("Ganados: " + partidosGanados + "\n");
                area.append("Perdidos: " + partidosPerdidos + "\n");
                area.append("Empatados: " + partidosEmpatados + "\n");
                area.append("------------------------------ \n");
            }
        }
    }
}