/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.util.ArrayList;

/**
 *
 * @author jenny_izquierdo
 */
public class DBAtributes {
    
    // Attribute
    public ArrayList<String> Params;
    public DBAtributes() 
    {
        Params = LoadParams();
    };
    
    private ArrayList<String> LoadParams()
    {
        ArrayList<String> params = new ArrayList<>();
        
        // Credentials
        String dbName = "institucion_universitaria_digital_antioquia";
        String dbPassword = "123456";
        String dbHost = "localhost";
        String dbUser = "postgres";
        int dbPort = 5432;
        
        // Add params
        params.add(dbName);
        params.add(dbPassword);
        params.add(dbHost);
        params.add(dbUser);
        params.add(dbPort+"");
        return params;
    }
}
