package org.finance;

import java.util.Comparator;

import model.StatDataHistory;

public class DataHistoryComparator implements Comparator<StatDataHistory> {

	@Override
	public int compare(StatDataHistory p, StatDataHistory q) {
		// TODO Auto-generated method stub
		if (p.getMonthAndYear().before(q.getMonthAndYear())) {
            return -1;
        } else if (p.getMonthAndYear().after(q.getMonthAndYear())) {
            return 1;
        } else {
            return 0;
        }     
	}

}
