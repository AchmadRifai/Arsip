/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author janoko
 */
public class Db {
    public static void hindar(Exception ex, String r) {
        java.util.Date d=new java.util.Date();
        java.io.File f=new java.io.File("error/"+r+
                "/"+d.getDate()+"-"+d.getMonth()+"-"+d.getYear()+"/"+d.getHours()+"-"+d.getMinutes()+"-"+d.getSeconds()+".log");
        if(!f.getParentFile().exists())f.getParentFile().mkdirs();
        if(f.exists())f.delete();
        try {
            java.io.PrintStream o=new java.io.PrintStream(f);
            ex.printStackTrace(o);
            o.close();
        } catch (FileNotFoundException ex1) {
            Logger.getLogger(Db.class.getName()).log(Level.SEVERE, null, ex1);
        }
    }

    private com.mongodb.MongoClient c;

    public Db() throws Exception{
        c=new com.mongodb.MongoClient();
    }

    public void close() throws Exception{
        c.close();
    }

    public com.mongodb.DB genDB() throws Exception{
        return c.getDB("arsip");
    }
}