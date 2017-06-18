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
public class Berkas {
    public static Berkas of(DB d, String path) {
        Berkas b=null;
        DBObject w=new com.mongodb.BasicDBObject();
        w.put("path", path);
        com.mongodb.DBCursor c=d.getCollectionFromString("berkas").find(w);
        if(c.hasNext())b=new Berkas(c.next());
        return b;
    }

    private String nama,tipe,extensi,path,koleksi;
    private java.sql.Timestamp upload;
    private boolean deleted;

    public Berkas() {
    }

    private Berkas(DBObject o) {
        nama=""+o.get("nama");
        tipe=""+o.get("tipe");
        extensi=""+o.get("extensi");
        path=""+o.get("path");
        koleksi=""+o.get("koleksi");
        upload=java.sql.Timestamp.valueOf(""+o.get("upload"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getExtensi() {
        return extensi;
    }

    public void setExtensi(String extensi) {
        this.extensi = extensi;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKoleksi() {
        return koleksi;
    }

    public void setKoleksi(String koleksi) {
        this.koleksi = koleksi;
    }

    public java.sql.Timestamp getUpload() {
        return upload;
    }

    public void setUpload(java.sql.Timestamp upload) {
        this.upload = upload;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public DBObject genDBO() {
        DBObject o=new com.mongodb.BasicDBObject();
        o.put("nama", nama);
        o.put("path", path);
        o.put("tipe", tipe);
        o.put("extensi", extensi);
        o.put("koleksi", koleksi);
        o.put("upload", ""+upload);
        o.put("deleted", ""+deleted);
        return o;
    }
}