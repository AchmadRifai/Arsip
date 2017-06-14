/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.util;

import entity.Akun;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author janoko
 */
public class DataList {
    public static void akunCards(List<Akun> l, PrintWriter o) throws Exception {
        o.print("<div class=\"ui four columns grid cards\">");
        for(Akun a:l){
            o.print("<div class=\"ui column card\">");
            o.print("<div class=\"image\">");
            if("-".equals(a.getFoto())){
                if("Male".equals(a.getJk()))o.print("<img src=\"gbr/lanang.png\">");
                else o.print("<img src=\"gbr/wedok.png\">");
            }else o.print("<img src=\""+alamatFoto(a)+"\">");
            konten(o,a);
            o.print("</div>");
        }o.print("</div>");
    }

    private static String alamatFoto(Akun a) throws Exception {
        util.RSA r=util.Work.loadRSA();
        String s="berkas.data?akses=";
        s=s+r.encrypt(a.getFoto());
        return s;
    }

    private static void konten(PrintWriter o, Akun a) {
        o.print("<div class=\"content\">");
        o.print("<a class=\"header\">"+a.getNama()+"</a>");
        o.print("<div class=\"meta\">");
        o.print("<span class=\"date\">"+a.getIkut()+"</span>");
        o.print("</div>");
        o.print("<div class=\"description\">"+a.getEmail()+"</div>");
        o.print("</div>");
    }
}