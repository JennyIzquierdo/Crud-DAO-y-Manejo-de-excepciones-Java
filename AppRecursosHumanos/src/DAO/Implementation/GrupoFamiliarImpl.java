/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implementation;

import DAO.DBConnection;
import DAO.Interface.GrupoFamiliarDao;
import DAO.ValueObject.GrupoFamiliar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jenny_izquierdo
 */
public class GrupoFamiliarImpl  implements GrupoFamiliarDao{

    //Connection Params
    
    DBConnection connection = DBConnection.getInstance();
    Statement statement = null;
    ResultSet resultSet = null;
    ArrayList<GrupoFamiliar> gruposFamiliares;
    
    
    
    
    @Override
    public ArrayList<GrupoFamiliar> getAllGrupoFamiliar() {
        gruposFamiliares = new ArrayList<>();
         try {
             statement = connection.getConnection().createStatement();
             // Execute query
             String sql = "select gp.funcionario_id, gp.miembro_nombres,gp.miembro_apellidos,\n" +
                            "       gp.miembro_numero_identificacion,gp.miembro_tipo_identificacion,\n" +
                            "       gp.miembro_telefono,gp.rol, f.numero_identificacion\n" +
                            "FROM GrupoFamiliar gp\n" +
                            "INNER JOIN Funcionario f ON f.funcionario_id = gp.funcionario_id\n" +
                            "GROUP BY gp.funcionario_id, gp.miembro_nombres,gp.miembro_apellidos,gp.miembro_numero_identificacion,gp.miembro_tipo_identificacion,gp.miembro_telefono,gp.rol,f.numero_identificacion\n" +
                            "ORDER BY gp.funcionario_id;";
             resultSet = statement.executeQuery(sql);
             
             // Storage the results
             while (resultSet.next())
             {
                GrupoFamiliar _gFamiliar = new GrupoFamiliar();
                _gFamiliar.setFuncionarioId(resultSet.getInt("funcionario_id"));
                _gFamiliar.setNombreCompleto(resultSet.getString("miembro_nombres") + " " +resultSet.getString("miembro_apellidos"));
                _gFamiliar.setNumeroIdentificacion(resultSet.getString("miembro_numero_identificacion"));
                _gFamiliar.setTipoIdentificacion(resultSet.getString("miembro_tipo_identificacion"));
                _gFamiliar.setTelefono(resultSet.getString("miembro_telefono"));
                _gFamiliar.setRol(resultSet.getString("rol"));
                _gFamiliar.setNumeroIdentificacionFuncionario(resultSet.getString("numero_identificacion"));//
                
                gruposFamiliares .add(_gFamiliar);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.getMessage());
         }

         return gruposFamiliares;  
    }

    @Override
    public GrupoFamiliar getGrupoFamiliar(int funcionarioId) {
       GrupoFamiliar _gFamiliar = new GrupoFamiliar();
       try {
             statement = connection.getConnection().createStatement();
             // Execute query
             String sql = "SELECT * FROM GrupoFamiliar WHERE funcionario_id = " + funcionarioId + " ;";
             resultSet = statement.executeQuery(sql);
             
             // Storage the results
             while (resultSet.next())
             {
                _gFamiliar.setFuncionarioId(resultSet.getInt("funcionario_id"));
                _gFamiliar.setNombreCompleto(resultSet.getString("miembro_nombres") + " " +resultSet.getString("miembro_apellidos"));
                _gFamiliar.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                _gFamiliar.setTipoIdentificacion(resultSet.getString("tipo_identificacion"));
                _gFamiliar.setTelefono(resultSet.getString("telefono"));
                _gFamiliar.setRol(resultSet.getString("rol"));
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.getMessage());
         }
       return _gFamiliar;
    }
    
}
