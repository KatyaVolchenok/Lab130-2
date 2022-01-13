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
public class Main {
    public static void main(String[] args) throws DocumentException {
        
        Setting setting = new Setting();
        System.out.println("url " + setting.getValue(Setting.URL));
        System.out.println("user " + setting.getValue(Setting.USER));
        System.out.println("pass " + setting.getValue(Setting.PASS));
        
  //     Authors author1 = new Authors(0, null, null);
    //    Authors author2 = new Authors(6, "Natasha Litvinova", "Yandex");
   //     Authors author3 = new Authors(5, null, "Google");
    //    Authors author4 = new Authors(5, "Sasha Vishnevsky", null);
        


    //    Documents document1 = new Documents(6, "Music", "Godsmack", new Date(2012/12/12), 5);
     //   Documents document2 = new Documents(5, "Music new", null, new Date(2012/12/12), 5);
  //      

        try (DbServer dbServer = DbServer.getDbServer()) {
   //         System.out.println(dbServer.addAuthor(author1));
     //    System.out.println(dbServer.addAuthor(author2));
      //      System.out.println(dbServer.addAuthor(author3));
    //       System.out.println(dbServer.addAuthor(author4));

      //     System.out.println(dbServer.addDocument(document1, author2));
    //        System.out.println(dbServer.addDocument(document2, author2));
   //         
//
           

  //         Documents [] AuthorName = dbServer.findDocumentByAuthor(new Authors(4, "Nataly Umbrella", null));
  //          if (AuthorName != null) {
  //             for (Documents doc : AuthorName)
   //                System.out.println(doc);
   //       }

   //         Documents [] Content = dbServer.findDocumentByContent("F");
   //         if (Content != null) {
   //            for (Documents doc : Content)
   //                System.out.println(doc);
    //        }

     //    System.out.println(dbServer.deleteAuthor(new Authors(6,null,null)));
  
        } catch (DocumentException e) {
            System.out.println("Error#1" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error#2" + e.getMessage());
        }
    }

}
