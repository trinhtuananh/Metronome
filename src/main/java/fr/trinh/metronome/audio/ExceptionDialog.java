/**
*MetronomeFX is a java application that allow the user to play a sound and set *the balance
*    Copyright (C) 2016  Tuan anh TRINH
*
*    This program is free software: you can redistribute it and/or *modify
*    it under the terms of the GNU General Public License as published *by
*    the Free Software Foundation, either version 3 of the License, or
*    (at your option) any later version.
*
*    This program is distributed in the hope that it will be useful,
*    but WITHOUT ANY WARRANTY; without even the implied warranty of
*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
*    GNU General Public License for more details.
*
*    You should have received a copy of the GNU General Public License
*    along with this program.  If not, see <http://www.gnu.org/*licenses/
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
