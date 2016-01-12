package com.qihua.console.card;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qihua.common.FileMeta;
import com.qihua.common.PageModel;
import com.qihua.exception.MultipleObjectException;
import com.qihua.exception.NullObjectException;

@Service
public class CardService {

  private static final int BATCH_COMMIT_COUNT = 500;

  @Autowired
  private CardDAO cardDAO;

  public List<Card> find() {
    return cardDAO.select();
  }

  public Card find(String cardId) throws NullObjectException {
    try {
      return cardDAO.select(cardId);
    } catch (EmptyResultDataAccessException e) {
      throw new NullObjectException();
    }
  }

  public Card save(Card item) throws Exception {
    if (item.getCardId() == null) {
      if (findByCardNo(item.getCardNo()) == null) {
        return cardDAO.insert(item);
      }

      throw new MultipleObjectException();
    }

    return cardDAO.update(item);
  }

  public Card findByCardNo(String cardNo) throws Exception {
    try {
      return cardDAO.selectByCardNo(cardNo);
    } catch (EmptyResultDataAccessException e) {
      return null;
    } catch (IncorrectResultSizeDataAccessException e) {
      throw new MultipleObjectException();
    }
  }

  public List<Card> find(CardQueryParameter queryParam) {
    return cardDAO.select(queryParam);
  }

  public PageModel<Card> search(CardQueryParameter queryParam) {
    return cardDAO.selectByPagination(queryParam);
  }

  public void remove(String id) throws Exception {
    cardDAO.delete(id);
  }

  public List<CardUpload> findUpload() {
    return cardDAO.selectUpload();
  }

  public CardUpload findUpload(Long uploadId) {
    return cardDAO.selectUpload(uploadId);
  }

  public CardUpload saveUpload(CardUpload upload) throws Exception {
    Long uploadId = cardDAO.insertUpload(upload);

    upload.setUploadId(uploadId);

    return upload;
  }

  public void updateUploadTotal(CardUpload upload) throws Exception {
    cardDAO.updateUpload(upload);
  }

  public void batchSave(List<Card> list) throws Exception {
    cardDAO.batchInsert(list);
  }

  @Transactional(propagation = Propagation.REQUIRED)
  public int save(FileMeta meta) throws Exception {
    CardUpload upload = saveUpload(new CardUpload(0, meta.getName()));

    Workbook workbook = null;

    try {
      if (isExcel2003(meta.getName())) {
        workbook = new HSSFWorkbook(new FileInputStream(meta.getUrl()));
        // workbook = new HSSFWorkbook(new FileInputStream(BCSUtils.getObject(meta.getName())));
      } else {
        workbook = new XSSFWorkbook(new FileInputStream(meta.getUrl()));
        // workbook = new XSSFWorkbook(new FileInputStream(BCSUtils.getObject(meta.getName())));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        workbook.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    int total = 0;
    List<Card> list = new ArrayList<Card>();

    Sheet sheet = workbook.getSheetAt(0);

    for (int idx = 0, size = sheet.getPhysicalNumberOfRows(); idx < size; idx++) {
      Row row = sheet.getRow(idx);
      if (row == null) {
        continue;
      }

      Card newItem = readRow(row);
      newItem.setUploadId(upload.getUploadId());

      list.add(newItem);

      if (idx == BATCH_COMMIT_COUNT) {
        total += BATCH_COMMIT_COUNT;

        batchSave(list);

        list.clear();
      }
    }

    batchSave(list);

    total += list.size();

    {
      upload.setTotal(total);

      updateUploadTotal(upload);
    }

    return total;
  }

  private Card readRow(Row row) {
    return new Card(getCellValue(row.getCell(0)), getCellValue(row.getCell(1)),
        Integer.parseInt(getCellValue(row.getCell(2))));
  }

  public static boolean isExcel2003(String filename) {
    return filename.toLowerCase().endsWith("xls");

  }

  private String getCellValue(Cell cell) {
    try {
      return cell.getStringCellValue();
    } catch (Exception e) {
      return cell.getNumericCellValue() + "";
    }
  }

}
