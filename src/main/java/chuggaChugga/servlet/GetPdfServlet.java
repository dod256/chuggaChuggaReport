package chuggaChugga.servlet;

import chuggaChugga.Controller.TicketController;
import chuggaChugga.domain.Ticket;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class GetPdfServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Font tableTitle = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        TicketController ticketController = (TicketController) req.getSession().getAttribute("ticketController");
        ArrayList<Ticket> ticketList = (ArrayList<Ticket>) ticketController.getTicketList();
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, resp.getOutputStream());
            document.open();
            PdfPTable table = new PdfPTable(4);
            table.setSpacingBefore(25);
            table.setSpacingAfter(25);
            table.setWidthPercentage(100);

            table.addCell(new PdfPCell(new Phrase("Passenger", tableTitle)));
            table.addCell(new PdfPCell(new Phrase("Train", tableTitle)));
            table.addCell(new PdfPCell(new Phrase("DepartureStation", tableTitle)));
            table.addCell(new PdfPCell(new Phrase("ArrivalStation", tableTitle)));

            for (Ticket ticket: ticketList) {
                table.addCell(ticket.getUserFirstName() + " " +ticket.getUserLastName());
                table.addCell(ticket.getTrainName());
                table.addCell(ticket.getDepartureStation() + "\n\n" + ticket.getDepartureDate());
                table.addCell(ticket.getArrivalStation() + "\n\n" + ticket.getArrivalDate());
            }

            document.add(table);
            resp.setContentType("application/pdf");
        } catch (DocumentException e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen()) {
                document.close();
            }
        }
    }
}
