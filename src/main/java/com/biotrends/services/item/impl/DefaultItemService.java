package com.biotrends.services.item.impl;

import com.biotrends.entities.item.Item;
import com.biotrends.entities.item.ItemBase;
import com.biotrends.exceptions.CommonBiotrendsRuntimeException;
import com.biotrends.repositories.item.ItemRepository;
import com.biotrends.services.item.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.biotrends.utils.Utils.booleanToYesNoString;
import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Oscar Malagon
 * @since 22/12/2016.
 */
@Slf4j @Service public class DefaultItemService implements ItemService {

    private static final String FOLDER_PATH = "C:\\Users\\Oscar Malagon\\IdeaProjects\\Files\\";
    private static final String FILE_NAME = "InventarioBiotrends_";
    private static final String XLS_EXTENTION = "_.xls";

    private final ItemRepository repository;

    public DefaultItemService(ItemRepository repository) {
        this.repository = repository;
    }

    @Override public Optional<Item> createOrUpdateItem(Item item) {
        if (item.getId() != null) {
            Optional<Item> itemEncontrado = findById(item.getId());
            if (itemEncontrado.isPresent()) {
                Item itemToUpdate = itemEncontrado.get();
                ItemBase itemBase = itemToUpdate.getItemBase();
                itemBase.setCantidad(item.getItemBase().getCantidad());
                itemBase.setCCalidad(item.getItemBase().getCCalidad());
                itemBase.setCEsp(item.getItemBase().getCEsp());
                itemBase.setDescripcion(item.getItemBase().getDescripcion());
                itemBase.setInventario(item.getItemBase().getInventario());
                itemBase.setPrecio(item.getItemBase().getPrecio());
                itemBase.setPresentacion(item.getItemBase().getPresentacion());

                itemToUpdate.setItemBase(itemBase);

                return Optional.ofNullable(repository.saveAndFlush(itemToUpdate));
            } else {
                return Optional.ofNullable(repository.saveAndFlush(item));
            }
        }

        log.error("Error creando o actualizando el item");
        throw new CommonBiotrendsRuntimeException("Error creando o actualizando el item");
    }

    @Override public Optional<Item> findById(String id) {
        checkNotNull(id, "El id del ítem no puede ser nulo");

        try {
            Item item = repository.findOne(id);

            return Optional.ofNullable(item);
        } catch (Exception ex) {
            log.error("Error buscando el item con id [" + id + "]", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Page<Item> findAll(int page, int size) {
        checkNotNull(page, "La página no puede ser null");
        checkNotNull(size, "El tamaño de la pagina no puede ser null");
        checkArgument(page >= 0, "La página no puede ser negativa");
        checkArgument(size >= 0, "El tamaño de la página no puede ser negativa");

        try {
            Pageable pageable = new PageRequest(page, size);
            return repository.findAll(pageable);
        } catch (Exception ex) {
            log.error("Error buscando los items", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public List<Item> findAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            log.error("Error buscando los items", ex);
            throw new EntityNotFoundException();
        }
    }

    @Override public Optional<Item> delete(String id) {
        Optional<Item> itemById = findById(id);
        try {
            if(itemById.isPresent()){
                repository.delete(itemById.get());

                return itemById;
            }

            log.error("Error buscando item con id [" + id + "]");
            throw new CommonBiotrendsRuntimeException("Error buscando item con id [" + id + "]");
        } catch (Exception ex) {
            log.error("Error eliminando el item con id [" + id + "]", ex);
            throw new CommonBiotrendsRuntimeException("Error eliminando el item con id [" + id + "]", ex);
        }
    }

    @Override public String generateReport() {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet();
        createHeaderRow(sheet);
        int rowCount = 0;
        List<Item> listadoItems = findAll();
        if (listadoItems != null) {
            for (Item item : listadoItems) {
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

    private void writeBook(Item item, Row row) {
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

    }

    private Sort orderByInventarioAndDescripcion() {
        return new Sort(Sort.Direction.ASC, "itemBase.inventario", "itemBase.descripcion");
    }
}
