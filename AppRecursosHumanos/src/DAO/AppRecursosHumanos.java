package DAO;

import java.awt.EventQueue;
import GUI.FrmCrudFuncionarios;
/**
 *
 * @author jenny_izquierdo
 */

public class AppRecursosHumanos {
    public static void main(String[] args){
        
               EventQueue.invokeLater(() -> {
             //Start your application here
             //For example, create and display a login window
            FrmCrudFuncionarios frmFuncionarios = new FrmCrudFuncionarios();
            frmFuncionarios.setVisible(true);
        });

// Create a JFrame
        
    }
}
