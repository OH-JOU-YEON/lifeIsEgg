package com.lifeEgg.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedPageDTO <T> {
    
    private static final long pageSize = 10;
    
    private long page;
    private long size = 5;
    private long totalSize;
    
    private List<T> contentList = new ArrayList<T>();
    
    public long getPage() {
        return page < 1 ? 1 : page;
    }
    
    public long getTotalPage() {
        return (long)Math.ceil((double)totalSize/size);
    }
    
    public boolean getHasPrev() {
        return page <= 1 ? false : true;
    }
    
    public boolean getHasNext() {
        return page >= getTotalPage() ? false : true;
    }
    
    public long getFirstPage() {
        return ((getPage()-1)/pageSize|0)*pageSize + 1;
    }
    
    public long getLastPage() {
        long lastPage = ((getPage()-1)/pageSize|0)*pageSize + pageSize;
        return lastPage < getTotalPage() ? lastPage : getTotalPage();
    }
    
    public long getFirst() {
        long first = (getPage()-1) * size + 1;
        return first < 1 ? 1: first;
    }
    
    public long getLast() {
        return getFirst() + size - 1;
    }
    
    public String getUrl(String base, long i) {
        
        if(i > 1 && size != 10) base += "?page=" + i + "&size=" + size;
        else if(i > 1) base += "?page=" + i;
        else if(size != 10) base += "?size=" + size;
        
        return base;
    }
}