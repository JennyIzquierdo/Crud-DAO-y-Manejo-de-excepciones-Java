/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implementation;

import DAO.DBConnection;
import DAO.Interface.InformacionAcademicaDao;
import DAO.ValueObject.InformacionAcademica;
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
public class InformacionAcademicaImpl  implements InformacionAcademicaDao{

     //Connection Params
    
    DBConnection connection = DBConnection.getInstance();
    Statement statement = null;
    ResultSet resultSet = null;
    ArrayList<InformacionAcademica> informacionesAcademicas;
    
    @Override
    public ArrayList<InformacionAcademica> getAllInformacionAcademica() {
         informacionesAcademicas = new ArrayList<>();
         try {
             statement = connection.getConnection().createStatement();
             // Execute query
             String sql = "SELECT inf.funcionario_id,inf.titulo_estudio,inf.universidad, f.numero_identificacion\n" +
                          "FROM InformacionAcademica inf\n" +
                          "INNER JOIN Funcionario f ON f.funcionario_id = inf.funcionario_id\n" +
                          "GROUP BY inf.funcionario_id,inf.titulo_estudio,inf.universidad, f.numero_identificacion\n" +
                          "ORDER BY inf.funcionario_id;";
             resultSet = statement.executeQuery(sql);
             
             // Storage the results
             while (resultSet.next())
             {
                InformacionAcademica _infoAcademica = new InformacionAcademica();
                _infoAcademica.setFuncionarioId(resultSet.getInt("funcionario_id"));
                _infoAcademica.setTituloEstudio(resultSet.getString("titulo_estudio"));
                _infoAcademica.setUniversidad(resultSet.getString("universidad"));
                _infoAcademica.setNumeroIdentificacionFuncionario(resultSet.getString("numero_identificacion"));
                informacionesAcademicas.add(_infoAcademica);
             }
             
         } catch (SQLException ex) {
             Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.getMessage());
         }

         return informacionesAcademicas;  
    }

    @Override
    public InformacionAcademica getInformacionAcademica(int funcionarioId) {
         InformacionAcademica _infoAcademica = new InformacionAcademica();
       try {
             statement = connection.getConnection().createStatement();
             // Execute query
             String sql = "SELECT * FROM InformacionAcademica WHERE funcionario_id = " + funcionarioId + " ;";
             resultSet = statement.executeQuery(sql);
             
             // Storage the results
             while (resultSet.next())
             {
                _infoAcademica.setFuncionarioId(resultSet.getInt("funcionario_id"));
                _infoAcademica.setTituloEstudio(resultSet.getString("titulo_estudio"));
                _infoAcademica.setUniversidad(resultSet.getString("universidad"));
             }
             
             
         } catch (SQLException ex) {
             Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
             System.out.println(ex.getMessage());
         }
       return _infoAcademica;
    }

   
    
}
