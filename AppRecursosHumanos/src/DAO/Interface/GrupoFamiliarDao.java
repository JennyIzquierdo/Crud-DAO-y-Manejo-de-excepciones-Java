/*
 * CRUD OPERATIONS
 */
package DAO.Interface;


import DAO.ValueObject.GrupoFamiliar;
import java.util.ArrayList;

/**
 *
 * @author jenny_izquierdo
 */
public interface GrupoFamiliarDao {
  public ArrayList<GrupoFamiliar> getAllGrupoFamiliar();
   public GrupoFamiliar getGrupoFamiliar(int funcionarioId);
}
