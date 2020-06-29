package org.pack.store.resposeVo;

import org.pack.store.entity.DictEntity;

import java.util.List;

public class DictRes {

    private List<DictEntity> dictList;
    //总页数
    private Integer pageTotal;

    public List<DictEntity> getDictList() {
        return dictList;
    }

    public void setDictList(List<DictEntity> dictList) {
        this.dictList = dictList;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }
}
