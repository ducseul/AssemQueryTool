/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemquerytool;

import java.util.Date;

/**
 *
 * @author Ducnm62
 */
public class QueryBlock {
    private String params;
    private String query;
    private Date timestamp;

    public QueryBlock() {
    }

    public String getParams() {
        return params;
    }

    public QueryBlock setParams(String params) {
        this.params = params;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public QueryBlock setQuery(String query) {
        this.query = query;
        return this;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public QueryBlock setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    
}
