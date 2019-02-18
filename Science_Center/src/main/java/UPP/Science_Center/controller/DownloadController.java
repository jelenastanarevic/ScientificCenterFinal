package UPP.Science_Center.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import UPP.Science_Center.model.Article;
import UPP.Science_Center.service.ArticleService;


@RestController
@RequestMapping(value = "/download")
public class DownloadController {
	
	@Autowired
	private ArticleService articleService;
	
	@CrossOrigin
	@RequestMapping(value = "/download/{idRada}", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download(@PathVariable("idRada") Long idRada) throws IOException {
		System.out.println("\n\tid koji je stigao iz requesta: " + idRada + "\n");
		//Long id = new Long(idRada);
		Article rad = articleService.findById(idRada);
		
		String baseDirectory = "";
		Path bookPath = Paths.get(baseDirectory, "C:\\Users\\Jelena\\Desktop\\Science_Center\\Science_Center\\src\\main\\java\\UPP\\Science_Center\\files\\"+rad.getFile_name());
		System.out.println("\n\n\tbook path: " + bookPath.toString());
		byte[] bookContent = Files.readAllBytes(bookPath);
	    ByteArrayResource resource = new ByteArrayResource(bookContent);

	    return ResponseEntity.ok()
	            .contentLength(bookContent.length)
	            .contentType(MediaType.parseMediaType("application/pdf"))
	            .body(resource);
	}

}
