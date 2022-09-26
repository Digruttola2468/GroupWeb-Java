package controler;

import modulos.Jugadores;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JugadoresBBDD {
    public static ArrayList<Jugadores> readAllPlayer(){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query = "SELECT * FROM jugadores;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            ArrayList<Jugadores> jugadores = new ArrayList();
            while(rs.next()){
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String apellido = rs.getString(3);

                jugadores.add(new Jugadores(id,name,apellido));
            }

            return jugadores;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }

    public static String readPlayer(int id){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query = "SELECT * FROM jugadores where idjugadores=" + id + ";";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            if(rs.next())
                return rs.getString(2);

        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
        return null;
    }

    public static void addPlayer(String name,String apellido){
        Conexion db_connect = new Conexion();
        try (Connection con = db_connect.get_connection()) {
            PreparedStatement ps = null;
            try {
                String query = "INSERT INTO partidos (`nombre`, `apellido`) VALUES (?, ?);";
                ps = con.prepareStatement(query);
                ps.setString(1, name);
                ps.setString(2, apellido);
                ps.executeUpdate();
                System.out.println("Jugador creado");
                JOptionPane.showMessageDialog(null,"Jugador Creado");

            } catch (SQLException e) {
                System.out.println(e);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
