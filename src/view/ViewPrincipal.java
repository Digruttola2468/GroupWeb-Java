package view;


import controler.EquiposBBDD;
import controler.JugadoresBBDD;
import controler.PartidosBBDD;
import modulos.Equipos;
import modulos.Jugadores;
import modulos.Partidos;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ViewPrincipal extends JFrame implements ActionListener , ListSelectionListener, ItemListener {

    //PANEL:
    private JList listGetPartidos;
    private DefaultListModel listModelGetPartidos;
    private JLabel lb_totalPartidos;

    //PANEL:
    private JComboBox local,visitante;
    private DefaultListModel listModelGetEnfrentamientos;
    private JList listGetEnfrentamientos;
    private ArrayList<Partidos> listEnfrentamientosBBDD;

    //PANEL:
    private JComboBox comboBox ;
    private DefaultListModel listModel;

    private static final ArrayList<Partidos> listPartidosBBDD =  PartidosBBDD.readPartidos(); //
    private static final ArrayList<Equipos> listEquiposBBDD =  EquiposBBDD.readAllTeams();    //
    private static final ArrayList<Jugadores> listPlayerBBDD =  JugadoresBBDD.readAllPlayer();//



    public ViewPrincipal() {
        setLayout(new BorderLayout(10,20));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,500);
        setMinimumSize(new Dimension(800,500));
        setLocationRelativeTo(null);

        menu();

        addPanelGetPartidos();
        addPanelGetPartidosFaced();
        addPanelGetPartidosWinLose();

    }

    private void menu(){
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItemEquipo,menuItemJugadores;

        menuBar = new JMenuBar();

        menu = new JMenu("Views");
        menu.setMnemonic(KeyEvent.VK_C);
        /*menu.getAccessibleContext().setAccessibleDescription(
                "Mostrar Equipos");*/
        menuBar.add(menu);

        menuItemEquipo = new JMenuItem("Clubes/Players");
        menuItemJugadores= new JMenuItem("Add Partidos");

        menuItemJugadores.setActionCommand("Go to add Partidos");
        menuItemEquipo.setActionCommand("Go to Clubes/Players");

        menuItemJugadores.addActionListener(this);
        menuItemEquipo.addActionListener(this);

        menu.add(menuItemEquipo);
        menu.add(menuItemJugadores);

        this.setJMenuBar(menuBar);
    }

    //All partidos
    private void addPanelGetPartidos(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        Border factory = BorderFactory.createLineBorder(new Color(60,60,60),1);
        Border title = BorderFactory.createTitledBorder(factory,"Partidos");
        jPanel.setBorder(title);

        listModelGetPartidos = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        listGetPartidos = new JList(listModelGetPartidos);
        listGetPartidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //No te permite seleccionar varios de la lista
        listGetPartidos.setSelectedIndex(0);               //Empieza seleccionando el primero
        listGetPartidos.addListSelectionListener(this);
        listGetPartidos.setVisibleRowCount(10);             //Muestra los primeros 5 , despues Scroll

        JScrollPane listScrollPane = new JScrollPane(listGetPartidos);

        JButton btGetPartidos = new JButton("Get Partidos");
        btGetPartidos.setActionCommand("get all partidos");
        btGetPartidos.addActionListener(this);

        lb_totalPartidos = new JLabel();

        JCheckBox checkBox = new JCheckBox("Ver Clubes");
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if( e.getStateChange() == 2){
                    listModelGetPartidos.clear();
                    getOther(listPartidosBBDD,listModelGetPartidos);
                }
                else if(e.getStateChange() == 1) {
                    listModelGetPartidos.clear();
                    getOtherTeams(listPartidosBBDD,listModelGetPartidos);
                }
            }
        });

        Container container = new Container();
        container.setLayout(new FlowLayout());
        container.add(btGetPartidos);
        container.add(checkBox);
        container.add(lb_totalPartidos);

        jPanel.add(container, BorderLayout.PAGE_END);
        jPanel.add(listScrollPane, BorderLayout.CENTER);

        this.add(jPanel, BorderLayout.PAGE_START);
    }

    //Partios enfrentados
    private void addPanelGetPartidosFaced(){
        JPanel jPanel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        jPanel.setLayout(borderLayout);

        Border factory = BorderFactory.createLineBorder(new Color(60,60,60),1);
        Border title = BorderFactory.createTitledBorder(factory,"Enfrentamientos");
        jPanel.setBorder(title);

        local = new JComboBox();
        JLabel jLabel = new JLabel(" VS ");
        visitante = new JComboBox();


        for (int i = 0; i < listPlayerBBDD.size(); i++) {
            local.addItem(listPlayerBBDD.get(i).getNombre());
            visitante.addItem(listPlayerBBDD.get(i).getNombre());
        }
        visitante.setSelectedIndex(1);

        JCheckBox checkBox = new JCheckBox("ver clubes");
        checkBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if( e.getStateChange() == 2){
                    listModelGetPartidos.clear();

                    if(listEnfrentamientosBBDD == null)
                        getOther(listEnfrentamientosBBDD,listModelGetEnfrentamientos);
                }
                else if(e.getStateChange() == 1) {
                    listModelGetPartidos.clear();
                    if(listEnfrentamientosBBDD == null)
                        getOtherTeams(listEnfrentamientosBBDD,listModelGetEnfrentamientos);
                }
            }
        });

        JButton btAceptar = new JButton("Search");
        btAceptar.setActionCommand("search Enfrentamientos");
        btAceptar.addActionListener(this);

        Container component = new Container();
        component.setLayout(new FlowLayout());
        component.add(local);
        component.add(jLabel);
        component.add(visitante);
        component.add(checkBox);


        listModelGetEnfrentamientos = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        listGetEnfrentamientos = new JList(listModelGetEnfrentamientos);
        listGetEnfrentamientos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //No te permite seleccionar varios de la lista
        listGetEnfrentamientos.setSelectedIndex(0);               //Empieza seleccionando el primero
        listGetEnfrentamientos.addListSelectionListener(this);
        listGetEnfrentamientos.setVisibleRowCount(10);             //Muestra los primeros 5 , despues Scroll

        JScrollPane listScrollPane = new JScrollPane(listGetEnfrentamientos);

        jPanel.add(component, BorderLayout.PAGE_START);
        jPanel.add(listScrollPane, BorderLayout.CENTER);
        jPanel.add(btAceptar, BorderLayout.PAGE_END);

        this.add(jPanel,BorderLayout.LINE_START);
    }

    //Partidos Ganados/Perdidos
    private void addPanelGetPartidosWinLose(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        Border factory = BorderFactory.createLineBorder(new Color(60,60,60),1);
        Border title = BorderFactory.createTitledBorder(factory,"Win/Lose/Empate");
        jPanel.setBorder(title);

        listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        listGetPartidos = new JList(listModel);
        listGetPartidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); //No te permite seleccionar varios de la lista
        listGetPartidos.setSelectedIndex(0);               //Empieza seleccionando el primero
        listGetPartidos.addListSelectionListener(this);
        listGetPartidos.setVisibleRowCount(10);             //Muestra los primeros 5 , despues Scroll

        JScrollPane listScrollPane = new JScrollPane(listGetPartidos);

        Container container = new Container();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.LEADING);
        container.setLayout(flowLayout);

        comboBox = new JComboBox();
        for (int i = 0; i < listPlayerBBDD.size(); i++) {
            comboBox.addItem(listPlayerBBDD.get(i).getNombre());
        }

        JButton btGetPartidos = new JButton("Get Partidos a Player");
        btGetPartidos.setActionCommand("get partidos a player");
        btGetPartidos.addActionListener(this);

        JCheckBox cb_Win = new JCheckBox("Win");
        JCheckBox cb_Lose = new JCheckBox("Lose");
        JCheckBox cb_empate = new JCheckBox("Empate");

        container.add(comboBox);
        container.add(btGetPartidos);
        container.add(cb_Win);
        container.add(cb_Lose);
        container.add(cb_empate);

        jPanel.add(listScrollPane, BorderLayout.CENTER);
        jPanel.add(container, BorderLayout.PAGE_START);

        this.add(jPanel, BorderLayout.LINE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if("get all partidos".equals(e.getActionCommand())){
            listModelGetPartidos.clear();
            for (int i = 0; i < listPartidosBBDD.size(); i++) {
                int idNombreLocal = listPartidosBBDD.get(i).getNombreLocal();
                int resultadoLocal = listPartidosBBDD.get(i).getResultadoLocal();
                int resultadoVisitante = listPartidosBBDD.get(i).getResultadoVisitante();
                int idNombreVisitante = listPartidosBBDD.get(i).getNombreVisitante();

                String nombreLocalString = listPlayerBBDD.get(idNombreLocal-1).getNombre();
                String nombreVisitanteString = listPlayerBBDD.get(idNombreVisitante-1).getNombre();

                String resultado = nombreLocalString + "    " + resultadoLocal + " - " + resultadoVisitante + "     " + nombreVisitanteString;
                listModelGetPartidos.add(i,resultado);

                lb_totalPartidos.setText("Total partidos: " + listPartidosBBDD.size());
            }
        }
        if("search Enfrentamientos".equals(e.getActionCommand())){
            int idNombreLocal = local.getSelectedIndex() + 1;
            int idNombreVisitante = visitante.getSelectedIndex() + 1;
            listModelGetEnfrentamientos.clear();

            listEnfrentamientosBBDD = PartidosBBDD.readEnfrentamientos(idNombreLocal,idNombreVisitante);

            getOther(listEnfrentamientosBBDD,listModelGetEnfrentamientos);
        }
        if("get partidos a player".equals(e.getActionCommand())){
            int idNombre = comboBox.getSelectedIndex() + 1;
            listModel.clear();

            getResult(listPartidosBBDD, listModel,idNombre);
        }
        if("Go to add Partidos".equals(e.getActionCommand())){
            this.setVisible(false);
            new ViewAddPartidos(listPlayerBBDD,listEquiposBBDD,listPartidosBBDD).setVisible(true);
        }
        if("Go to Clubes/Players".equals(e.getActionCommand())){
            this.setVisible(false);
            new ViewGetEquipo(listPartidosBBDD,listEquiposBBDD,listPlayerBBDD).setVisible(true);
        }


    }

    private void getOther(ArrayList<Partidos> listPartidos, DefaultListModel model){
        for (int i = 0; i < listPartidos.size(); i++) {
                int idNombreLocal1 = listPartidos.get(i).getNombreLocal();
                int resultadoLocal = listPartidos.get(i).getResultadoLocal();
                int resultadoVisitante = listPartidos.get(i).getResultadoVisitante();
                int idNombreVisitante1 = listPartidos.get(i).getNombreVisitante();

                String nombreLocalString = listPlayerBBDD.get(idNombreLocal1-1).getNombre();
                String nombreVisitanteString = listPlayerBBDD.get(idNombreVisitante1-1).getNombre();

                String resultado = nombreLocalString + " " + resultadoLocal + " - " + resultadoVisitante + " " + nombreVisitanteString;
                model.addElement( resultado);

        }
    }

    private void getOtherTeams(ArrayList<Partidos> listPartidos, DefaultListModel model){
        for (int i = 0; i < listPartidos.size(); i++) {
            int idNombreLocal = listPartidos.get(i).getNombreLocal();
            int idEquipoLocal = listPartidos.get(i).getEquipoLocal();
            int resultadoLocal = listPartidos.get(i).getResultadoLocal();
            int resultadoVisitante = listPartidos.get(i).getResultadoVisitante();
            int idEquipoVisitante = listPartidos.get(i).getEquipoVisitante();
            int idNombreVisitante = listPartidos.get(i).getNombreVisitante();

            String nombreLocalString = listPlayerBBDD.get(idNombreLocal-1).getNombre();
            String equipoLocalString = listEquiposBBDD.get(idEquipoLocal-1).getNombreEquipo();
            String nombreVisitanteString = listPlayerBBDD.get(idNombreVisitante-1).getNombre();
            String equipoVisitanteString = listEquiposBBDD.get(idEquipoVisitante-1).getNombreEquipo();

            String resultado = nombreLocalString + "   " +
                    equipoLocalString + "   " +
                    resultadoLocal +  " - " +
                    resultadoVisitante + "   " +
                    equipoVisitanteString + "   " +
                    nombreVisitanteString;
            model.addElement(resultado);
        }
    }

    private void getResult(ArrayList<Partidos> listPartidos, DefaultListModel model,int idNombre){
        for (int i = 0; i < listPartidos.size(); i++) {
            if(listPartidos.get(i).getNombreLocal() == idNombre || listPartidos.get(i).getNombreVisitante() == idNombre){
                int idNombreLocal1 = listPartidos.get(i).getNombreLocal();
                int resultadoLocal = listPartidos.get(i).getResultadoLocal();
                int resultadoVisitante = listPartidos.get(i).getResultadoVisitante();
                int idNombreVisitante1 = listPartidos.get(i).getNombreVisitante();

                String nombreLocalString = listPlayerBBDD.get(idNombreLocal1-1).getNombre();
                String nombreVisitanteString = listPlayerBBDD.get(idNombreVisitante1-1).getNombre();

                String resultado = nombreLocalString + " " + resultadoLocal + " - " + resultadoVisitante + " " + nombreVisitanteString;
                model.addElement( resultado);
            }
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(e.getValueIsAdjusting()){
            System.out.println(listGetPartidos.getSelectedIndex());
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {

    }
}




