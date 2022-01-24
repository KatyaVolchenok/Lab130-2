/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab130.pkg21;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Shwartskopff
 */
public class DbServer implements IDbService{
    private static DbServer dbServer;
    private Connection con;
    private Statement stm;
    private ResultSet rs;
    
    private DbServer(){
    }
    
    
     public static DbServer getDbServer() {
        if (dbServer == null) dbServer = new DbServer();
        return dbServer;
    }
   
     @Override
    public boolean addAuthor(Authors author) throws DocumentException {
         Setting setting = new Setting();
            try( Connection connection = DriverManager.getConnection(
                setting.getValue(Setting.URL), 
                setting.getValue(Setting.USER), 
                setting.getValue(Setting.PASS))){
            if(author.getId() != 0 
                    && author.getIdentific()!= null 
                    && !author.getIdentific().trim().equals("") 
                    && author.getNote() != null){
                if (stm == null) stm = connection.createStatement();
               stm.executeUpdate("INSERT INTO Authors (id, identific, note) VALUES (" + author.getId() + ",'" + author.getIdentific() + "','" + author.getNote() + "')");
                return true;
            } else if(author.getId() != 0 && author.getIdentific() == null){
                 if(stm == null) stm = connection.createStatement();
                stm.executeUpdate("UPDATE Authors SET note = '" + author.getNote() + "' WHERE id = " + author.getId());
                return false;
            } else if(author.getId() != 0 && author.getNote() == null){
                 if(stm == null) stm = connection.createStatement();
                stm.executeUpdate("UPDATE Authors SET identific = '" + author.getIdentific() + "' WHERE id = " + author.getId());
                return false;
            } else 
                throw new DocumentException("Обновление не произошло");
        } catch (SQLException ex) {
            throw new DocumentException("Cоединение не установлено" + ex.getMessage());
        }
 
    }
     
     
    @Override
    public boolean addDocument(Documents doc, Authors author) throws DocumentException {
        Setting setting = new Setting();
            try( Connection connection = DriverManager.getConnection(
                setting.getValue(Setting.URL), 
                setting.getValue(Setting.USER), 
                setting.getValue(Setting.PASS))){
            if (doc.getIdDoc() != 0 
                    && author.getId() != 0 
                    && doc.getIdAuthors() != 0 
                    && doc.getNameDoc() != null 
                    && !doc.getNameDoc().trim().equals("") 
                    && doc.getTextDoc() != null 
                    && !doc.getTextDoc().trim().equals("")
                    && doc.getDateDoc() != null) {
                if (stm == null) stm = connection.createStatement();
                stm.executeUpdate("INSERT INTO Documents (id, nameDoc, textDoc, dateDoc, idAuthors) " +
                        "VALUES (" + doc.getIdDoc() + ",'" + doc.getNameDoc() + "', '" + doc.getTextDoc() + "', '" + doc.getDateDoc()+ "', " + doc.getIdAuthors() + ")");
                return true;
            }  else if (doc.getIdDoc() != 0 
                    && doc.getIdAuthors() != 0 
                    && doc.getTextDoc() == null) {
                if (stm == null) stm = connection.createStatement();
                stm.executeUpdate("UPDATE Documents SET " + "nameDoc='" + doc.getNameDoc() + "', " +
                        "dateDoc='" + doc.getDateDoc() + "', " + "idAuthors=" + doc.getIdAuthors() + " WHERE id=" + doc.getIdDoc());
                return false;
            } else throw new DocumentException("Обновление не произошло");
        } catch (SQLException ex) {
            throw new DocumentException("Соединение не установлено" + ex.getMessage());
        }
    }

