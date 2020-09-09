package com.upload.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.upload.demo.dto.StockPriceDTO;
import com.upload.demo.model.UploadSummary;
import com.upload.demo.service.UploadService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload-ws")
@CrossOrigin(origins = "*")
public class UploadController {
	
	@Autowired
	private UploadService uploadService;
	
	@PostMapping("/upload")
	public ResponseEntity<UploadSummary> uploadExcelFile(@RequestParam("file") MultipartFile file) throws Exception{
		uploadService.uploadExcel(file);
		List<StockPriceDTO> stockPriceList = uploadService.importExcelData2DB(file);
		return new ResponseEntity<>(uploadService.getUploadSummary(stockPriceList), HttpStatus.OK);
	}
}
