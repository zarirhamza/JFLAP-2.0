/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package source;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author Zarir Hamza
 */
public class StatesData {
    private TransitionsData tData;
    private ArrayList<TransitionsData> tDataList = new ArrayList<>();
    private String name;
    private JLabel actualObject;
    private boolean iCondition;
    StatesData(){
        iCondition = false;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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

    /**
     * @return the iCondition
     */
    public boolean isiCondition() {
        return iCondition;
    }

    /**
     * @param iCondition the iCondition to set
     */
    public void setiCondition(boolean iCondition) {
        this.iCondition = iCondition;
    }

    /**
     * @return the tData
     */
    public TransitionsData gettData() {
        return tData;
    }

    /**
     * @param tData the tData to set
     */
    public void settData(TransitionsData tData) {
        this.tData = tData;
        tDataList.add(tData);
    }

    /**
     * @return the tDataList
     */
    public ArrayList<TransitionsData> gettDataList() {
        return tDataList;
    }

    /**
     * @param tDataList the tDataList to set
     */
    public void settDataList(ArrayList<TransitionsData> tDataList) {
        this.tDataList = tDataList;
    }

    
}
