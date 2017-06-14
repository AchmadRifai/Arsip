/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import util.Db;

/**
 *
 * @author janoko
 */
public class Akun {
    private String kode,email,pass,nama,jk,foto;
    private java.sql.Timestamp ikut;
    private boolean login,deleted,blocked;

    public com.mongodb.DBObject genDBObject() {
        com.mongodb.BasicDBObject o=new com.mongodb.BasicDBObject();
        o.put("kode", kode);
        o.put("email", email);
        o.put("pass", pass);
        o.put("nama", nama);
        o.put("jk", jk);
        o.put("foto", foto);
        o.put("ikut", ""+ikut);
        o.put("login", ""+login);
        o.put("deleted", ""+deleted);
        o.put("blocked", ""+blocked);
        return o;
    }

    public Akun() {}

    public Akun(com.mongodb.DBObject o){
        kode=""+o.get("kode");
        email=""+o.get("email");
        pass=""+o.get("pass");
        nama=""+o.get("nama");
        jk=""+o.get("jk");
        foto=""+o.get("foto");
        ikut=java.sql.Timestamp.valueOf(""+o.get("ikut"));
        login=Boolean.parseBoolean(""+o.get("login"));
        deleted=Boolean.parseBoolean(""+o.get("deleted"));
        blocked=Boolean.parseBoolean(""+o.get("blocked"));
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public java.sql.Timestamp getIkut() {
        return ikut;
    }

    public void setIkut(java.sql.Timestamp ikut) {
        this.ikut = ikut;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void update(Db d) throws Exception {
        com.mongodb.DBObject w=new com.mongodb.BasicDBObject();
        w.put("kode", kode);
        com.mongodb.DBCursor c=d.genDB().getCollectionFromString("akun").find(w);
        if(c.hasNext()){
            com.mongodb.DBObject o=c.next();
            kode=""+o.get("kode");
            email=""+o.get("email");
            pass=""+o.get("pass");
            nama=""+o.get("nama");
            jk=""+o.get("jk");
            foto=""+o.get("foto");
            ikut=java.sql.Timestamp.valueOf(""+o.get("ikut"));
            login=Boolean.parseBoolean(""+o.get("login"));
            deleted=Boolean.parseBoolean(""+o.get("deleted"));
            blocked=Boolean.parseBoolean(""+o.get("blocked"));
        }c.close();
    }
}