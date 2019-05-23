package Model.Service.ServiceImplementation.Report;

import Model.Model.Book;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class PDFReport implements Report {

    private static String PDF_FILE = "E:/AN 3/Sem 2/PS/Assignment 2/Reports/PDF Report.pdf";


    private static PdfPTable insertTable(Document pdfDoc, List<Book> books) {
        PdfPTable table = new PdfPTable(2);
        Stream.of("Title", "Genre")
                .forEach(column -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.CYAN);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(column));
                    table.addCell(header);
                });
        books.forEach(book -> {
            if (book.getStock().equals(0)) {
                PdfPCell cell = new PdfPCell();
                cell.setBackgroundColor(BaseColor.ORANGE);
                cell.setPhrase(new Phrase(book.getTitle()));
                table.addCell(cell);
                cell.setPhrase(new Phrase((book.getGenre())));
                table.addCell(cell);
            }
        });
        return table;
    }

    // generate PDF Report
    public void exportReport(String loginAdmin, List<Book> booksOutOfStock) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {
            Document pdfDoc = new Document();
            PdfWriter.getInstance(pdfDoc, new FileOutputStream(PDF_FILE));
            pdfDoc.open();
            pdfDoc.add(new Paragraph("Title: Books Out Of Stock"));
            pdfDoc.add(new Paragraph("Author: " + loginAdmin));
            pdfDoc.add(new Paragraph("Date: " + dateFormat.format(date)));
            pdfDoc.add(new Paragraph(" "));
            PdfPTable table = insertTable(pdfDoc, booksOutOfStock);
            pdfDoc.add(table);
            pdfDoc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
