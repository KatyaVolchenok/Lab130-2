/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab130.pkg21;

import java.sql.Date;

/**
 *
 * @author Shwartskopff
 */
public class Documents {
    private final int idDoc;
    private final String nameDoc;
    private final String textDoc;
    private Date dateDoc;
    private int idAuthors;

    public Documents(int idDoc, String nameDoc, String textDoc, Date dateDoc,int idAuthors) {
        this.idDoc = idDoc;
        this.nameDoc = nameDoc;
        this.textDoc = textDoc;
        this.dateDoc = dateDoc;
        this.idAuthors = idAuthors;
    } 

    public int getIdDoc() {
        return idDoc;
    }

    public String getNameDoc() {
        return nameDoc;
    }

    public String getTextDoc() {
        return textDoc;
    }

    
    public Date getDateDoc() {
        return dateDoc;
    }

    public void setDateDoc(Date dateDoc) {
        this.dateDoc = dateDoc;
    }

    public int getIdAuthors() {
        return idAuthors;
    }

    public void setIdAuthors(int id_authors) {
        this.idAuthors = idAuthors;
    }

    @Override
    public String toString() {
        return "Documents{" + "idDoc=" + idDoc + ", nameDoc=" + nameDoc + ", textDoc=" + textDoc + ", dateDoc=" + dateDoc + ", id_authors=" + idAuthors + '}';
    }

}
