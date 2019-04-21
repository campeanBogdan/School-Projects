package Model.Service.ServiceImplementation.Report;

import Model.Model.Book;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TXTReport implements Report {

    private static String TXT_FILE = "E:/AN 3/Sem 2/PS/Assignment 2/Reports/TXT Report.txt";


    // generate TXT Report
    static BufferedWriter writer = null;
    static int i = 1;
    public void exportReport(String loginAdmin, List<Book> booksOutOfStock) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        try {
            writer = new BufferedWriter(new FileWriter(TXT_FILE));
            writer.write("Title: Books Out Of Stock");
            writer.newLine();
            writer.write("Author: " + loginAdmin);
            writer.newLine();
            writer.write(dateFormat.format(date));
            writer.newLine();
            writer.newLine();
            writer.write("TITLE:");
            writer.newLine();

            booksOutOfStock.forEach(book -> {
                try {
                    if (book.getStock().equals(0)) {
                        writer.write(i + ". " + book.getTitle());
                        writer.newLine();
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            i = 1;
            writer.newLine();
            writer.write("GENRE:");
            writer.newLine();
            booksOutOfStock.forEach(book -> {
                try {
                    if (book.getStock().equals(0)) {
                        writer.write(i + ". " + book.getGenre());
                        writer.newLine();
                        i++;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
