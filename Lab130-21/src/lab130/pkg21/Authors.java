/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab130.pkg21;

/**
 *
 * @author Shwartskopff
 */
public class Authors {
    private int id;
    private String identific;
    private String note;

    public Authors(int id, String identific, String note) {
        this.id = id;
        this.identific = identific;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentific() {
        return identific;
    }

    public void setIdentific(String identific) {
        this.identific = identific;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Authors{" + "id=" + id + ", identific=" + identific + ", note=" + note + '}';
    }
}
