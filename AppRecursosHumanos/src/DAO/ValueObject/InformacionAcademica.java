/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.ValueObject;

/**
 *
 * @author jenny_izquierdo
 */
public class InformacionAcademica {
    
    //Atributes
    private int funcionarioId;
    private String Universidad;
    private String tituloEstudio;
    private String numeroIdentificacionFuncionario;

    public String getNumeroIdentificacionFuncionario() {
        return numeroIdentificacionFuncionario;
    }

    public void setNumeroIdentificacionFuncionario(String numeroIdentificacionFuncionario) {
        this.numeroIdentificacionFuncionario = numeroIdentificacionFuncionario;
    }

    public InformacionAcademica() {
    }

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getUniversidad() {
        return Universidad;
    }

    public void setUniversidad(String Universidad) {
        this.Universidad = Universidad;
    }

    public String getTituloEstudio() {
        return tituloEstudio;
    }

    public void setTituloEstudio(String tituloEstudio) {
        this.tituloEstudio = tituloEstudio;
    }
    
}