    @Override
    public Documents[] findDocumentByAuthor(Authors author) throws DocumentException {
        Setting setting = new Setting();
        List<Documents> list = new ArrayList<>();
            try( Connection connection = DriverManager.getConnection(
                setting.getValue(Setting.URL), 
                setting.getValue(Setting.USER), 
                setting.getValue(Setting.PASS))){
              if (author.getIdentific() != null && !author.getIdentific().trim().equals("")) {
                if (stm == null) stm= connection.createStatement();
                rs = stm.executeQuery("SELECT documents.id, documents.nameDoc, "
                        + "documents.textDoc, documents.dateDoc, documents.idAuthors  FROM Documents  "
                        + "LEFT JOIN Authors ON authors.id=documents.idAuthors WHERE authors.identific = '" + author.getIdentific() + "'");
                while (rs.next()) {
                    Documents doc = new Documents(
                            rs.getInt(1), 
                            rs.getString(2),
                            rs.getString(3), 
                            rs.getDate(4), 
                            rs.getInt(5));
                    list.add(doc);
                    }
                if (list.isEmpty()) return null;
                Documents[] retList = new Documents[list.size()];
                int i = 0;
                for (Documents d : list)
                    retList[i++] = d;
                return retList;
            } else throw new DocumentException("Поиск невозможен");
        } catch (SQLException ex) {
            throw new DocumentException("Соединение не установлено" + ex.getMessage());
        }
    }

    @Override
    public Documents[] findDocumentByContent(String content) throws DocumentException {
        Setting setting = new Setting();
        List<Documents> list = new ArrayList<>();
            try( Connection connection = DriverManager.getConnection(
                setting.getValue(Setting.URL), 
                setting.getValue(Setting.USER), 
                setting.getValue(Setting.PASS))){
                if(content != null){
            if (stm == null) stm = connection.createStatement();
            rs = stm.executeQuery("SELECT * FROM Documents WHERE textDoc LIKE '" + content + "%'");
            while(rs.next()) {
               Documents doc = new Documents(
                        rs.getInt(1), 
                        rs.getString(2),
                        rs.getString(3), 
                        rs.getDate(4), 
                        rs.getInt(5));
                list.add(doc);
            }
            if (list.isEmpty()) return null;
            Documents[] retList = new Documents[list.size()];
            int i = 0;
            for (Documents d : list)
                retList[i++] = d;
            return retList;
                }else throw new DocumentException("Наименование несоотвествует");
        } catch (SQLException e) {
            throw new DocumentException("Соединение не установлено");
        }   
    }

    @Override
    public boolean deleteAuthor(Authors author) throws DocumentException {
        Setting setting = new Setting();
            try( Connection connection = DriverManager.getConnection(
                setting.getValue(Setting.URL), 
                setting.getValue(Setting.USER), 
                setting.getValue(Setting.PASS))){
                 if (author.getId() != 0) {
                if (stm == null) stm = connection.createStatement();
                stm.executeUpdate("DELETE FROM Documents WHERE idAuthors=" + author.getId());
                stm.executeUpdate("DELETE FROM Authors WHERE id=" + author.getId());
                return true;
            } else if (author.getIdentific() != null && !author.getIdentific().trim().equals("")) {
                if (stm == null) stm = connection.createStatement();
                stm.executeQuery("SELECT id FROM Authors WHERE identific = '" + author.getIdentific() + "'");
                if (!rs.next()) return false;
                int idAuthor = rs.getInt(1);
                stm.executeUpdate("DELETE FROM Documents WHERE idAuthors=" + idAuthor);
                stm.executeUpdate("DELETE FROM Authors WHERE id=" + idAuthor);
                return true;
            } else throw new DocumentException("Поле заполнено неправильно");
            }catch (SQLException e) {
            throw new DocumentException("Соединение не установлено");
        }   
    }

    @Override
    public boolean deleteAuthor(int id) throws DocumentException {
       Setting setting = new Setting();
            try( Connection connection = DriverManager.getConnection(
                setting.getValue(Setting.URL), 
                setting.getValue(Setting.USER), 
                setting.getValue(Setting.PASS))){
                if(id != 0){
                 if (stm == null) stm = connection.createStatement();
                    stm.executeUpdate("DELETE FROM Documents WHERE idAuthor=" + id);
                    stm.executeUpdate("DELETE FROM Authors WHERE id=" + id);
                return true;
                } else return false;
            }catch (SQLException e) {
            throw new DocumentException("Соединение не установлено");
        }   
    }
    
    @Override
    public void close() throws Exception {
       if (con != null && con.isValid(0)) con.close();
        if (stm != null) stm.close();
        if (rs != null) rs.close();
        System.out.println("Соединение прервано");
    }

}
