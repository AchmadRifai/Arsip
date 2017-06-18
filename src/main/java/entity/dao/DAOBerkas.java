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
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("path", a.getPath());
        d.getCollectionFromString("berkas").update(o, b.genDBO());
    }

    @Override
    public List<Berkas> all() throws Exception {
        List<Berkas>l=new java.util.LinkedList<>();
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("deleted", "false");
        com.mongodb.DBCursor c=d.getCollectionFromString("berkas").find(o);
        while(c.hasNext())l.add(new Berkas(c.next()));
        c.close();
        return l;
    }

    @Override
    public List<Berkas> sampah() throws Exception {
        List<Berkas>l=new java.util.LinkedList<>();
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("deleted", "true");
        com.mongodb.DBCursor c=d.getCollectionFromString("berkas").find(o);
        while(c.hasNext())l.add(new Berkas(c.next()));
        c.close();
        return l;
    }
}