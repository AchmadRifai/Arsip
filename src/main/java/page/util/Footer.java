/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package page.util;

import java.io.PrintWriter;

/**
 *
 * @author janoko
 */
public class Footer {
    public static void defaultFoot(PrintWriter o) {
        o.print("</body>");
        o.print("</html>");
    }
}