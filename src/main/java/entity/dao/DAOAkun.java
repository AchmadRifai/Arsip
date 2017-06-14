/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.dao;

import entity.Akun;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author janoko
 */
public class DAOAkun implements DAO<Akun>{
    private com.mongodb.DB d;

    public DAOAkun(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void createTable() throws Exception {
        //
    }

    @Override
    public void insert(Akun v) throws Exception {
        d.getCollectionFromString("akun").insert(v.genDBObject());
    }

    @Override
    public void delete(Akun w) throws Exception {
        Akun b=new Akun(w.genDBObject());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void trueDelete(Akun w) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("kode", w.getKode());
        d.getCollectionFromString("akun").remove(o);
    }

    @Override
    public void update(Akun a, Akun b) throws Exception {
        com.mongodb.DBObject o=new com.mongodb.BasicDBObject();
        o.put("kode", a.getKode());
        d.getCollectionFromString("akun").update(o, b.genDBObject());
    }

    @Override
    public List<Akun> all() throws Exception {
        List<Akun>l=new java.util.LinkedList<>();
        com.mongodb.DBCursor c=d.getCollectionFromString("akun").find();
        while(c.hasNext()){
            Akun a=new Akun(c.next());
            if(!a.isDeleted())l.add(a);
        }c.close();
        l.sort(sorter());
        return l;
    }

    @Override
    public List<Akun> sampah() throws Exception {
        List<Akun>l=new java.util.LinkedList<>();
        com.mongodb.DBCursor c=d.getCollectionFromString("akun").find();
        while(c.hasNext()){
            Akun a=new Akun(c.next());
            if(a.isDeleted())l.add(a);
        }c.close();
        l.sort(sorter());
        return l;
    }

    private Comparator<? super Akun> sorter() {
        return (Akun o1, Akun o2) -> {
            int x;
            if(o1.getIkut().after(o2.getIkut()))x=1;
            else if(o1.getIkut().equals(o2.getIkut()))x=0;
            else x=-1;
            return x;
        };
    }
}