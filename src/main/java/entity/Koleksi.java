/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.mongodb.DB;
import com.mongodb.DBObject;

/**
 *
 * @author janoko
 */
public class Koleksi {
    public static Koleksi of(DB d, String kode) throws Exception {
        Koleksi k=null;
        DBObject w=new com.mongodb.BasicDBObject();
        w.put("kode", kode);
        com.mongodb.DBCursor c=d.getCollectionFromString("koleksi").find(w);
        if(c.hasNext())k=new Koleksi(c.next());
        return k;
    }

    private String akun,nama,kode,akses;
    private java.sql.Timestamp buat;
    private boolean deleted;

    public Koleksi() {
    }

    public Koleksi(DBObject o) throws Exception {
        akun=""+o.get("akun");
        nama=""+o.get("nama");
        kode=""+o.get("kode");
        akses=""+o.get("akses");
        buat=java.sql.Timestamp.valueOf(""+o.get("buat"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    public String getAkun() {
        return akun;
    }

    public void setAkun(String akun) {
        this.akun = akun;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getAkses() {
        return akses;
    }

    public void setAkses(String akses) {
        this.akses = akses;
    }

    public java.sql.Timestamp getBuat() {
        return buat;
    }

    public void setBuat(java.sql.Timestamp buat) {
        this.buat = buat;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DBObject genDBO() {
        DBObject o=new com.mongodb.BasicDBObject();
        o.put("kode", kode);
        o.put("nama", nama);
        o.put("akun", akun);
        o.put("akses", akses);
        o.put("buat", ""+buat);
        o.put("deleted", ""+deleted);
        return o;
    }
}