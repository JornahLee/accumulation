package com.jornah.work.excel;

import java.util.ArrayList;
import java.util.Comparator;

public class OPOMRowComparator implements Comparator<ArrayList<Object>>{

    @Override
    public int compare(ArrayList<Object> o1, ArrayList<Object> o2) {
        String jobId1=o1.get(FindJobFlow.OPOM_JOB_ID_INDEX).toString();
        String jobId2=o1.get(FindJobFlow.OPOM_JOB_ID_INDEX).toString();
        return jobId1.compareTo(jobId2);
    }


}
