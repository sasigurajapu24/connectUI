package com.incon.connect.ui.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.incon.connect.ui.dto.QRCodeDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

@Component
public class GenerateQRCode {

	private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.RED);
	private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
	private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

//	public File generateCode(List<QRCodeDto> list, Long id, String password) throws Exception {
//		File file = new File("Codes_" + id + "_" + new SimpleDateFormat("ddMMyyHHmmssSSS").format(new Date()) + ".pdf");
//		FileOutputStream fos = null;
//		if (null != list) {
//			try {
//				Document document = new Document();
//				fos = new FileOutputStream(file);
//				PdfWriter writer = PdfWriter.getInstance(document, fos);
//				writer.setEncryption(password.getBytes(), "tcb0gavalli".getBytes(), PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_128);
//				document.open();
//				
//				addTitlePage(document);
//				
//				addContent(document, list);
//				
//				document.close();
//
//			} catch (Exception ex) {
//				throw ex;
//			}
//		} else {
//			throw new Exception("Empty list returned. No codes in database.");
//		}
//		// try {
//		// FileOutputStream fout = new FileOutputStream(new
//		// File("C:\\QR_Code.JPG"));
//		//
//		// fout.write(out.toByteArray());
//		//
//		// fout.flush();
//		// fout.close();
//		//
//		// } catch (FileNotFoundException e) {
//		// // Do Logging
//		// } catch (IOException e) {
//		// // Do Logging
//		// }
//		return file;
//	}
	
	
	public byte[] generateCode(List<String> list, Long id, String password) throws Exception {
//		File file = new File("Codes_" + id + "_" + new SimpleDateFormat("ddMMyyHHmmssSSS").format(new Date()) + ".pdf");
		ByteArrayOutputStream fos = null;
		if (null != list) {
			try {
				Document document = new Document();
				fos = new ByteArrayOutputStream();
				PdfWriter writer = PdfWriter.getInstance(document, fos);
				writer.setEncryption(password.getBytes(), "tcb0gavalli".getBytes(), PdfWriter.ALLOW_COPY, PdfWriter.ENCRYPTION_AES_128);
				document.open();
				
				addTitlePage(document);
				
				addContent(document, list);
				
				document.close();

			} catch (Exception ex) {
				throw ex;
			}
		} else {
			throw new Exception("Empty list returned. No codes in database.");
		}
		// try {
		// FileOutputStream fout = new FileOutputStream(new
		// File("C:\\QR_Code.JPG"));
		//
		// fout.write(out.toByteArray());
		//
		// fout.flush();
		// fout.close();
		//
		// } catch (FileNotFoundException e) {
		// // Do Logging
		// } catch (IOException e) {
		// // Do Logging
		// }
		return fos.toByteArray();
	}


	public static void main(String[] args) throws WriterException, IOException {
		String qrCodeText = "Naveen Injeti test";
		String filePath = "D:\\Naveen\\JD.png";
		int size = 200;
		String fileType = "png";
		File qrFile = new File(filePath);
		createQRImage(qrFile, qrCodeText, size, fileType);
		System.out.println("DONE");
	}

	private static void createQRImage(File qrFile, String qrCodeText, int size, String fileType) throws WriterException, IOException {
		// Create the ByteMatrix for the QR-Code that encodes the given String
		Hashtable hintMap = new Hashtable();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, size, size, hintMap);
		// Make the BufferedImage that are to hold the QRCode
		int matrixWidth = byteMatrix.getWidth();
		BufferedImage image = new BufferedImage(matrixWidth, matrixWidth, BufferedImage.TYPE_INT_RGB);
		image.createGraphics();

		Graphics2D graphics = (Graphics2D) image.getGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, matrixWidth, matrixWidth);
		// Paint and save the image using the ByteMatrix
		graphics.setColor(Color.BLACK);

		for (int i = 0; i < matrixWidth; i++) {
			for (int j = 0; j < matrixWidth; j++) {
				if (byteMatrix.get(i, j)) {
					graphics.fillRoundRect(i, j, 4, 4, 2, 1);// Rect(i, j, 1,
																// 1);
				}
			}
		}
		ImageIO.write(image, fileType, qrFile);
	}

	private static void addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		preface.add(new Paragraph("Truecheck QR codes", catFont));

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		preface.add(new Paragraph("Report generated by: Truecheck admin "+ new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				smallBold));
		addEmptyLine(preface, 3);
		preface.add(new Paragraph("This document contains confidential information", smallBold));

		addEmptyLine(preface, 8);

//		preface.add(new Paragraph("This document is a preliminary version and not subject to your license agreement or any other agreement with vogella.com ;-).", redFont));

		document.add(preface);
		// Start a new page
		document.newPage();
	}

//	private static void addContent(Document document, List<QRCodeDto> list) throws DocumentException, MalformedURLException, IOException {
//		
//		for (QRCodeDto qrCodeDto : list) {
////			ObjectMapper mapperObj = new ObjectMapper();
//			String encryptedString = qrCodeDto.toString();// CryptoUtil.encrypt(mapperObj.writeValueAsString(qrCodeDto));
//			System.out.println("encrypted :: " + encryptedString);
//			ByteArrayOutputStream out = QRCode.from(encryptedString).to(ImageType.PNG).stream();
//
//			Image image1 = Image.getInstance(out.toByteArray());
//			document.add(image1);
//			addEmptyLine(document, 1);
//		}
//
//	}
	
	private static void addContent(Document document, List<String> list) throws DocumentException, MalformedURLException, IOException {
		
		for (String qrCodeDto : list) {
//			ObjectMapper mapperObj = new ObjectMapper();
			String encryptedString = qrCodeDto.toString();// CryptoUtil.encrypt(mapperObj.writeValueAsString(qrCodeDto));
			System.out.println("encrypted :: " + encryptedString);
			ByteArrayOutputStream out = QRCode.from(encryptedString).to(ImageType.PNG).stream();

			Image image1 = Image.getInstance(out.toByteArray());
			document.add(image1);
			addEmptyLine(document, 1);
		}

	}



	private static void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
	
	private static void addEmptyLine(Document paragraph, int number) throws DocumentException {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
