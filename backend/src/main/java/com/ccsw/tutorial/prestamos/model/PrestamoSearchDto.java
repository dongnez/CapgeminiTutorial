package com.ccsw.tutorial.prestamos.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

public class PrestamoSearchDto {
    private PageableRequest pageables;
    private PrestamoFilterDto filterParams;

    public PageableRequest getPageable() {
        return pageables;
    }

    public void setPageable(PageableRequest pageables) {
        this.pageables = pageables;
    }

    public PrestamoFilterDto getFilterParams() {
        return filterParams;
    }

    public void setFilterParams(PrestamoFilterDto filterDto) {
        this.filterParams = filterDto;
    }
}
