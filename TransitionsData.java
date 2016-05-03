/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import javax.swing.JLabel;

/**
 *
 * @author Zarir Hamza
 */
public class TransitionsData {
    private String fState;
    private String nState;
    private String input;
    private JLabel actualObject;
    TransitionsData(){
    this.input = "Blank";
    }

    /**
     * @return the fState
     */
    public String getfState() {
        return fState;
    }

    /**
     * @param fState the fState to set
     */
    public void setfState(String fState) {
        this.fState = fState;
    }

    /**
     * @return the nState
     */
    public String getnState() {
        return nState;
    }

    /**
     * @param nState the nState to set
     */
    public void setnState(String nState) {
        this.nState = nState;
    }

    /**
     * @return the input
     */
    public String getInput() {
        return input;
    }

    /**
     * @param input the input to set
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * @return the actualObject
     */
    public JLabel getActualObject() {
        return actualObject;
    }

    /**
     * @param actualObject the actualObject to set
     */
    public void setActualObject(JLabel actualObject) {
        this.actualObject = actualObject;
    }

}