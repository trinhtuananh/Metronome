/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.trinh.metronome.audio;

import java.io.PrintWriter;
import java.io.StringWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

/**
 *
 * @author Tuan anh TRINH
 */
public class ExceptionDialog extends Alert{
    
        TextArea textArea;
    public ExceptionDialog(Exception exception) {
        super(AlertType.ERROR);
        StringWriter sw = new StringWriter();
PrintWriter pw = new PrintWriter(sw);
exception.printStackTrace(pw);
String exceptionText = sw.toString();
        textArea = new TextArea(exceptionText);
        this.setHeaderText(exception.toString());
        this.show();
    }
    
}
