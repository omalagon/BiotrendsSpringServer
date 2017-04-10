package com.biotrends.services.oldconsumo.impl;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.oldconsumo.OldConsumoDTO;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.oldconsumodao.OldConsumoDAO;
import com.biotrends.services.oldconsumo.OldConsumoService;

import lombok.extern.slf4j.Slf4j;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.biotrends.utils.Utils.booleanToYesNoString;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author Oscar Malagon
 * @since 27/01/2017.
 */
@Slf4j
@Service
public class DefaultOldConsumoService implements OldConsumoService {

	private static final String FOLDER_PATH = "D:\\Documents\\NetBeansProjects\\Consumos\\";
    private static final String FILE_NAME = "Consumos_";
    private static final String XLS_EXTENTION = "_.xls";
    
    private final OldConsumoDAO oldConsumoDAO;

    @Autowired
    public DefaultOldConsumoService(OldConsumoDAO oldConsumoDAO){
        this.oldConsumoDAO = oldConsumoDAO;
    }

    @Override
    public List<OldConsumoDTO> listadoDeConsumos() {
        return oldConsumoDAO.getConsumos();
    }
    
    @Override
	public List<OldConsumoDTO> listadoDeConsumosPorFecha(String fecha) {
		return oldConsumoDAO.getConsumos(fecha);
	}

	@Override
	public String generateReport(String fecha) {
		Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        createHeaderRow(sheet);
        int rowCount = 0;
        List<OldConsumoDTO> listadoItems = listadoDeConsumosPorFecha(fecha);
        if (listadoItems != null) {
            for (OldConsumoDTO item : listadoItems) {
                Row row = sheet.createRow(++rowCount);
                writeBook(item, row);
            }
        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);

        String fileName = FOLDER_PATH + FILE_NAME + new Date().getTime() + XLS_EXTENTION;
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
        } catch (IOException ex) {
            log.error("Error creando el archivo", ex);
            throw new CommonBiotrendsRuntimeException("Error creando el archivo", ex);
        } finally {
            try {
                workbook.close();
            } catch (IOException ex) {
                log.error("Error cerrando el objeto Workbook", ex);
            }
        }

        return fileName;
	}
	
    private void createHeaderRow(Sheet sheet){
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        Font font = sheet.getWorkbook().createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short)14);
        cellStyle.setFont(font);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);


        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Código");

        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Descripción");

        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Presentación");

        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Cantidad");

        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Precio");

        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Cert. Calidad");

        cell = row.createCell(6);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Cumpl. Esp");

        cell = row.createCell(7);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total Compras");

        cell = row.createCell(8);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Total consumos");
        

    }
    
    private void writeBook(OldConsumoDTO consumo, Row row){
    	
    	Item item = consumo.getItem();
    	
    	Cell cell = row.createCell(0);
    	cell.setCellValue(item.getId());
    	
    	cell = row.createCell(1);
        cell.setCellValue(item.getItemBase().getDescripcion());

        cell = row.createCell(2);
        cell.setCellValue(item.getItemBase().getPresentacion());

        cell = row.createCell(3);
        cell.setCellValue(item.getItemBase().getCantidad());

        cell = row.createCell(4);
        cell.setCellValue(item.getItemBase().getPrecio());

        cell = row.createCell(5);
        cell.setCellValue(booleanToYesNoString(item.getItemBase().getCCalidad()));

        cell = row.createCell(6);
        cell.setCellValue(booleanToYesNoString(item.getItemBase().getCEsp()));
        
        cell = row.createCell(7);
        cell.setCellValue(consumo.getTotalCompras());
        
        cell = row.createCell(8);
        cell.setCellValue(consumo.getTotalDescargos());
    }

}
