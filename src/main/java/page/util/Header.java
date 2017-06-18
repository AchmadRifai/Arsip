/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.util;

import entity.Akun;
import java.io.PrintWriter;
import util.Db;

/**
 *
 * @author janoko
 */
public class Header {
    public static void headIndex(PrintWriter o, String title) {
        o.println("<!DOCTYPE html>");
        o.print("<html>");
        o.print("<head>");
        o.print("<title>"+title+"</title>");
        meta(o);
        style1(o);
        o.print("</head>");
        o.print("<body>");
    }

    private static void meta(PrintWriter o) {
        o.print("<meta charset=\"UTF-8\">");
        o.print("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\">");
        o.print("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
    }

    private static void style1(PrintWriter o) {
        o.print("<link rel=\"stylesheet\" type=\"text/css\" href=\"lib/semantic.min.css\">");
        o.print("<style type=\"text/css\">");
        o.print("body {background-color: #AFAFAF;}");
        o.print(".ui.menu .item img.logo {margin-right: 1.5em;}");
        o.print(".main.container {margin-top: 7em;background-color: wheat;}");
        o.print(".wireframe {margin-top: 2em;}");
        o.print(".ui.footer.segment {margin: 5em 0em 0em;padding: 5em 0em;}");
        o.print("</style>");
        o.print("<script src=\"lib/jquery.js\"></script>");
        o.print("<script src=\"lib/semantic.min.js\"></script>");
    }

    public static void navIndex(PrintWriter o, Object id, Db d) throws Exception {
        o.print("<div class=\"ui fixed inverted menu green\">");
        o.print("<div class=\"ui container\">");
        if(id!=null){
            Akun a=(Akun) id;
            a.update(d);
            if(a.isLogin())navLanjutAkun(o,a);
            else navLanjutBiasa(o);
        }else navLanjutBiasa(o);
        o.print("</div></div>");
    }

    private static void navLanjutBiasa(PrintWriter o) {
        //
    }

    private static void navLanjutAkun(PrintWriter o, Akun a) {
        //
    }
}