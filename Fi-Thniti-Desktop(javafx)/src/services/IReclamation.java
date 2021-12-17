/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
//import java.sql.ResultSet;
//import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author ouerf
 */
public interface IReclamation {
    
    public void ajouter_Reclamation(Reclamation p);
    
    public void modifier_Reclamation(Reclamation p);
    
    public void supprimer_Reclamation(Reclamation p);
    
    public List<Reclamation> getAllReclamation();
    
    public void traiter_Reclamation(Reclamation p);
    
    public int getNbr_Reclamation();
    
    
}
