package Model.Service.ServiceImplementation.Report;

import Model.Model.Book;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReportGenerator {

    public Report getReport(String reportType) {
        if (reportType.equals("PDF"))
            return new PDFReport();
        if (reportType.equals("TXT"))
            return new TXTReport();

        return null;
    }
}
