package fr.univtln.groupc;

/*
 * Copyright (c) Ian F. Darwin, http://www.darwinsys.com/, 1996-2002.
 * All rights reserved. Software written by Ian F. Darwin and others.
 * $Id: LICENSE,v 1.8 2004/02/09 03:33:38 ian Exp $
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS''
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * Java, the Duke mascot, and all variants of Sun's Java "steaming coffee
 * cup" logo are trademarks of Sun Microsystems. Sun's, and James Gosling's,
 * pioneering role in inventing and promulgating (and standardizing) the Java
 * language and environment is gratefully acknowledged.
 *
 * The pioneering role of Dennis Ritchie and Bjarne Stroustrup, of AT&T, for
 * inventing predecessor languages C and C++ is also gratefully acknowledged.
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Internal Frames Demo
 *
 * @version $Id: JIFrameDemo.java,v 1.4 2003/07/15 01:46:47 ian Exp $
 */
public class JIFrameDemo {

    /* Main View */
    public static void main(String[] a) {
        final JFrame jf = new JFrame("JIFrameDemo Main Window");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        screenSize.width -= 42;
        screenSize.height -= 42;
        jf.setSize(screenSize);
        jf.setLocation(20, 20);

        JMenuBar mb = new JMenuBar();
        jf.setJMenuBar(mb);
        JMenu fm = new JMenu("File");
        mb.add(fm);
        JMenuItem mi;
        fm.add(mi = new JMenuItem("Exit"));
        mi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        JDesktopPane dtp = new JDesktopPane();
        //dtp.setBackground(Color.GREEN);
        jf.setContentPane(dtp);

        JInternalFrame mboxFrame = new JInternalFrame("Mail Reader", true,
                true, true, true);
        JLabel reader = new JLabel("Mail Reader Would Be Here");
        mboxFrame.setContentPane(reader);
        mboxFrame.setSize(400, 300);
        mboxFrame.setLocation(50, 50);
        mboxFrame.setVisible(true);
        dtp.add(mboxFrame);

        JInternalFrame compFrame = new JInternalFrame("Compose Mail", true,
                true, true, true);
        JLabel composer = new JLabel("Mail Compose Would Be Here");
        compFrame.setContentPane(composer);
        compFrame.setSize(300, 200);
        compFrame.setLocation(200, 200);
        compFrame.setVisible(true);
        dtp.add(compFrame);

        JInternalFrame listFrame = new JInternalFrame("Users", true, true,
                true, true);
        JLabel list = new JLabel("List of Users Would Be Here");
        listFrame.setContentPane(list);
        listFrame.setLocation(400, 400);
        listFrame.setSize(500, 200);
        listFrame.setVisible(true);
        dtp.add(listFrame);

        jf.setVisible(true);
        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                jf.setVisible(false);
                jf.dispose();
                System.exit(0);
            }
        });
    }
}