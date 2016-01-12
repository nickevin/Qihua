package com.qihua.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PageModel<T> extends PageImpl<T> implements Page<T> {

    private static final long serialVersionUID = 2318885386672191587L;

    public PageModel(List<T> content) {
        super(content);
    }

    public PageModel() {
        super(new ArrayList<T>());
    }

    public PageModel(List<T> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    @Override
    public boolean hasNext() {
        return getNumber() < getTotalPages();
    }

    public int getNext() {
        return getNumber() + 1;
    }

    @Override
    public boolean hasPrevious() {
        return getNumber() > 1;
    }

    public int getPrevious() {
        return getNumber() - 1;
    }

    public int getCurrent() {
        return super.getNumber() > getTotalPages() ? getTotalPages() : super.getNumber();
    }

    public List<T> getResultSet() {
        return getContent();
    }

}
