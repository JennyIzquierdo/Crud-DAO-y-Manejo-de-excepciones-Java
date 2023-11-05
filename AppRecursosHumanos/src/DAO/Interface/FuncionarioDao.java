/*
 * CRUD OPERATIONS
 */
package DAO.Interface;

import DAO.ValueObject.Funcionario;
import java.util.ArrayList;

/**
 *
 * @author jenny_izquierdo
 */
public interface FuncionarioDao {
  public ArrayList<Funcionario> getAllFuncionarios();
   public Funcionario getFuncionario(int funcionarioId);
   public void createFuncionario(Funcionario funcionario);
   public void updateFuncionario(Funcionario funcionario, int IdUser);
   public int deleteFuncionario(int funcionarioId);  
   public ArrayList<String> getAllEstadoCivil();
   public ArrayList<String> getAllSexo();
   public ArrayList<String> getAllTipoId();
   public ArrayList<String> getAllTipoDirs();
   public int getIdNextFuncionario();
}
