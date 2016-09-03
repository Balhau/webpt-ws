package org.pub.pt.data.ws.piratebay.domain;

/**
 * Created by vitorfernandes on 9/3/16.
 */
public class PirateBayRequest {
    private String query;
    private int page;
    private String order;

    public PirateBayRequest(String query,int page,String order){
        this.order=order;
        this.query=query;
        this.page=page;
    }

    public PirateBayRequest(){

    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
