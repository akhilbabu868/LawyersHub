/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lawyershub.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author ASUS
 */
public class ImageSerilizer {

    public ImageSerilizer() {
    }

    public List imageSerilizer(List lst, String[] index) {

        ListIterator Itr = lst.listIterator();
        while (Itr.hasNext()) {
            Map r = (Map) Itr.next();

            for (int i = 0; i < index.length; i++) {
                try {
                    if (r.get(index[i]) != null) {

                        Blob b = (Blob) r.get(index[i]);
                        //System.out.println("image:"+b.length());
                        String da;
                        int l = (int) b.length();
                        da = Base64.encodeBase64String(b.getBytes(1, l));
                        r.put(index[i], da);
                        // Itr.set(r);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return lst;
    }

    public List imageSerilizer(List lst, int[] index) {

        ListIterator Itr = lst.listIterator();
        while (Itr.hasNext()) {
            Object[] r = (Object[]) Itr.next();

            for (int i = 0; i < index.length; i++) {
                try {
                    if (r[index[i]] != null) {

                        Blob b = (Blob) r[index[i]];
                        //System.out.println("image:"+b.length());
                        String da;
                        int l = (int) b.length();
                        da = Base64.encodeBase64String(b.getBytes(1, l));
                        r[index[i]] = da;
                        //  Itr.set(r);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return lst;
    }

    public byte[] toByteArray(Blob fromImageBlob) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            return toByteArrayImpl(fromImageBlob, baos);
        } catch (Exception e) {
        }
        return null;
    }

    public byte[] toByteArrayImpl(Blob fromImageBlob,
            ByteArrayOutputStream baos) throws SQLException, IOException {
        byte buf[] = new byte[4000];
        int dataSize;
        InputStream is = fromImageBlob.getBinaryStream();

        try {
            while ((dataSize = is.read(buf)) != -1) {
                baos.write(buf, 0, dataSize);
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return baos.toByteArray();
    }

}
