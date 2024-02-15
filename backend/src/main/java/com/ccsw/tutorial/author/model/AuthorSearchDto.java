package com.ccsw.tutorial.author.model;

import com.ccsw.tutorial.common.pagination.PageableRequest;

/**
 * @author ccsw
 *
 */
public class AuthorSearchDto {

    private PageableRequest pageables;

    public PageableRequest getPageable() {
        return pageables;
    }

    public void setPageable(PageableRequest pageables) {
        this.pageables = pageables;
    }
}
