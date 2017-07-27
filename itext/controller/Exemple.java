/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itext.controller;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 *
 * @author Marco
 */
public class Exemple {

    private static Exemple instance = null;
    private String nameDoc;
    private Rectangle pageSize;
    private Document doc;
    
    private Exemple() {
        this.doc = new Document();
        this.nameDoc = "unamed.pdf";
        this.pageSize = PageSize.A4;
    }
    
    public boolean startDoc(){
        try {
            //Cria uma instancia do escritor;
            PdfWriter.getInstance(doc, new FileOutputStream(this.nameDoc));
            this.doc.open();

        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return  true;
    }

    public static Exemple getInstance() {
        if (instance == null) {
            instance = new Exemple();
        }
        return instance;
    }

    public boolean setName(String name) {
        if (name.contains(".") || !name.endsWith(".pdf")) {
            if (!name.endsWith(".pdf")) {
                name = name + ".pdf";
            }
            this.nameDoc = name;
            return true;
        }
        return false;
    }

    public void setPageSize(Rectangle pageSize) {
        this.pageSize = pageSize;
    }

    public boolean addTitle(String content, int aling) {

        Paragraph title = new Paragraph(content);
        title.setAlignment(aling);
        title.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD, BaseColor.BLACK));
        try {

            this.doc.add(title);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean addParagraph(String content, int aling) {
        Paragraph paragrph = new Paragraph(content);
        paragrph.setAlignment(aling);
        paragrph.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK));
        try {
            this.doc.add(paragrph);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean addParagraph(String content) {
        return this.addParagraph(content, Paragraph.ALIGN_LEFT);

    }

    public boolean addSubTilte(String sub, int aling) {
        Paragraph subTitle = new Paragraph(sub);
        subTitle.setAlignment(aling);
        subTitle.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK));
        try {
            //Adiciona um elemento ao pdf
            this.doc.add(subTitle);

        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
        return true;
    }

    public boolean addLineSeparetor() {
        try {
            this.doc.add(new Paragraph("\n"));
            this.doc.add(new LineSeparator());
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar linha de separação" + ex);
            return false;
        }
        return true;
    }

    public boolean addList(ArrayList<String> itens, boolean numered, boolean lettered) {

        List list = new List(numered, lettered);
        for (String iten : itens) {
            list.add(iten);
        }
        try {

            this.doc.add(list);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar a lista ao documento" + ex);
            return false;
        }

        return true;

    }

    public boolean addTable(ArrayList<String> tipe, ArrayList<String> responses) {
        
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
        PdfPTable table = new PdfPTable(2);
        table.setPaddingTop(50);
      
        float[] widths = {20,80};
        
        for(int i =0; i < tipe.size(); i++){
            table.addCell(new Paragraph(tipe.get(i),font));
            table.addCell(new Paragraph(responses.get(i),font));
        }        
        try {
            table.setWidths(widths);
            this.doc.add(new Paragraph("\n"));
            this.doc.add(table);
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar a tablela ao documento" + ex);
            return false;
        }
        return true;
    }

    public boolean addImage(String path) {
        try {
            Image img = Image.getInstance(path);
            img.setAlignment(Image.ALIGN_RIGHT);
            img.scaleToFit(100, 200);
            this.doc.add(img);
        } catch (BadElementException | IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar a imagem ao documento" + ex);
            return false;
        } catch (DocumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar a imagem ao documento" + ex);
            return false;
        }
        return true;
    }

    public void write() {

        this.doc.close();
    }
}
