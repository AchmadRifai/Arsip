/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 *
 * @author janoko
 */
public class Work {
    public static RSA loadRSA() throws GeneralSecurityException, IOException {
        return new RSA(new java.io.File("private"),new java.io.File("public"));
    }
}