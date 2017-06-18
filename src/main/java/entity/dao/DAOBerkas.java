/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.dao;

import entity.Berkas;
import java.util.List;

/**
 *
 * @author janoko
 */
public class DAOBerkas implements DAO<Berkas>{
    private com.mongodb.DB d;

    public DAOBerkas(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void createTable() throws Exception {
        //
    }

    @Override
    public void insert(Berkas v) throws Exception {
        d.getCollectionFromString("berkas").insert(v.genDBO());
    }

    @Override
    public void delete(Berkas w) throws Exception {
        Berkas b=Berkas.of(d,w.getPath());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void trueDelete(Berkas w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("path", w.getPath());
        d.getCollectionFromString("berkas").remove(o);
    }

    @Override
    public void update(Berkas a, Berkas b) throws Exception {
        d.getCollectionFromString("berkas").update(a.genDBO(), b.genDBO());
    }

    @Override
    public List<Berkas> all() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Berkas> sampah() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}