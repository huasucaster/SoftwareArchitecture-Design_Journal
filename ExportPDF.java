import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.AttributedString;
import java.util.Scanner;

import com.qoppa.pdfWriter.PDFDocument;
import com.qoppa.pdfWriter.PDFGraphics;
import com.qoppa.pdfWriter.PDFPage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import java.io.File;
import java.util.Scanner;

public class ExportPDF
{
    Button btLogExport=new Button("ExportToPDF");



    public ExportPDF(GetJournal log) {
        btLogExport.setOnAction(e -> {
            try
            {
                float pageWidth = 8.5f;
                float pageHeight = 11f;

                float marginLeft = 1;
                float marginTop = 1;
                float marginBottom = 1;
                float marginRight = 1;// Define page format for PDF document
                Paper p = new Paper ();
                // set paper size
                p.setSize(pageWidth * 72, pageHeight * 72);
                // set no margin to paper (we're taking care of the margins when writing)
                p.setImageableArea(0, 0, pageWidth * 72,  pageHeight * 72);
                PageFormat pageFormat = new PageFormat ();
                pageFormat.setPaper(p);
                // Create the PDF document
                PDFDocument pdfDoc = new PDFDocument();

                // Create font
                Font font = PDFGraphics.HELVETICA.deriveFont(Font.PLAIN, 11f);

                // Init page information
                PDFPage newPage = null;
                Graphics2D g2 = null;
                FontMetrics fm = null;
                float currentY = marginTop * 72;
                float wrapWidth = (pageWidth - (marginLeft + marginRight)) * 72f;

                BufferedReader reader = new BufferedReader (new FileReader(log.getURL()));
                String line = reader.readLine();
                while (line != null)
                {
                    // Create new page when needed
                    if (newPage == null)
                    {
                        newPage = pdfDoc.createPage(pageFormat);
                        pdfDoc.addPage(newPage);
                        g2 = newPage.createGraphics();
                        g2.setFont(font);
                        fm = g2.getFontMetrics();
                        currentY = marginTop * 72;
                    }

                    if (line.length() <= 0)
                    {
                        // Advance to next line
                        currentY += fm.getHeight();
                        line = reader.readLine();
                        continue;
                    }

                    AttributedString attrString = new AttributedString(line);
                    attrString.addAttribute(TextAttribute.FONT, font, 0, line.length());
                    LineBreakMeasurer lbm = new LineBreakMeasurer(attrString.getIterator(), g2.getFontRenderContext());

                    int offset = 0;

                    while (offset < line.length())
                    {
                        offset = lbm.nextOffset(wrapWidth);

                        // Draw the line
                        g2.drawString(line.substring(lbm.getPosition(), offset), marginLeft * 72, currentY);

                        // update the line LineBreakMeasurer position
                        lbm.setPosition(offset);

                        // Advance to next line
                        currentY += fm.getHeight();
                        if (currentY >= ((pageHeight - marginBottom) * 72))
                        {
                            newPage = pdfDoc.createPage(pageFormat);
                            pdfDoc.addPage(newPage);
                            g2 = newPage.createGraphics();
                            g2.setFont(font);
                            fm = g2.getFontMetrics();
                            currentY = marginTop * 72;
                        }
                    }

                    // Read the next line
                    line = reader.readLine();
                }

                // Close the text file
                reader.close();

                // Save the document
                pdfDoc.saveDocument(log.url+".pdf");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ExportToPDF");
                alert.setContentText("Successful to Export!");
                alert.showAndWait();
            }
            catch (IOException ioE)
            {
                ioE.printStackTrace();
            }
            catch (Throwable t)
            {
                t.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ExportToPDF");
                alert.setContentText("Error！None journal exists!");
                alert.showAndWait();
            }
        });
    }


}