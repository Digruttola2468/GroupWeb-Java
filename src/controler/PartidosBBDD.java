package controler;

import modulos.Partidos;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PartidosBBDD {

    public static ArrayList<Partidos> readPartidos(){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try{
            Connection con = conexion.get_connection();
            String query = "SELECT * FROM partidos;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            ArrayList<Partidos> partidosList = new ArrayList<>();

            //Mientras que obtenga datos
            while(rs.next()) {

                int id = rs.getInt(1);
                int nombreLocal = rs.getInt(2);
                int equipoLocal = rs.getInt(3);
                int resultadoLocal = rs.getInt(4);
                int resultadoVisitante = rs.getInt(5);
                int nombreVisitante = rs.getInt(6);
                int equipoVisitante = rs.getInt(7);

                partidosList.add(new Partidos(id,nombreLocal,equipoLocal,resultadoLocal,resultadoVisitante,equipoVisitante,nombreVisitante));
            }

            return partidosList;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }

    public static void readPlayerWinLocal(int idNombre){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query = "SELECT * FROM partidos where idNombreLocal = " + idNombre +" and resultadoLocal > resultadoVisitante;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            //Mientras que obtenga datos
            while(rs.next()){

                int idNombreLocal = rs.getInt(1);
                int resultadoLocal = rs.getInt(2);
                int resultadoVisitante = rs.getInt(3);
                int idNombreVisitante = rs.getInt(4);

                JugadoresBBDD.readPlayer(idNombreLocal);
                System.out.print(" ");
                System.out.print(resultadoLocal);
                System.out.print(" - ");
                System.out.print(resultadoVisitante);
                System.out.print(" ");
                JugadoresBBDD.readPlayer(idNombreVisitante);
                System.out.println();
            }

        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public static void readPlayerLoseLocal(String nombre){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query =
                    "SELECT idNombreLocal, resultadoLocal, resultadoVisitante, idNombreVisitante \n" +
                            "FROM partidos\n" +
                            "INNER JOIN jugadores ON partidos.idNombreLocal = jugadores.idjugadores\n" +
                            "WHERE jugadores.nombre = '" + nombre + "' and resultadoVisitante > resultadoLocal;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            //Mientras que obtenga datos
            while(rs.next()){
                int idNombreLocal = rs.getInt(1);
                int resultadoLocal = rs.getInt(2);
                int resultadoVisitante = rs.getInt(3);
                int idNombreVisitante = rs.getInt(4);

                JugadoresBBDD.readPlayer(idNombreLocal);
                System.out.print(" ");
                System.out.print(resultadoLocal);
                System.out.print(" - ");
                System.out.print(resultadoVisitante);
                System.out.print(" ");
                JugadoresBBDD.readPlayer(idNombreVisitante);
                System.out.println();
            }

        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public static void readPlayerWinVisitante(String nombre){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query =
                    "SELECT idNombreVisitante, resultadoVisitante, resultadoLocal, idNombreLocal \n" +
                            "FROM partidos\n" +
                            "INNER JOIN jugadores ON partidos.idNombreVisitante = jugadores.idjugadores\n" +
                            "WHERE jugadores.nombre = '" + nombre + "' and resultadoVisitante > resultadoLocal;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            //Mientras que obtenga datos
            while(rs.next()){
                int idNombreLocal = rs.getInt(1);
                int resultadoLocal = rs.getInt(2);
                int resultadoVisitante = rs.getInt(3);
                int idNombreVisitante = rs.getInt(4);

                JugadoresBBDD.readPlayer(idNombreLocal);
                System.out.print(" ");
                System.out.print(resultadoLocal);
                System.out.print(" - ");
                System.out.print(resultadoVisitante);
                System.out.print(" ");
                JugadoresBBDD.readPlayer(idNombreVisitante);
                System.out.println();
            }

        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public static void readPlayerLoseVisitante(String nombre){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query =
                    "SELECT idNombreVisitante, resultadoVisitante, resultadoLocal, idNombreLocal \n" +
                            "FROM partidos\n" +
                            "INNER JOIN jugadores ON partidos.idNombreVisitante = jugadores.idjugadores\n" +
                            "WHERE jugadores.nombre = '" + nombre + "' and resultadoLocal > resultadoVisitante;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            //Mientras que obtenga datos
            while(rs.next()){
                int idNombreLocal = rs.getInt(1);
                int resultadoLocal = rs.getInt(2);
                int resultadoVisitante = rs.getInt(3);
                int idNombreVisitante = rs.getInt(4);

                JugadoresBBDD.readPlayer(idNombreLocal);
                System.out.print(" ");
                System.out.print(resultadoLocal);
                System.out.print(" - ");
                System.out.print(resultadoVisitante);
                System.out.print(" ");
                JugadoresBBDD.readPlayer(idNombreVisitante);
                System.out.println();
            }

        }catch(SQLException e){
            System.err.println(e);
        }
    }

    public static ArrayList<Partidos> readEnfrentamientos(int idNombreLocal, int idNombreVisitante){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try{
            Connection con = conexion.get_connection();
            String query = "SELECT * FROM partidos WHERE idNombreLocal=" + idNombreLocal + " AND idNombreVisitante=" + idNombreVisitante + ";";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            ArrayList<Partidos> partidosList = new ArrayList<>();

            //Mientras que obtenga datos
            while(rs.next()) {

                int id = rs.getInt(1);
                int nombreLocal = rs.getInt(2);
                int equipoLocal = rs.getInt(3);
                int resultadoLocal = rs.getInt(4);
                int resultadoVisitante = rs.getInt(5);
                int nombreVisitante = rs.getInt(6);
                int equipoVisitante = rs.getInt(7);

                partidosList.add(new Partidos(id,nombreLocal,equipoLocal,resultadoLocal,resultadoVisitante,equipoVisitante,nombreVisitante));
            }

            query = "SELECT * FROM partidos WHERE idNombreLocal=" + idNombreVisitante + " AND idNombreVisitante=" + idNombreLocal + ";";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()) {

                int id = rs.getInt(1);
                int nombreLocal = rs.getInt(2);
                int equipoLocal = rs.getInt(3);
                int resultadoLocal = rs.getInt(4);
                int resultadoVisitante = rs.getInt(5);
                int nombreVisitante = rs.getInt(6);
                int equipoVisitante = rs.getInt(7);

                partidosList.add(new Partidos(id,nombreLocal,equipoLocal,resultadoLocal,resultadoVisitante,equipoVisitante,nombreVisitante));
            }

            return partidosList;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }

    public static boolean addPartido(int idNombreLocal,int idEquipoLocal, int resultadoLocal, int resultadoVisitante,int idEquipoVisitante, int idNombreVisitante){
        Conexion db_connect = new Conexion();
        try (Connection con = db_connect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO partidos (`idNombreLocal`, `idEquipoLocal`, `resultadoLocal`, `resultadoVisitante`, `idNombreVisitante`, `idEquipoVisitante`) VALUES (?, ?, ?, ?, ?, ?);";
                ps = con.prepareStatement(query);
                ps.setInt(1, idNombreLocal);
                ps.setInt(2, idEquipoLocal);
                ps.setInt(3, resultadoLocal);
                ps.setInt(4, resultadoVisitante);
                ps.setInt(5, idNombreVisitante);
                ps.setInt(6, idEquipoVisitante);
                ps.executeUpdate();
                System.out.println("Partido creado");
                JOptionPane.showMessageDialog(null,"Partido Creado");
                return true;
            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
}
