/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Implementation;

import DAO.DBConnection;
import DAO.Interface.FuncionarioDao;
import DAO.ValueObject.Funcionario;
import java.sql.PreparedStatement;
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
public class FuncionarioImpl implements FuncionarioDao {

    DBConnection connection = DBConnection.getInstance();
    Statement statement = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    ArrayList<Funcionario> funcionarios;

    @Override
    public ArrayList<Funcionario> getAllFuncionarios() {
        funcionarios = new ArrayList<>();
        try {
            statement = connection.getConnection().createStatement();
            // Execute query
            String sql = "SELECT * FROM Funcionario ORDER BY funcionario_id";
            resultSet = statement.executeQuery(sql);

            // Storage the results
            while (resultSet.next()) {
                Funcionario _funcionario = new Funcionario();
                _funcionario.setFuncionarioId(resultSet.getInt("funcionario_id"));
                _funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                _funcionario.setTipoIdentificacion(resultSet.getString("tipo_identificacion"));
                _funcionario.setNombres(resultSet.getString("nombres"));
                _funcionario.setApellidos(resultSet.getString("apellidos"));
                _funcionario.setEstadoCivil(resultSet.getString("estado_civil"));
                _funcionario.setSexo(resultSet.getString("sexo"));
                _funcionario.setDireccion(resultSet.getString("direccion"));
                _funcionario.setTelefono(resultSet.getString("telefono"));
                _funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
                funcionarios.add(_funcionario);
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return funcionarios;
    }

    @Override
    public Funcionario getFuncionario(int funcionarioId) {
        Funcionario _funcionario = new Funcionario();
        try {
            statement = connection.getConnection().createStatement();
            // Execute query
            String sql = "SELECT * FROM Funcionario WHERE funcionario_id = " + funcionarioId + " ;";
            resultSet = statement.executeQuery(sql);

            // Storage the results
            while (resultSet.next()) {
                _funcionario.setFuncionarioId(resultSet.getInt("funcionario_id"));
                _funcionario.setNumeroIdentificacion(resultSet.getString("numero_identificacion"));
                _funcionario.setTipoIdentificacion(resultSet.getString("tipo_identificacion"));
                _funcionario.setNombres(resultSet.getString("nombres"));
                _funcionario.setApellidos(resultSet.getString("apellidos"));
                _funcionario.setEstadoCivil(resultSet.getString("estado_civil"));
                _funcionario.setSexo(resultSet.getString("sexo"));
                _funcionario.setDireccion(resultSet.getString("direccion"));
                _funcionario.setTelefono(resultSet.getString("telefono"));
                _funcionario.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _funcionario;
    }

    @Override
    public void updateFuncionario(Funcionario funcionario, int IdUser) {
        try {
            // Execute query
            statement = connection.getConnection().createStatement();
            String updateSql = "UPDATE Funcionario SET nombres = ?, apellidos = ?, estado_civil = ?,"
                    + " sexo = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?"
                    + " WHERE funcionario_id = ?";
            preparedStatement = connection.getConnection().prepareStatement(updateSql);

            // Set the updated values
            preparedStatement.setString(1, funcionario.getNombres());
            preparedStatement.setString(2, funcionario.getApellidos());
            preparedStatement.setString(3, funcionario.getEstadoCivil());
            preparedStatement.setString(4, funcionario.getSexo());
            preparedStatement.setString(5, funcionario.getDireccion());
            preparedStatement.setString(6, funcionario.getTelefono());

            // Convert java.util.Date to java.sql.Date
            java.util.Date fechaNacimiento = funcionario.getFechaNacimiento();
            java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());

            preparedStatement.setDate(7, sqlFechaNacimiento);
            preparedStatement.setInt(8, IdUser);

            // Execute the update statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " rows updated.");

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int deleteFuncionario(int funcionarioId) {
        int rowsAffectedF  = 0;
        int rowsAffectedGF = 0;
        int rowsAffectedIA = 0;
        
        try {
            rowsAffectedGF = deleteGrupoFamiliar(funcionarioId);
            rowsAffectedIA = deleteInformacionAcademica(funcionarioId);
            
            
            // Execute query
            statement = connection.getConnection().createStatement();
            String deleteSql = "DELETE FROM Funcionario WHERE funcionario_id = ?";
            preparedStatement = connection.getConnection().prepareStatement(deleteSql);

            // Set the updated values
            preparedStatement.setInt(1, funcionarioId);

            // Execute the update statement
            rowsAffectedF = preparedStatement.executeUpdate();
            System.out.println(rowsAffectedF + " rows dropped.");

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rowsAffectedF + rowsAffectedGF + rowsAffectedIA;
    }
    
    private int deleteGrupoFamiliar( int funcionarioId)
    {
        int rowsAffected = 0;
        try {
            // Execute query
            statement = connection.getConnection().createStatement();
            String deleteSql = "DELETE FROM GrupoFamiliar WHERE funcionario_id = ?";
            preparedStatement = connection.getConnection().prepareStatement(deleteSql);

            // Set the updated values
            preparedStatement.setInt(1, funcionarioId);

            // Execute the update statement
            rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " rows dropped.");

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
    
     private int deleteInformacionAcademica( int funcionarioId)
    {
        int rowsAffected = 0;
        try {
            // Execute query
            statement = connection.getConnection().createStatement();
            String deleteSql = "DELETE FROM InformacionAcademica WHERE funcionario_id = ?";
            preparedStatement = connection.getConnection().prepareStatement(deleteSql);

            // Set the updated values
            preparedStatement.setInt(1, funcionarioId);

            // Execute the update statement
            rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " rows dropped.");

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowsAffected;
    }
     
   /* @Override
    public ArrayList<String> getAllTipoDocumento() {
        ArrayList<String> _tipoDocumento = new ArrayList<>();

        _tipoDocumento.add("CC");
        _tipoDocumento.add("TI");
        _tipoDocumento.add("CE");
        _tipoDocumento.add("RCN");
        _tipoDocumento.add("PP");
        return _tipoDocumento;
        
    }*/
    
    
    @Override
    public ArrayList<String> getAllEstadoCivil() {
        ArrayList<String> _estados = new ArrayList<>();

        _estados.add("Soltero/a");
        _estados.add("Casado/a");
        _estados.add("Divorciado/a");
        _estados.add("Viudo/a");
        return _estados;
    }
    
    

    @Override
    public ArrayList<String> getAllSexo() {
        ArrayList<String> _sexo = new ArrayList<>();

        _sexo.add("Mujer");
        _sexo.add("Hombre");

        return _sexo;
    }

    @Override
    public void createFuncionario(Funcionario funcionario) {
        try{
         // Execute query
            statement = connection.getConnection().createStatement();
            String insertSql = "INSERT INTO Funcionario (nombres,apellidos,numero_identificacion,tipo_identificacion,estado_civil,sexo,direccion,telefono,fecha_nacimiento)"
                             + "VALUES(?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.getConnection().prepareStatement(insertSql);

            // Set the updated values
            preparedStatement.setString(1, funcionario.getNombres());
            preparedStatement.setString(2, funcionario.getApellidos());
            preparedStatement.setString(3, funcionario.getNumeroIdentificacion());
            preparedStatement.setString(4, funcionario.getTipoIdentificacion());
            preparedStatement.setString(5, funcionario.getEstadoCivil());
            preparedStatement.setString(6, funcionario.getEstadoCivil());
            preparedStatement.setString(7, funcionario.getDireccion());
            preparedStatement.setString(8, funcionario.getTelefono());
             // Convert java.util.Date to java.sql.Date
            java.util.Date fechaNacimiento = funcionario.getFechaNacimiento();
            java.sql.Date sqlFechaNacimiento = new java.sql.Date(fechaNacimiento.getTime());
            
            preparedStatement.setDate(9, sqlFechaNacimiento);

            // Execute the update statement
            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " rows insert.");

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<String> getAllTipoId() {
        ArrayList<String> _tiposId = new ArrayList<>();
        /*try {
            statement = connection.getConnection().createStatement();
            // Execute query
            String sql = " SELECT DISTINCT tipo_identificacion FROM funcionario;";
            resultSet = statement.executeQuery(sql);

            // Storage the results
            while (resultSet.next()) {
                _tiposId.add(resultSet.getString("tipo_identificacion"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        _tiposId.add("CC");
        _tiposId.add("CE");
        _tiposId.add("PP");
        return _tiposId; 
        //return _tiposId;
    }

    @Override
    public ArrayList<String> getAllTipoDirs() {
        ArrayList<String> _dirs = new ArrayList<>();

        _dirs.add("Calle");
        _dirs.add("Carrera");
        _dirs.add("Diagonal");
        _dirs.add("Transversal");
        _dirs.add("Avenida");
        return _dirs;
    }

    @Override
    public int getIdNextFuncionario() {
       int id = 0;
        try {
            statement = connection.getConnection().createStatement();
            // Execute query
            String sql = " SELECT funcionario_id FROM funcionario WHERE funcionario_id = 1;";
            resultSet = statement.executeQuery(sql);

            // Storage the results
            while (resultSet.next()) {
                id = resultSet.getInt("funcionario_id");
            }

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioImpl.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR" + ex.getMessage());
        }
        return id == -1 ? 1: id+1;
    }
    

}
