package edu.cerveapp.utils;

import com.lowagie.text.*;
import com.lowagie.text.pdf.AcroFields.Item;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfSignatureAppearance;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.DottedLineSeparator;

import edu.cerveapp.entities.Configuracion;
import edu.cerveapp.entities.GustoPedido;
import edu.cerveapp.entities.InvalidConfigurationException;
import edu.cerveapp.entities.Pedido;

import java.awt.Color;
import java.io.*;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.UUID;

import javax.swing.GroupLayout.Alignment;


public class GeneratePDFFileIText {
	private static final Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 26, Font.BOLDITALIC);
	private static final Font paragraphFont = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
	private static final Font categoryFont = new Font(Font.TIMES_ROMAN, 18, Font.BOLD);
	private static final Font subcategoryFont = new Font(Font.TIMES_ROMAN, 16, Font.BOLD);
	private static final Font blueFont = new Font(Font.TIMES_ROMAN, 12, Font.NORMAL, Color.BLUE);
	private static final Font smallBold = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);

	private static final String[] COLUMN_NAMES = { "Gusto", "Cantidad", "Precio" };
	Configuracion conf;

	public GeneratePDFFileIText() throws InvalidConfigurationException {
		conf = Configuracion.getInstance();
	}

	public void createPDF(Pedido pedido) throws DocumentException {

		// Creamos el documento e indicamos el nombre del fichero usamos uno temporal.
		String pathname = conf.getConfiguracion("remitos_path") + UUID.randomUUID().toString();
		String salida = conf.getConfiguracion("remitos_path") + String.valueOf(pedido.getId()) + ".pdf";
		File pdfNewFile = new File(pathname);

		try {
			FileOutputStream fileOut = new FileOutputStream(pdfNewFile);
			Document document = new Document();
			PdfWriter.getInstance(document, fileOut);

			document.open();
			// metadatos del PDF
			document.addTitle(String.valueOf(pedido.getId()));
			document.addSubject(pedido.getUsuario().getApellido() + " " + pedido.getUsuario().getNombre());
			document.addKeywords("cerveceria, PDF, pedido");
			document.addAuthor("Sistema cerveAPP");
			document.addCreator("cerveAPP");

			// Primer Pagina

			Paragraph titutlo = new Paragraph("Remito de transito", categoryFont);
			Paragraph subtitulo = new Paragraph("Despacho de cerveza", categoryFont);

			titutlo.setAlignment(Element.ALIGN_CENTER);
			subtitulo.setAlignment(Element.ALIGN_CENTER);
			Chapter chapter = new Chapter(1);
			chapter.add(titutlo);
			chapter.add(subtitulo);

			Anchor anchor = new Anchor("Lista de gustos pedidos " + pedido.getId(), categoryFont);
			anchor.setName("Tabla");

			Paragraph paragraph = new Paragraph();
			paragraph.add(new Paragraph("Pedido: " + pedido.getId(), smallBold));
			paragraph.add(new Paragraph("Domicilio de entrega: " + pedido.getUsuario().getDireccion()));
			paragraph.add(new Paragraph("Entregar a: " + pedido.getUsuario().getNombre() + " " + pedido.getUsuario().getApellido()));
			
			paragraph.add(new Paragraph("Total: " + new DecimalFormat("#.##").format(pedido.getMonto())));
			paragraph.add(new Paragraph(""));
			Section paragraphMore = chapter.addSection(paragraph);

			// Creamos la tabla.
			PdfPTable table = new PdfPTable(COLUMN_NAMES.length);

			PdfPCell columnHeader;
			// Rellenamos la cabecera

			for (int column = 0; column < COLUMN_NAMES.length; column++) {
				columnHeader = new PdfPCell(new Phrase(COLUMN_NAMES[column]));
				columnHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(columnHeader);
			}
			table.setHeaderRows(1);

			// listamos
			Iterator<GustoPedido> gustoPedido = pedido.getGustosPedido().iterator();
			while (gustoPedido.hasNext()) {
				GustoPedido gustos = gustoPedido.next();
				table.addCell(gustos.getNomnbre());
				table.addCell(String.valueOf(gustos.getCantidadPedida()));
				
				table.addCell(new DecimalFormat("#.##").format(gustos.getCantidadPedida() * gustos.getPreciolitro()));

			}

			paragraphMore.add(table);

			chapter.add(new Paragraph("Conforme de Recepción."));
			chapter.add(new Paragraph("Firma y Aclaracion......................................"));

			document.add(chapter);
			document.close();
			
			signPdf(pathname,salida);
			
			pdfNewFile.delete();
		} catch (DocumentException | IOException  e) {
			throw new DocumentException("No se pudo generar el PDF "+e.getMessage());
		}

	}

	private void signPdf(String fname, String fnameS) throws IOException, DocumentException {

		String fileKey = conf.getSeccion().getValorClave("keystore_path")
				+ conf.getSeccion().getValorClave("keystore_name");
		String fileKeyPassword = conf.getSeccion().getValorClave("cert_pass");

		try {
			KeyStore ks = KeyStore.getInstance("pkcs12");
			ks.load(new FileInputStream(fileKey), fileKeyPassword.toCharArray());
			String alias = (String) ks.aliases().nextElement();
			PrivateKey key = (PrivateKey) ks.getKey(alias, fileKeyPassword.toCharArray());
			Certificate[] chain = ks.getCertificateChain(alias);

			PdfReader pdfReader = new PdfReader((new File(fname)).getAbsolutePath());
			File outputFile = new File(fnameS);

			PdfStamper pdfStamper;
			pdfStamper = PdfStamper.createSignature(pdfReader, null, '\0', outputFile);
			PdfSignatureAppearance sap = pdfStamper.getSignatureAppearance();
			sap.setCrypto(key, chain, null, PdfSignatureAppearance.SELF_SIGNED);
			sap.setReason("Firmado Electronicamente.");
			sap.setLocation("");

			sap.setVisibleSignature(new Rectangle(10, 10, 50, 30), 1, "Campo de Firma");

			pdfStamper.setFormFlattening(true);
			pdfStamper.close();

		} catch (Exception key) {
			throw new DocumentException(key);
		}
	}

}
