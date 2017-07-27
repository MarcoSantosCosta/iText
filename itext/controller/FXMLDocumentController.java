/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itext.controller;

import com.itextpdf.text.Paragraph;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextField name;

    @FXML
    private TextField email;

    @FXML
    private TextField phone;

    @FXML
    private TextField img;

    @FXML
    private TextArea description;

    @FXML
    private TextField formation1;

    @FXML
    private TextField formation2;

    @FXML
    private TextField ability1;

    @FXML
    private TextField ability2;

    @FXML
    private TextField abilityDescription1;

    @FXML
    private TextField abilityDescription2;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        Exemple ex = Exemple.getInstance();
        ArrayList<Boolean> result = new ArrayList();
        result.add(ex.setName(name.getText()));
        result.add(ex.startDoc());
        result.add(ex.addTitle(name.getText(), Paragraph.ALIGN_CENTER));
        result.add(ex.addParagraph(email.getText(), Paragraph.ALIGN_CENTER));
        result.add(ex.addParagraph(phone.getText(), Paragraph.ALIGN_CENTER));
        result.add(ex.addImage(img.getText()));
        result.add(ex.addLineSeparetor());
        result.add(ex.addSubTilte("Descrição", Paragraph.ALIGN_LEFT));
        result.add(ex.addParagraph(description.getText()));
        result.add(ex.addLineSeparetor());
        result.add(ex.addSubTilte("Formação", Paragraph.ALIGN_LEFT));
        ArrayList<String> formation = new ArrayList();
        formation.add(formation1.getText());
        formation.add(formation2.getText());
        result.add(ex.addList(formation, true, false));
        result.add(ex.addLineSeparetor());
        result.add(ex.addSubTilte("Formação", Paragraph.ALIGN_LEFT));
        ArrayList<String> abilityTipe = new ArrayList();
        abilityTipe.add(ability1.getText());
        abilityTipe.add(ability2.getText());
        ArrayList<String> abilyDescription = new ArrayList();
        abilyDescription.add(abilityDescription1.getText());
        abilyDescription.add(abilityDescription2.getText());
        result.add(ex.addTable(abilityTipe, abilyDescription));
        ex.write();
        if (!result.contains(false)) {
            JOptionPane.showMessageDialog(null, "Currículo Criado com Sucesso");
            try {
                Desktop.getDesktop().open(new File(name.getText()+".pdf"));
            } catch (IOException ex1) {
                JOptionPane.showMessageDialog(null,"Erro ao abrir pdf");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
