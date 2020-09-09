package com.upload.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.upload.demo.dao.CompanyRepository;
import com.upload.demo.dao.StockPriceRepository;
import com.upload.demo.dto.CompanyDTO;
import com.upload.demo.dto.StockPriceDTO;
import com.upload.demo.model.UploadSummary;

@Service
public class UploadService {
	
	@Autowired
    private StockPriceRepository stockRepository;
	
	@Autowired
	private CompanyRepository companyRepository;

    @Value("${excel.upload.path}")
    private String excelStoragePath;

    public void uploadExcel(MultipartFile file) throws Exception {
        try {
            File fileStoragePath = new File(excelStoragePath + file.getOriginalFilename());
            FileOutputStream out = new FileOutputStream(fileStoragePath);
            out.write(file.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            throw new Exception("Upload Excel failed");
        }
    }

    public List<StockPriceDTO> importExcelData2DB(MultipartFile file) throws Exception {
        List<StockPriceDTO> stockPriceList = new ArrayList<>();
        InputStream inputStream = null;
        Workbook workbook = null;
        try {
            inputStream = file.getInputStream();
            workbook = this.getWorkbook(inputStream, file.getOriginalFilename());
            if (null == workbook) {
                throw new Exception("workbook is empty！");
            }

            for (Row row : workbook.getSheetAt(0)) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                StockPriceDTO stockPriceDto = new StockPriceDTO();
                stockPriceDto.setCompanyCode(row.getCell(0).getStringCellValue());
                stockPriceDto.setExchangeName(row.getCell(1).getStringCellValue());
                stockPriceDto.setCurrentPrice((float) row.getCell(2).getNumericCellValue());
                Date input = row.getCell(3).getDateCellValue();
                LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                stockPriceDto.setDate(date);
                stockPriceDto.setTime(LocalTime.parse(row.getCell(4).getStringCellValue().trim()));
                stockPriceList.add(stockPriceDto);
            }
            for (StockPriceDTO stockItem : stockPriceList) {
            	stockRepository.save(stockItem);
            }
        } catch (Exception e) {
            throw new Exception("Import Data to  DB failed");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (workbook != null) {
                workbook.close();
            }
        }
        return stockPriceList;
    }

    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook workbook = null;
        String fileType = fileName.substring(fileName.lastIndexOf("."));
        if (".xls".equals(fileType)) {
            workbook = new HSSFWorkbook(inStr);
        } else if (".xlsx".equals(fileType)) {
            workbook = new XSSFWorkbook(inStr);
        } else {
            throw new Exception("Invalid Excel！");
        }
        return workbook;
    }
    
    @Transactional
    public UploadSummary getUploadSummary(List<StockPriceDTO> stockPriceList){
    	UploadSummary summary = new UploadSummary();
        if (CollectionUtils.isNotEmpty(stockPriceList)) {
        	String s = stockPriceList.get(0).getCompanyCode().substring(0, 6); 
//        	System.out.println(s);
        	CompanyDTO companydto = companyRepository.findByStockCode(s).iterator().next();
//        	System.out.println(companydtos.size());
        	summary.setCompanyName(companydto.getCompanyName());
            summary.setExchangeName(stockPriceList.get(0).getExchangeName());
            summary.setNumberOfRecords(stockPriceList.size());
            summary.setFrom(stockPriceList.get(0).getDate());
            summary.setTo(stockPriceList.get(stockPriceList.size()-1).getDate());
        }
        return summary;
    }


}
