package com.pmverification;

import java.util.List;

public class ProcessInstance {
    String id;
    String description;
    List<AuditTrailEntry> entryList;
    int numSimilarInstances;
    int groupedIdentifiers;
}
