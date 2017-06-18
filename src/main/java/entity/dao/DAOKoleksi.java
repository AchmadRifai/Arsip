/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.dao;

import entity.Koleksi;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author janoko
 */
public class DAOKoleksi implements DAO<Koleksi>{
    private com.mongodb.DB d;

    public DAOKoleksi(com.mongodb.DB db){
        d=db;
    }

    @Override
    public void createTable() throws Exception {
        //
    }

    @Override
    public void insert(Koleksi v) throws Exception {
        d.getCollectionFromString("koleksi").insert(v.genDBO());
    }

    @Override
    public void delete(Koleksi w) throws Exception {
        Koleksi b=Koleksi.of(d,w.getKode());
        b.setDeleted(true);
        update(w,b);
    }

    @Override
    public void trueDelete(Koleksi w) throws Exception {
        com.mongodb.DBObject wo=new com.mongodb.BasicDBObject();
        wo.put("kode", w.getKode());
        d.getCollectionFromString("koleksi").remove(wo);
    }

    @Override
    public void update(Koleksi a, Koleksi b) throws Exception {
        d.getCollectionFromString("koleksi").update(a.genDBO(), b.genDBO());
    }

    @Override
    public List<Koleksi> all() throws Exception {
        List<Koleksi>l=new java.util.LinkedList<>();
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("deleted", "false");
        com.mongodb.DBCursor c=d.getCollectionFromString("koleksi").find(w);
        while(c.hasNext())l.add(new Koleksi(c.next()));
        l.sort(sorter());
        return l;
    }

    @Override
    public List<Koleksi> sampah() throws Exception {
        List<Koleksi>l=new java.util.LinkedList<>();
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("deleted", "true");
        com.mongodb.DBCursor c=d.getCollectionFromString("koleksi").find(w);
        while(c.hasNext())l.add(new Koleksi(c.next()));
        l.sort(sorter());
        return l;
    }

    private Comparator<? super Koleksi> sorter() {
        return (Koleksi o1, Koleksi o2) -> {
            int x;
            if(o1.getBuat().after(o2.getBuat()))x=1;
            else if(o1.getBuat().before(o2.getBuat()))x=-1;
            else x=0;
            return x;
        };
    }
}