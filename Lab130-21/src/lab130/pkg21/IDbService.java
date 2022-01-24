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
public interface IDbService extends AutoCloseable {
    boolean addAuthor(Authors author) throws DocumentException;
    boolean addDocument(Documents doc, Authors author) throws DocumentException;
    Documents[] findDocumentByAuthor(Authors author) throws DocumentException;
    Documents[] findDocumentByContent(String content) throws DocumentException;
    boolean deleteAuthor(Authors author) throws DocumentException;
    boolean deleteAuthor(int id) throws DocumentException;

}
