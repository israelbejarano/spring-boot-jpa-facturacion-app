package com.ideas.springboot.app.view.pdf;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.ideas.springboot.app.models.entity.Factura;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
/**
 * The Class FacturaPdfView. Componente que renderiza el pdf de la factura
 * @author Israel Bejarano
 */
@Component("factura/ver")
public class FacturaPdfView extends AbstractPdfView {

	/**
	 * Builds the pdf document.
	 *
	 * @param model the model
	 * @param document the document
	 * @param writer the writer
	 * @param request the request
	 * @param response the response
	 * @throws Exception the exception
	 */
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Factura factura = (Factura) model.get("factura");
		
		PdfPTable tabla = new PdfPTable(1);
		tabla.addCell("Datos del cliente");
		tabla.addCell(factura.getCliente().getNombre() + " " + factura.getCliente().getApellido());
		tabla.addCell(factura.getCliente().getEmail());
		
		PdfPTable tabla2 = new PdfPTable(1);
		tabla.addCell("Datos de la factura");
		tabla.addCell("Folio: "+ factura.getId());
		tabla.addCell("Descripción " + factura.getDescripcion());
		tabla.addCell("Fecha: " + factura.getCreateAt());
		
		document.add(tabla);
		document.add(tabla2);
	}
}