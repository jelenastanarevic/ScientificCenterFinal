package UPP.Science_Center.service;

import java.io.File;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import UPP.Science_Center.model.Article;


@Component
public class PDFHandler {
	public Article getIndexUnit(File file) {
		Article retVal = new Article();
		try {
			PDFParser parser = new PDFParser( new RandomAccessFile(file, "r"));
			parser.parse();
			String text = getText(parser);
			//retVal.setContent(text);

			// metadata extraction
			PDDocument pdf = parser.getPDDocument();
			PDDocumentInformation info = pdf.getDocumentInformation();

			String title = ""+info.getTitle();
			//retVal.setTitle(title);

			String keywords = ""+info.getKeywords();
			String[] p = keywords.split(",");
			List<String> newKeywords = new ArrayList<String>();
			for(String s : p) {
				newKeywords.add(s.trim());
			}
			//retVal.setKeywords(newKeywords);
			
			retVal.setFile_name((file.getCanonicalPath()));
			
			
			pdf.close();
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}

		return retVal;
	}

	public String getText(File file) {
		try {
			PDFParser parser = new PDFParser( new RandomAccessFile(file, "r"));
			parser.parse();
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
	
	public String getText(PDFParser parser) {
		try {
			PDFTextStripper textStripper = new PDFTextStripper();
			String text = textStripper.getText(parser.getPDDocument());
			return text;
		} catch (IOException e) {
			System.out.println("Greksa pri konvertovanju dokumenta u pdf");
		}
		return null;
	}
}
