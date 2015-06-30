package org.n52.sos.statistics.api.utils.dto;

import java.util.LinkedList;
import java.util.List;

public class KibanaConfigHolderDto {
    private List<KibanaConfigEntryDto> entries = new LinkedList<>();

    public void add(KibanaConfigEntryDto entry) {
        entries.add(entry);
    }

    public List<KibanaConfigEntryDto> getEntries() {
        return entries;
    }

    public void setEntries(List<KibanaConfigEntryDto> entries) {
        this.entries = entries;
    }

}
