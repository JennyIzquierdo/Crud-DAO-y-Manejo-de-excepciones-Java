/*
 * CRUD OPERATIONS
 */
package DAO.Interface;

import DAO.ValueObject.InformacionAcademica;
import java.util.ArrayList;

/**
 *
 * @author jenny_izquierdo
 */
public interface InformacionAcademicaDao {
  public ArrayList<InformacionAcademica> getAllInformacionAcademica();
   public InformacionAcademica getInformacionAcademica(int funcionarioId);
}
