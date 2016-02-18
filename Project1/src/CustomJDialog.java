/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Grant
 */
public class CustomJDialog extends javax.swing.JDialog{
    
    CustomJDialog()
    {
        
    }
    
    @Override
    public void setTitle(String title)
    {
        super.setTitle(title + Constants.PROGRAM_NAME_SUFFIX);
    }
    
    @Override
    public void setVisible(boolean visible)
    {
        if(visible)
            super.pack();
        super.setVisible(visible);
    }
    
}
