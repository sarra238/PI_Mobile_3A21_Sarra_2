/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.commentaire;
import java.util.List;

/**
 *
 * @author lv
 */
public abstract class commEvI {
     public abstract void AjouterComm(commentaire a);
    public abstract List<commentaire> AfficherAllComm(int ide);
}
