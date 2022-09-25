package controler;

import modulos.Equipos;
import modulos.Jugadores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EquiposBBDD {
    public static ArrayList<Equipos> readAllTeams(){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query = "SELECT * FROM equipo;";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            ArrayList<Equipos> equiposList = new ArrayList();
            while(rs.next()){
                int id = rs.getInt(1);
                String equipoName = rs.getString(2);

                equiposList.add(new Equipos(id,equipoName));
            }

            return equiposList;
        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
    }

    public static String readTeam(int id){
        Conexion conexion = new Conexion();
        PreparedStatement ps = null; //Damos el Qruery
        ResultSet rs = null;        //permite traer los datos en filas

        try(Connection con = conexion.get_connection()) {

            String query = "SELECT * FROM equipo where idequipo=" + id + ";";
            ps = con.prepareStatement(query);
            //Obtenemos los datos
            rs = ps.executeQuery();

            //Mientras que obtenga datos
            while(rs.next()){
                return rs.getString(2);
            }

        }catch(SQLException e){
            System.err.println(e);
            return null;
        }
        return null;
    }
}
