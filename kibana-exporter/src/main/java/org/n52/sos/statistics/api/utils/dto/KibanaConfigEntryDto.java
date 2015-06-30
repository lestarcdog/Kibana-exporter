package org.n52.sos.statistics.api.utils.dto;

public class KibanaConfigEntryDto {
    private String index;
    private String type;
    private String id;
    private String source;

    public KibanaConfigEntryDto() {
    }

    public KibanaConfigEntryDto(String index, String type, String id, String source) {
        super();
        this.index = index;
        this.type = type;
        this.id = id;
        this.source = source;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
