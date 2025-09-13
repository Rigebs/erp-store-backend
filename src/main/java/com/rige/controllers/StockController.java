package com.rige.controllers;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.rige.dto.request.StockRequest;
import com.rige.dto.response.ApiResponse;
import com.rige.dto.response.StockResponse;
import com.rige.filters.StockFilter;
import com.rige.services.IStockService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final IStockService stockService;

    @PostMapping("/manage")
    public ResponseEntity<ApiResponse<StockResponse>> manageStock(@RequestBody StockRequest request) {
        final StockResponse response = stockService.handleStock(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Stock managed successfully",
                        response
                ));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<StockResponse>>> getAllStock(
            @RequestParam(required = false) Long productId,
            @RequestParam(required = false) Long warehouseId,
            @RequestParam(required = false) Integer minQuantity,
            @RequestParam(required = false) Integer maxQuantity,
            @RequestParam(required = false) Boolean belowMin,
            @PageableDefault Pageable pageable
    ) {
        final StockFilter filter = StockFilter.builder()
                .productId(productId)
                .warehouseId(warehouseId)
                .minQuantity(minQuantity)
                .maxQuantity(maxQuantity)
                .belowMin(belowMin)
                .build();

        final Page<StockResponse> stocks = stockService.getAllStock(filter, pageable);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Stock retrieved successfully",
                        stocks
                )
        );
    }

    @PostMapping("/export")
    public ResponseEntity<byte[]> exportStock(
            @RequestParam String format,
            @RequestBody List<StockResponse> data
    ) throws Exception {
        byte[] fileBytes;
        String filename;
        String contentType;

        if ("excel".equalsIgnoreCase(format)) {
            fileBytes = generateExcel(data);
            filename = "stocks.xlsx";
            contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        } else if ("pdf".equalsIgnoreCase(format)) {
            fileBytes = generatePdf(data);
            filename = "stocks.pdf";
            contentType = "application/pdf";
        } else {
            throw new IllegalArgumentException("Formato no soportado: " + format);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType(contentType))
                .body(fileBytes);
    }

    private byte[] generateExcel(List<StockResponse> data) throws Exception {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Stock");

            // Header
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Producto");
            header.createCell(2).setCellValue("Almacén");
            header.createCell(3).setCellValue("Cantidad");
            header.createCell(4).setCellValue("Stock mínimo");

            // Data
            int rowIdx = 1;
            for (StockResponse stock : data) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(stock.getId());
                row.createCell(1).setCellValue(stock.getProduct().getName());
                row.createCell(2).setCellValue(stock.getWarehouse().getName());
                row.createCell(3).setCellValue(stock.getQuantity());
                row.createCell(4).setCellValue(stock.getMinQuantity());
            }

            workbook.write(out);
            return out.toByteArray();
        }
    }

    private byte[] generatePdf(List<StockResponse> data) throws Exception {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            PdfPTable table = new PdfPTable(5); // 5 columnas
            table.addCell("ID");
            table.addCell("Producto");
            table.addCell("Almacén");
            table.addCell("Cantidad");
            table.addCell("Stock mínimo");

            for (StockResponse stock : data) {
                table.addCell(String.valueOf(stock.getId()));
                table.addCell(stock.getProduct().getName());
                table.addCell(stock.getWarehouse().getName());
                table.addCell(String.valueOf(stock.getQuantity()));
                table.addCell(String.valueOf(stock.getMinQuantity()));
            }

            document.add(table);
            document.close();

            return out.toByteArray();
        }
    }
}